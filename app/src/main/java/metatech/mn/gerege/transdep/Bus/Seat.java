package metatech.mn.gerege.transdep.Bus;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import metatech.mn.gerege.R;

/**
 * Created by Enkhtur on 9/19/2017.
 */

public class Seat extends View {

    private Context context;
    private RectF mSmallRect, mBigRect, mSmallRectBorder;
    private Paint mPaint, mBorderPaint, mNumberPaint;
    private float mSmallRectWidth, mSmallRectHeight;
    private float mBigRectWidth, mBigRectHeight;
    private float mSmallRectBorderRadius, mBigRectBorderRadius, mBorderRectR;
    private float mCanvasWidth, mCanvasHeight, mSeatMargin;
    private int mSeatColor, mBorderColor, mSeatSelectedColor;

    private String mText;
    private boolean isSelected;

    private SeatListener seatListener;
    private GestureDetector gestureDetector;

    //______________________Communicate with container_____________________________________________________________________
    public interface SeatListener {
        public void seatCicked(Seat seat);
    }

    //__________________Gesture_____________________________
    private class EventListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            if (seatListener == null)           // seatListener null bival ymar ch event sonsohgui
                return false;
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            seatListener.seatCicked(Seat.this);
            return true;
        }
    }
    //__________________Gesture_____________________________

    public void setListener(SeatListener seatListener) {
        this.seatListener = seatListener;
    }
    //________________________________________________________________________________________________________



    //_______________________________Constructors_____________________________________________________________
    public Seat(Context context) {
        super(context);
        init(null, context);
    }

    public Seat(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, context);
    }

    public Seat(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, context);
    }

    public Seat(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, context);
    }
    //_________________________________________________________________________________________________________

    private void init(AttributeSet attrs, Context context) {
        this.context = context;
        this.mSeatSelectedColor = ContextCompat.getColor(context, R.color.seat_color_orange);
        this.mSeatColor = ContextCompat.getColor(getContext(), R.color.seat_color_gray);
        this.isSelected = false;

        this.gestureDetector = new GestureDetector(getContext(), new EventListener());

        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.Seat);
            mSeatMargin = typedArray.getDimension(R.styleable.Seat_seat_margin, 0);
            mSeatColor = typedArray.getColor(R.styleable.Seat_seat_color, mSeatColor);
            mCanvasWidth = 100;
            mCanvasHeight = 86;
        }

        mSmallRect = new RectF();
        mBigRect = new RectF();
        mSmallRectBorder = new RectF();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mNumberPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void setUpSize(int w, int h) {
        mCanvasWidth = w;
        mCanvasHeight = h;

        mBorderColor = Color.WHITE;

        mBigRectWidth = mCanvasWidth - 2 * mSeatMargin;
        mBigRectHeight = (float) ((mCanvasHeight - 2 * mSeatMargin) * 0.8);
        mSmallRectWidth = (float) (mBigRectWidth * 0.74);
        mSmallRectHeight = mBigRectHeight;

        mSmallRect.top = mSeatMargin;
        mSmallRect.left = (float) (mSeatMargin + mBigRectWidth * 0.13);
        mSmallRect.right = mSmallRect.left + mSmallRectWidth;
        mSmallRect.bottom = mSmallRect.top + mSmallRectHeight;

        mBigRect.top = (float) (mSeatMargin + mSmallRectHeight * 0.25);
        mBigRect.left = mSeatMargin;
        mBigRect.right = mBigRect.left + mBigRectWidth;
        mBigRect.bottom = mBigRect.top + mBigRectHeight;

        mBigRectBorderRadius = (float) (mBigRect.width() * 0.08);
        mSmallRectBorderRadius = (float) (mSmallRect.width() * 0.2);

        mSmallRectBorder.top = mSmallRect.top;
        mSmallRectBorder.left = (float) (mSmallRect.left - mBigRectWidth * 0.03);
        mSmallRectBorder.right = (float) (mSmallRect.right + mBigRectWidth * 0.03);
        mSmallRectBorder.bottom = (float) (mSmallRect.bottom + mBigRectWidth * 0.03);
        mBorderRectR = (float) (mSmallRectBorder.width() * 0.2);

        mPaint.setAntiAlias(true);

        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);

        mNumberPaint.setColor(Color.WHITE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //boolean b = super.onTouchEvent(event);
        boolean b = gestureDetector.onTouchEvent(event);

        return b;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        RelativeLayout relativeLayout = (RelativeLayout) getParent();

        int w = resolveSize( relativeLayout.getWidth() * 106 / 720, widthMeasureSpec);
        int h = resolveSize( relativeLayout.getHeight() * 80 / 1440, heightMeasureSpec);

        setUpSize(w, h);

        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isSelected) {
            mPaint.setColor(mSeatSelectedColor);
        } else {
            mPaint.setColor(mSeatColor);
        }

        canvas.drawRoundRect(mBigRect, mBigRectBorderRadius, mBigRectBorderRadius, mPaint);
        canvas.drawRoundRect(mSmallRectBorder, mBorderRectR, mBorderRectR, mBorderPaint);
        canvas.drawRoundRect(mSmallRect, mSmallRectBorderRadius, mSmallRectBorderRadius, mPaint);

        float textSize = (mSmallRect.bottom - mSmallRect.top) / 2;
        mNumberPaint.setTextSize(textSize);
        canvas.drawText(mText, (mSmallRect.left + mSmallRect.right) / 2 - mNumberPaint.measureText(mText) / 2, (mSmallRect.top + mSmallRect.bottom) / 2 + mNumberPaint.getTextSize()/4, mNumberPaint);

        postInvalidate();
    }

    public void setSizePostion (int marginLeft, int marginTop, int width, int height) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.width = width;
        layoutParams.height = height;
        layoutParams.topMargin = marginTop;
        layoutParams.leftMargin = marginLeft;

        setLayoutParams(layoutParams);
    }

    public void setText(String string) {
        mText = string;
    }

    public String getText() {
        return mText;
    }

    public int getSeatColor() {
        return mSeatColor;
    }

    public void setSeatColor(int seatColor) {
        this.mSeatColor = seatColor;
    }

    public void setSeatSelectedColor(int color) {
        mSeatSelectedColor = color;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
