package metatech.mn.gerege.transdep.Bus;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import metatech.mn.gerege.R;

/**
 * Created by Enkhtur on 10/2/2017.
 */

public class SeatPosition {

    private int position[][];

    private int left;
    private int top;
    private int bottom;
    private int seatWidth;
    private int seatHeight;
    private List<Seat> additionalSeats;

    public static SeatPosition getInstance(Context context, int parentWidth, int parentHeight, int seatCount) {
        float rw = 0;
        float rh = 0;

        SeatPosition seatPosition = new SeatPosition();
        Seat seat;

        switch (seatCount) {

            case 10:
                rw = 764;
                rh = 960;

                seatPosition.left = (int) (parentWidth * (110 / rw));
                seatPosition.top = (int) (parentHeight * (495 / rh));
                seatPosition.bottom = (int) (parentHeight * (60 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int [][]{
                        {2, 3, 4},
                        {5, 6, 7},
                        {8, 9, 10}
                };

                seatPosition.additionalSeats = new ArrayList<>();
                seat = new Seat(context);
                seat.setText("1");
                seat.setSizePostion(
                        (int) (parentWidth * (557 / rw)),
                        (int) (parentHeight * (140 / rh)),
                        seatPosition.seatWidth,
                        seatPosition.seatHeight
                );

                seatPosition.additionalSeats.add(seat);


                return seatPosition;

            case 11:
                rw = 764;
                rh = 960;

                seatPosition.left = (int) (parentWidth * (110 / rw));
                seatPosition.top = (int) (parentHeight * (495 / rh));
                seatPosition.bottom = (int) (parentHeight * (60 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int [][]{
                        {3, 4, 5},
                        {6, 7, 8},
                        {9, 10, 11}
                };
                return seatPosition;

            case 14:

                rw = 764;
                rh = 960;

                seatPosition.left = (int) (parentWidth * (110 / rw));
                seatPosition.top = (int) (parentHeight * (370 / rh));
                seatPosition.bottom = (int) (parentHeight * (60 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int [][]{
                        {3, 4, 5},
                        {6, 7, 8},
                        {9, 10, 11},
                        {12, 13, 14}
                };
                return seatPosition;

            case 24:
                rw = 764;
                rh = 1200;

                seatPosition.left = (int) (parentWidth * (150 / rw));
                seatPosition.top = (int) (parentHeight * (490 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {5, 6, -1},
                        {7, 8, 9},
                        {10, 11, 12},
                        {13, 14, 15},
                        {16, 17, 18},
                        {19, 20, 21},
                        {22, 23, 24}
                };

                seatPosition.additionalSeats = new ArrayList<>();
                seat = new Seat(context);
                seat.setText("1");   // 1
                seat.setSizePostion(
                        (int) (parentWidth * (508 / rw)),
                        (int) (parentHeight * (135 / rh)),
                        seatPosition.seatWidth,
                        seatPosition.seatHeight
                );
                seatPosition.additionalSeats.add(seat);

                seat = new Seat(context);
                seat.setText("2");   // 2
                seat.setSizePostion(
                        (int) (parentWidth * (149 / rw)),
                        (int) (parentHeight * (350 / rh)),
                        seatPosition.seatWidth,
                        seatPosition.seatHeight
                );
                seatPosition.additionalSeats.add(seat);

                seat = new Seat(context);
                seat.setText("3");   // 3
                seat.setSizePostion(
                        (int) (parentWidth * (328 / rw)),
                        (int) (parentHeight * (350 / rh)),
                        seatPosition.seatWidth,
                        seatPosition.seatHeight
                );
                seatPosition.additionalSeats.add(seat);

                seat = new Seat(context);
                seat.setText("4");   // 4
                seat.setSizePostion(
                        (int) (parentWidth * (508 / rw)),
                        (int) (parentHeight * (350 / rh)),
                        seatPosition.seatWidth,
                        seatPosition.seatHeight
                );
                seatPosition.additionalSeats.add(seat);
                return seatPosition;

            case 25:
                rw = 764;
                rh = 1200;

                seatPosition.left = (int) (parentWidth * (100 / rw));
                seatPosition.top = (int) (parentHeight * (490 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {2, 3, -1, -1},
                        {4, 5, -1, -1},
                        {6, 7, 8, 9},
                        {10, 11, 12, 13},
                        {14, 15, 16, 17},
                        {18, 19, 20, 21},
                        {22, 23, 24, 25}
                };

                seatPosition.additionalSeats = new ArrayList<>();
                seat = new Seat(context);
                seat.setText("1");   // 1
                seat.setSizePostion(
                        (int) (parentWidth * (558 / rw)),
                        (int) (parentHeight * (350 / rh)),
                        seatPosition.seatWidth,
                        seatPosition.seatHeight
                );
                seatPosition.additionalSeats.add(seat);

                return seatPosition;

            case 26:
                rw = 764;
                rh = 1200;

                seatPosition.left = (int) (parentWidth * (100 / rw));
                seatPosition.top = (int) (parentHeight * (490 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {2, 3, -1, -1},
                        {4, 5, 6, -1},
                        {7, 8, 9, 10},
                        {11, 12, 13, 14},
                        {15, 16, 17, 18},
                        {19, 20, 21, 22},
                        {23, 24, 25, 26}
                };

                seatPosition.additionalSeats = new ArrayList<>();
                seat = new Seat(context);
                seat.setText("1");   // 1
                seat.setSizePostion(
                        (int) (parentWidth * (558 / rw)),
                        (int) (parentHeight * (330 / rh)),
                        seatPosition.seatWidth,
                        seatPosition.seatHeight
                );
                seatPosition.additionalSeats.add(seat);

                return seatPosition;

            case 28:
                rw = 764;
                rh = 1200;

                seatPosition.left = (int) (parentWidth * (80 / rw));
                seatPosition.top = (int) (parentHeight * (490 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {2, 3, -1, -1, -1},
                        {3, 5, -1, 6, 7},
                        {8, 9, -1, 10, 11},
                        {12, 13, -1, 14, 15},
                        {16, 17, -1, 18, 19},
                        {20, 21, -1, 22, 23},
                        {24, 25, 26, 27, 28}
                };

                seatPosition.additionalSeats = new ArrayList<>();
                seat = new Seat(context);
                seat.setText("1");   // 1
                seat.setSizePostion(
                        (int) (parentWidth * (576 / rw)),
                        (int) (parentHeight * (330 / rh)),
                        seatPosition.seatWidth,
                        seatPosition.seatHeight
                );
                seatPosition.additionalSeats.add(seat);

                return seatPosition;

            case 29:
                rw = 764;
                rh = 1200;

                seatPosition.left = (int) (parentWidth * (80 / rw));
                seatPosition.top = (int) (parentHeight * (490 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {3, 4, -1, -1, -1},
                        {5, 6, -1, 7, 8},
                        {9, 10, -1, 11, 12},
                        {13, 14, -1, 15, 16},
                        {17, 18, -1, 19, 20},
                        {21, 22, -1, 23, 24},
                        {25, 26, 27, 28, 29}
                };

                seatPosition.additionalSeats = new ArrayList<>();
                seat = new Seat(context);
                seat.setText("1");   // 1
                seat.setSizePostion(
                        (int) (parentWidth * (452 / rw)),
                        (int) (parentHeight * (330 / rh)),
                        seatPosition.seatWidth,
                        seatPosition.seatHeight
                );
                seatPosition.additionalSeats.add(seat);

                seat = new Seat(context);
                seat.setText("2");   // 1
                seat.setSizePostion(
                        (int) (parentWidth * (576 / rw)),
                        (int) (parentHeight * (330 / rh)),
                        seatPosition.seatWidth,
                        seatPosition.seatHeight
                );
                seatPosition.additionalSeats.add(seat);

                return seatPosition;
            case 34:
                rw = 764;
                rh = 1200;

                seatPosition.left = (int) (parentWidth * (80 / rw));
                seatPosition.top = (int) (parentHeight * (495 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (100 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (70 / rh));

                seatPosition.position = new int[][]{
                        {-1, -1, -1, -1, -1},
                        {2, 3, -1, 4, 5},
                        {6, 7, -1, 8, 9},
                        {10, 11, -1, 12, 13},
                        {14, 15, -1, 16, 17},
                        {18, 19, -1, 20, 21},
                        {22, 23, -1, 24, 25},
                        {26, 27, -1, 28, 29},
                        {30, 31, 32, 33, 34}
                };

                seatPosition.additionalSeats = new ArrayList<>();
                seat = new Seat(context);
                seat.setText("1");   // 1
                seat.setSizePostion(
                        (int) (parentWidth * (576 / rw)),
                        (int) (parentHeight * (330 / rh)),
                        seatPosition.seatWidth,
                        seatPosition.seatHeight
                );
                seatPosition.additionalSeats.add(seat);

                return seatPosition;

            case 35:
                rw = 764;
                rh = 1440;

                seatPosition.left = (int) (parentWidth * (74 / rw));
                seatPosition.top = (int) (parentHeight * (332 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {1, 2, -1, 3, 4},
                        {5, 6, -1, 7, 8},
                        {9, 10, -1, 11, 12},
                        {13, 14, -1, 15, 16},
                        {17, 18, -1, 19, 20},
                        {21, 22, -1, 23, 24},
                        {25, 26, -1, 27, 28},
                        {29, 30, -1, -1, -1},
                        {31, 32, 33, 34, 35}
                };

                return seatPosition;

            case 36:
                rw = 764;
                rh = 1200;

                seatPosition.left = (int) (parentWidth * (80 / rw));
                seatPosition.top = (int) (parentHeight * (490 / rh));
                seatPosition.bottom = (int) (parentHeight * (35 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {1, 2, -1, -1, -1},
                        {3, 4, -1, 5, 6},
                        {7, 8, -1, 9, 10},
                        {11, 12, -1, 13, 14},
                        {15, 16, -1, 17, 18},
                        {24, 25, -1, 26, 27},
                        {28, 29, -1, 30, 31},
                        {32, 33, 34, 35, 36}
                };

                seatPosition.additionalSeats = new ArrayList<>();
                int space = (parentWidth - (2 * seatPosition.left ) - (5 * seatPosition.seatWidth)) / 4;

                for (int i = 0; i < 5; i++) {
                    seat = new Seat(context);
                    seat.setText(String.valueOf(i + 19));   // 19 - 23
                    Log.d("qqweqweqwe", "" + (parentWidth));
                    seat.setSizePostion(
                            seatPosition.left + i * (seatPosition.seatWidth + space),
                            (int) (parentHeight * (330 / rh)),
                            seatPosition.seatWidth,
                            seatPosition.seatHeight
                    );
                    seatPosition.additionalSeats.add(seat);
                }

                return seatPosition;

            case 38:
                rw = 764;
                rh = 1200;

                seatPosition.left = (int) (parentWidth * (74 / rw));
                seatPosition.top = (int) (parentHeight * (332 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {-1, -1, -1, -1, 1},
                        {2, 3, -1, 4, 5},
                        {6, 7, -1, 8, 9},
                        {10, 11, -1, 12, 13},
                        {14, 15, -1, 16, 17},
                        {18, 19, -1, 20, 21},
                        {22, 23, -1, 24, 25},
                        {26, 27, -1, 28, 29},
                        {30, 31, -1, 32, 33},
                        {34, 35, 36, 37, 38}
                };
                return seatPosition;

            case 40:
                rw = 764;
                rh = 1440;

                seatPosition.left = (int) (parentWidth * (74 / rw));
                seatPosition.top = (int) (parentHeight * (332 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {1, 2, -1, 3, 4},
                        {5, 6, -1, 7, 8},
                        {9, 10, -1, 11, 12},
                        {13, 14, -1, 15, 16},
                        {17, 18, -1, 19, 20},
                        {21, 22, -1, 23, 24},
                        {25, 26, -1, 27, 28},
                        {29, 30, -1, 31, 32},
                        {33, 34, -1, 35, 36},
                        {37, 38, -1, 39, 40}
                };
                return seatPosition;

            case 41:
                rw = 764;
                rh = 1440;

                seatPosition.left = (int) (parentWidth * (74 / rw));
                seatPosition.top = (int) (parentHeight * (332 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {1, 2, -1, 3, 4},
                        {5, 6, -1, 7, 8},
                        {9, 10, -1, 11, 12},
                        {13, 14, -1, 15, 16},
                        {17, 18, -1, 19, 20},
                        {21, 22, -1, 23, 24},
                        {25, 26, -1, 27, 28},
                        {29, 30, -1, 31, 32},
                        {33, 34, -1, 35, 36},
                        {37, 38, 39, 40, 41}
                };
                return seatPosition;

            case 43:
                rw = 764;
                rh = 1440;

                seatPosition.left = (int) (parentWidth * (74 / rw));
                seatPosition.top = (int) (parentHeight * (332 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {1, 2, -1, -1, -1},
                        {3, 4, -1, 5, 6},
                        {7, 8, -1, 9, 10},
                        {11, 12, -1, 13, 14},
                        {15, 16, -1, 17, 18},
                        {19, 20, -1, 21, 22},
                        {23, 24, -1, 25, 26},
                        {27, 28, -1, 29, 30},
                        {31, 32, -1, 33, 34},
                        {35, 36, -1, 37, 38},
                        {39, 40, 41, 42, 43},
                };
                return seatPosition;

            case 44:
                rw = 764;
                rh = 1440;

                seatPosition.left = (int) (parentWidth * (74 / rw));
                seatPosition.top = (int) (parentHeight * (332 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {1, 2, -1, 3, 4},
                        {5, 6, -1, 7, 8},
                        {9, 10, -1, 11, 12},
                        {13, 14, -1, 15, 16},
                        {17, 18, -1, 19, 20},
                        {21, 22, -1, 23, 24},
                        {25, 26, -1, 27, 28},
                        {29, 30, -1, 31, 32},
                        {33, 34, -1, 35, 36},
                        {37, 38, -1, 39, 40},
                        {41, 42, -1, 43, 44}
                };
                return seatPosition;

            case 45:
                rw = 764;
                rh = 1440;

                seatPosition.left = (int) (parentWidth * (74 / rw));
                seatPosition.top = (int) (parentHeight * (332 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {1, 2, -1, 3, 4},
                        {5, 6, -1, 7, 8},
                        {9, 10, -1, 11, 12},
                        {13, 14, -1, 15, 16},
                        {17, 18, -1, 19, 20},
                        {21, 22, -1, 23, 24},
                        {25, 26, -1, 27, 28},
                        {29, 30, -1, 31, 32},
                        {33, 34, -1, 35, 36},
                        {37, 38, -1, 39, 40},
                        {41, 42, 43, 44, 45}
                };
                return seatPosition;

            case 48:
                rw = 764;
                rh = 1440;

                seatPosition.left = (int) (parentWidth * (74 / rw));
                seatPosition.top = (int) (parentHeight * (332 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {1, 2, -1, 3, 4},
                        {5, 6, -1, 7, 8},
                        {9, 10, -1, 11, 12},
                        {13, 14, -1, 15, 16},
                        {17, 18, -1, 19, 20},
                        {21, 22, -1, 23, 24},
                        {25, 26, -1, 27, 28},
                        {29, 30, -1, 31, 32},
                        {33, 34, -1, 35, 36},
                        {37, 38, -1, 39, 40},
                        {41, 42, -1, 43, 44},
                        {45, 46, -1, 47, 48}
                };
                return seatPosition;

            case 53:
                rw = 764;
                rh = 1440;

                seatPosition.left = (int) (parentWidth * (74 / rw));
                seatPosition.top = (int) (parentHeight * (332 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {1, 2, -1, 3, 4},
                        {5, 6, -1, 7, 8},
                        {9, 10, -1, 11, 12},
                        {13, 14, -1, 15, 16},
                        {17, 18, -1, 19, 20},
                        {21, 22, -1, 23, 24},
                        {25, 26, -1, 27, 28},
                        {29, 30, -1, 31, 32},
                        {33, 34, -1, 35, 36},
                        {37, 38, -1, 39, 40},
                        {41, 42, -1, 43, 44},
                        {45, 46, -1, 47, 48},
                        {49, 50, 51, 52, 53}
                };
                return seatPosition;

            case 54:
                rw = 764;
                rh = 1440;

                seatPosition.left = (int) (parentWidth * (74 / rw));
                seatPosition.top = (int) (parentHeight * (332 / rh));
                seatPosition.bottom = (int) (parentHeight * (30 / rh));
                seatPosition.seatWidth = (int) (parentWidth * (106 / rw));
                seatPosition.seatHeight = (int) (parentHeight * (80 / rh));

                seatPosition.position = new int[][]{
                        {-1, -1, -1, -1, 1},
                        {2, 3, -1, 4, 5},
                        {6, 7, -1, 8, 9},
                        {10, 11, -1, 12, 13},
                        {14, 15, -1, 16, 17},
                        {18, 19, -1, 20, 21},
                        {22, 23, -1, 24, 25},
                        {26, 27, -1, 28, 29},
                        {30, 31, -1, 32, 33},
                        {34, 35, -1, 36, 37},
                        {38, 39, -1, 40, 41},
                        {42, 43, -1, 44, 45},
                        {46, 47, -1, 48, 49},
                        {50, 51, 52, 53, 54}
                };
                return seatPosition;

            default:
                seatPosition.position = null;
                return null;
        }
    }

    public int[][] getSeatsPosition() {
        return position;
    }

    public void setSeatsPosition(int[][] seats) {
        this.position = seats;
    }

    public List<Seat> getAdditionalSeats() {
        return additionalSeats;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getSeatWidth() {
        return seatWidth;
    }

    public void setSeatWidth(int seatWidth) {
        this.seatWidth = seatWidth;
    }

    public int getSeatHeight() {
        return seatHeight;
    }

    public void setSeatHeight(int seatHeight) {
        this.seatHeight = seatHeight;
    }
}
