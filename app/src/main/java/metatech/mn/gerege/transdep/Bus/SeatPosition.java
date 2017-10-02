package metatech.mn.gerege.transdep.Bus;

/**
 * Created by Enkhtur on 10/2/2017.
 */

public class SeatPosition {

    private int seatLeftMargin;
    private int seatTopmargin;
    private int seatBottommargin;
    private int seatWidth;
    private int seatHeight;

    public SeatPosition() {
    }

    public SeatPosition(int seatLeftMargin, int seatTopmargin, int seatBottommargin, int seatWidth, int seatHeight) {
        this.seatLeftMargin = seatLeftMargin;
        this.seatTopmargin = seatTopmargin;
        this.seatBottommargin = seatBottommargin;
        this.seatWidth = seatWidth;
        this.seatHeight = seatHeight;
    }

    public static int[][] getSeatsPosition(int seatCount) {
        int position[][];

        switch (seatCount) {

            case 24:
                position = new int[][]{
                        {2, 3, 4},
                        {5, 6, -1},
                        {7, 8, 9},
                        {10, 11, 12},
                        {13, 14, 15},
                        {16, 17, 18},
                        {19, 20, 21},
                        {22, 23, 24}
                };
                break;

            case 25:
                position = new int[][]{
                        {-1, -1, -1, 1},
                        {2, 3, -1, -1},
                        {4, 5, -1, -1},
                        {6, 7, 8, 9},
                        {10, 11, 12, 13},
                        {14, 15, 16, 17},
                        {18, 19, 20, 21},
                        {22, 23, 24, 25}
                };
                break;

            case 26:
                position = new int[][]{
                        {-1, -1, -1, 1},
                        {2, 3, -1, -1},
                        {4, 5, 6, -1},
                        {7, 8, 9, 10},
                        {11, 12, 13, 14},
                        {15, 16, 17, 18},
                        {19, 20, 21, 22},
                        {23, 24, 25, 26}
                };
                break;

            case 28:
                position = new int[][]{
                        {-1, -1, -1, -1, 1},
                        {2, 3, -1, -1, -1},
                        {3, 5, -1, 6, 7},
                        {8, 9, -1, 10, 11},
                        {12, 13, -1, 14, 15},
                        {16, 17, -1, 18, 19},
                        {20, 21, -1, 22, 23},
                        {24, 25, 26, 27, 28}
                };
                break;

            case 29:
                position = new int[][]{
                        {-1, -1, -1, 1, 2},
                        {3, 4, -1, -1, -1},
                        {5, 6, -1, 7, 8},
                        {9, 10, -1, 11, 12},
                        {13, 14, -1, 15, 16},
                        {17, 18, -1, 19, 20},
                        {21, 22, -1, 23, 24},
                        {25, 26, 27, 28, 29}
                };
                break;
            case 34:
                position = new int[][]{
                        {-1, -1, -1, -1, 1},
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
                break;

            case 35:
                position = new int[][]{
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
                break;

            case 36:
                position = new int[][]{
                        {19, 20, 21, 22, 23},
                        {1, 2, -1, -1, -1},
                        {3, 4, -1, 5, 6},
                        {7, 8, -1, 9, 10},
                        {11, 12, -1, 13, 14},
                        {15, 16, -1, 17, 18},
                        {24, 25, -1, 26, 27},
                        {28, 29, -1, 30, 31},
                        {32, 33, 34, 35, 36}
                };
                break;

            case 38:
                position = new int[][]{
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
                break;

            case 40:
                position = new int[][]{
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
                break;

            case 41:
                position = new int[][]{
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
                break;

            case 43:
                position = new int[][]{
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
                break;

            case 44:
                position = new int[][]{
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
                break;

            case 45:
                position = new int[][]{
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
                break;

            case 48:
                position = new int[][]{
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
                break;

            case 53:
                position = new int[][]{
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
                break;

            case 54:
                position = new int[][]{
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
                break;

            default:
                position = null;
                break;
        }

        return position;
    }

    public int getSeatLeftMargin() {
        return seatLeftMargin;
    }

    public void setSeatLeftMargin(int seatLeftMargin) {
        this.seatLeftMargin = seatLeftMargin;
    }

    public int getSeatTopmargin() {
        return seatTopmargin;
    }

    public void setSeatTopmargin(int seatTopmargin) {
        this.seatTopmargin = seatTopmargin;
    }

    public int getSeatBottommargin() {
        return seatBottommargin;
    }

    public void setSeatBottommargin(int seatBottommargin) {
        this.seatBottommargin = seatBottommargin;
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
