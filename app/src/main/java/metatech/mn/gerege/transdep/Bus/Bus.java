package metatech.mn.gerege.transdep.Bus;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import metatech.mn.gerege.R;
import metatech.mn.gerege.transdep.RecyclerView.data.Dispatcher;

public class Bus extends AppCompatActivity implements Seat.SeatListener, View.OnClickListener {

    public static int BUS_BIG = 1;
    public static int BUS_MEDIUM = 2;
    public static int BUS_SMALL = 3;
    public static String BUS_SEAT_COUNT = "countSeat";
    public static String BUS_AVAIL_SEATS = "availSeats";
    public static String BUS_DISPATCHER = "dispatcher";
    public static String BUS_PASSENGER = "noOfPassenger";

    private RelativeLayout mRelativeLayout;

    private List<Integer> availSeats;
    private List<Seat> selectedSeats;
    private int countPassenger;
    private int countSeat;
    private Dispatcher dispatcher;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        dispatcher = (Dispatcher) bundle.getSerializable(Bus.BUS_DISPATCHER);
        availSeats = bundle.getIntegerArrayList(Bus.BUS_AVAIL_SEATS);
        countPassenger = bundle.getInt(Bus.BUS_PASSENGER);
        countSeat = bundle.getInt(Bus.BUS_SEAT_COUNT);
        selectedSeats = new ArrayList<>();

        mRelativeLayout = (RelativeLayout) findViewById(R.id.root_bus);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
        floatingActionButton.hide();

        if (dispatcher.getCarTypeId() == BUS_BIG) {
            mRelativeLayout.setBackgroundResource(R.drawable.bus_big);
        }
        if (dispatcher.getCarTypeId() == BUS_MEDIUM) {
            mRelativeLayout.setBackgroundResource(R.drawable.bus_medium);
        }
        if (dispatcher.getCarTypeId() == BUS_SMALL) {
            mRelativeLayout.setBackgroundResource(R.drawable.bus_small);
        }

        final Handler handler = new Handler();
        handler.postDelayed(
            new Runnable() {
                @Override
                public void run() {
                    if (mRelativeLayout.getWidth() == 0 && mRelativeLayout.getHeight() == 0) {
                        handler.postDelayed(this, 10);
                        return;
                    }

                    SeatPosition seatPosition = SeatPosition.getInstance(Bus.this, mRelativeLayout.getWidth(), mRelativeLayout.getHeight(), countSeat);

                    drawBus(
                            seatPosition.getSeatsPosition(),
                            seatPosition.getLeft(),
                            seatPosition.getTop(),
                            seatPosition.getBottom(),
                            seatPosition.getSeatWidth(),
                            seatPosition.getSeatHeight()
                    );

                    List<Seat> additionalSeats = seatPosition.getAdditionalSeats();

                    for (Seat seat : additionalSeats) {
                        mRelativeLayout.addView(seat);
                        seat.setListener(Bus.this);
                        if (seat.getText().equals("1") || seat.getText().equals("2")) {
                            seat.setSeatColor(ContextCompat.getColor(Bus.this, R.color.seat_color_yellow));
                        }
                    }
                }
            },
            10
        );

    }



    @Override
    public void seatCicked(Seat seat) {
        if (seat.isSelected()) {        // songoson suudlaa tsutslah
            seat.setSelected(false);
            selectedSeats.remove(seat);
            floatingActionButton.hide();
        } else {                        // suudal songoh
            if (selectedSeats.size() == countPassenger) {
                selectedSeats.get(0).setSelected(false);
                selectedSeats.remove(0).invalidate();
                selectedSeats.add(seat);
                seat.setSelected(true);
            }
            if (selectedSeats.size() < countPassenger) {
                seat.setSelected(true);
                selectedSeats.add(seat);
            }
            if (selectedSeats.size() == countPassenger && selectedSeats.size() != 0) {
                floatingActionButton.show();
            }
        }
        seat.invalidate();
    }

    public void drawBus(int array[][], int seatLeftMargin, int seatTopmargin, int seatBottommargin, int seatWidth, int seatHeight) {
        int noSeatPerRow = array[0].length;
        int noSeatPerCol = array.length;

        int widthBetweenSeat = (mRelativeLayout.getWidth() - seatLeftMargin * 2 - (seatWidth * noSeatPerRow)) / (noSeatPerRow - 1);
        int heightBetweenSeat = (mRelativeLayout.getHeight() - seatTopmargin - seatBottommargin - (seatHeight * noSeatPerCol)) / (noSeatPerCol - 1);

        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == -1)
                    continue;

                Seat seat = new Seat(Bus.this);

                seat.setText(String.valueOf(array[i][j]));
                seat.setSeatColor(ContextCompat.getColor(Bus.this, R.color.seat_color_gray));
                seat.setSizePostion(
                        seatLeftMargin + j * (seatWidth + widthBetweenSeat),
                        seatTopmargin + i * (seatHeight + heightBetweenSeat),
                        seatWidth,
                        seatHeight
                );
                seat.setListener(null);
                mRelativeLayout.addView(seat);

                if (availSeats.contains(array[i][j])) {
                    seat.setListener(Bus.this);
                    seat.setSeatColor(ContextCompat.getColor(Bus.this, R.color.seat_color_green));

                    if (seat.getText().equals("1") || seat.getText().equals("2")) {
                        seat.setSeatColor(ContextCompat.getColor(Bus.this, R.color.seat_color_yellow));
                    }

                    if (temp < countPassenger && (seat.getSeatColor() != ContextCompat.getColor(Bus.this, R.color.seat_color_yellow))) {
                        seat.setSelected(true);
                        temp++;
                        selectedSeats.add(seat);
                    }
                }
            }
        }

        if (selectedSeats.size() == countPassenger && selectedSeats.size() != 0) {
            floatingActionButton.show();
        }
    }

    @Override
    public void onClick(View v) {
        if (floatingActionButton == v) {
            Toast.makeText(Bus.this, "Clicked" + "", Toast.LENGTH_SHORT).show();
        }
    }
}