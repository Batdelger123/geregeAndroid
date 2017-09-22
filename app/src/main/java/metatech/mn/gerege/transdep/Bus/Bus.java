package metatech.mn.gerege.transdep.Bus;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.Toast;
import metatech.mn.gerege.R;

public class Bus extends AppCompatActivity implements Seat.SeatListener{

    private View view;
    private RelativeLayout mRelativeLayout;

    public Bus() {
    }

    public Bus(int totalSeat, int[] availSeats) {

    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        Log.d("LayoutSize", "" + "Ssss");

        mRelativeLayout = (RelativeLayout) findViewById(R.id.root_bus);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // left : 74
                // top  : 332
                int noSeatPerRow = 5;
                int noSeatPerCol = 11;
                int totalSeat = 45;

                int seatLeftMargin = (mRelativeLayout.getWidth() * 74) / 720;
                int seatTopmargin = (mRelativeLayout.getHeight() * 332) / 1440;
                int seatBottommargin = (mRelativeLayout.getHeight() * 30) / 1440;

                int seatWidth = (mRelativeLayout.getWidth() * 106) / 720;
                int seatHeight =  (mRelativeLayout.getHeight() * 80) / 1440;

                int widthBetweenSeat = (mRelativeLayout.getWidth() - seatLeftMargin * 2 - (seatWidth * noSeatPerRow)) / (noSeatPerRow - 1);
                int heightBetweenSeat = (mRelativeLayout.getHeight() - seatTopmargin - seatBottommargin - (seatHeight * noSeatPerCol)) / (noSeatPerCol - 1);
                Log.d("widthBetweenSeat", "" + widthBetweenSeat);
                Log.d("heightBetweenSeat", "" + heightBetweenSeat);

                int counter = 0;

                for (int i = 0; i < noSeatPerCol; i++) {
                    for (int j = 0; j < noSeatPerRow; j++) {
                        if (j == 2 && i != noSeatPerCol - 1)        // [0:3 - 10:3]
                            continue;

                        Seat seat = new Seat(getApplicationContext());
                        seat.setSeatColor(ContextCompat.getColor(Bus.this, R.color.seat_color_green));
                        seat.setText(String.valueOf(++  counter));

                        seat.setListener(Bus.this);     // seatCicked(String);

                        seat.setSizePostion(
                                seatLeftMargin + j * (seatWidth + widthBetweenSeat),
                                seatTopmargin + i * (seatHeight + heightBetweenSeat),
                                seatWidth,
                                seatHeight
                        );

                        //seat.setText(String.valueOf(++counter));

                        mRelativeLayout.addView(seat);
                    }
                }
            }
        }, 10);
    }

    @Override
    public void seatCicked(Seat seat) {
        Toast.makeText(getApplicationContext(), "seat " + seat.getText(), Toast.LENGTH_SHORT).show();

        if (seat.isSelected()) {
            seat.setSelected(false);
        } else {
            seat.setSelected(true);
        }

        seat.invalidate();
    }
}
