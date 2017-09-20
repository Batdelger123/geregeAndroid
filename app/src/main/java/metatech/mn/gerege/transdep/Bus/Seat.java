package metatech.mn.gerege.transdep.Bus;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import metatech.mn.gerege.R;

/**
 * Created by Enkhtur on 9/19/2017.
 */

public class Seat extends View {

    private RectF mSmallRect, mBigRect, mSmallRectBorder;
    private Paint mPaint, mBorderPaint;
    private float mSmallRectWidth, mSmallRectHeight;
    private float mBigRectWidth, mBigRectHeight;
    private float mSmallRectBorderRadius, mBigRectBorderRadius, mBorderRectR;
    private int mRectColor, mBorderColor;


    public Seat(Context context) {
        super(context);
        init(null);
    }

    public Seat(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Seat(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Seat(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    @SuppressWarnings("ResourceAsColor")
    private void init(AttributeSet attrs) {
        float size, margin;

        if (attrs == null) {
            size = 100;
            margin = 5;
            mRectColor = R.color.seat_color_green;
        } else {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.Seat);
            size = typedArray.getDimensionPixelSize(R.styleable.Seat_seat_size, 100);
            margin = typedArray.getDimension(R.styleable.Seat_seat_margin, 5);
            mRectColor = typedArray.getColor(R.styleable.Seat_seat_color, R.color.seat_color_green);
        }

        mBorderColor = Color.WHITE;

        mBigRectWidth = size - 2 * margin;
        mBigRectHeight = (float) (mBigRectWidth * 0.8);
        mSmallRectWidth = (float) (mBigRectWidth * 0.72);
        mSmallRectHeight = mSmallRectWidth;

        mSmallRect = new RectF();
        mSmallRect.top = margin;
        mSmallRect.left = (float) (margin + mBigRectWidth * 0.14);
        mSmallRect.right = mSmallRect.left + mSmallRectWidth;
        mSmallRect.bottom = mSmallRect.top + mSmallRectHeight;

        mBigRect = new RectF();
        mBigRect.top = (float) (margin + mSmallRectHeight * 0.25);
        mBigRect.left = margin;
        mBigRect.right = mBigRect.left + mBigRectWidth;
        mBigRect.bottom = mBigRect.top + mBigRectHeight;


        mBigRectBorderRadius = (float) (mBigRect.width() * 0.08);
        mSmallRectBorderRadius = (float) (mSmallRect.width() * 0.1);


        mSmallRectBorder = new RectF();
        mSmallRectBorder.top = mSmallRect.top;
        mSmallRectBorder.left = (float) (mSmallRect.left - mBigRectWidth * 0.04);
        mSmallRectBorder.right = (float) (mSmallRect.right + mBigRectWidth * 0.04);
        mSmallRectBorder.bottom = (float) (mSmallRect.bottom + mBigRectWidth * 0.04);
        mBorderRectR = (float) (mSmallRect.width() * 0.1);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mRectColor);

        mBorderPaint = new Paint();
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(mBigRect, mBigRectBorderRadius, mBigRectBorderRadius, mPaint);
        canvas.drawRoundRect(mSmallRectBorder, mBorderRectR, mBorderRectR, mBorderPaint);
        canvas.drawRoundRect(mSmallRect, mSmallRectBorderRadius, mSmallRectBorderRadius, mPaint);

        Log.d("Small", "" + mSmallRect.width() + ":" + mSmallRect.height() + " --> " + mSmallRect.top + "|" + mSmallRect.left + "|" + mSmallRect.right + "|" + mSmallRect.bottom);
        Log.d("Border", "" + mSmallRectBorder.width() + ":" + mSmallRectBorder.height() + " --> " + mSmallRectBorder.top + "|" + mSmallRectBorder.left + "|" + mSmallRectBorder.right + "|" + mSmallRectBorder.bottom);
        postInvalidate();
    }
}
