package metatech.mn.gerege.transdep.RecyclerView;

/**
 * Created by Enkhtur on 9/12/2017.
 */

public class ListItem {

    private String from;
    private String to;
    private String date;
    private String lastStop;
    private String busType;
    private int countSeat;

    public ListItem() {
        this.from = "УБ.Сонгинохайрхан";
        this.to = "Ар.Эрдэнэбулган";
        this.date = "2017-12-12, 17:30";
        this.lastStop = "Вокзал";
        this.busType = "Дунд оврын автобус";
        this.countSeat = 45;
    }

    public ListItem(String from, String to, String date, String parking, String busType, int countSeat) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.lastStop = parking;
        this.busType = busType;
        this.countSeat = countSeat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public int getCountSeat() {
        return countSeat;
    }

    public void setCountSeat(int countSeat) {
        this.countSeat = countSeat;
    }

    public String getLastStop() {
        return lastStop;
    }

    public void setLastStop(String parking) {
        this.lastStop = parking;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
