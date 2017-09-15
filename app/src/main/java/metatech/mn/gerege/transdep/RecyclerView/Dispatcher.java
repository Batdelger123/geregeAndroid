package metatech.mn.gerege.transdep.RecyclerView;

/**
 * Created by Enkhtur on 9/13/2017.
 */

public class Dispatcher {

    private int id;
    private int directionId;
    private String directionName;
    private int startStopId;
    private String startStopName;
    private int endStopId;
    private String endStopName;
    private int carTypeId;
    private String carTypeName;
    private int noOfSeat;
    private int availableSeat;

    public Dispatcher(int id, int directionId, String directionName, int startStopId, String startStopName, int endStopId, String endStopName, int carTypeId, String carTypeName, int noOfSeat, int availableSeat, String leaveDate) {
        this.id = id;
        this.directionId = directionId;
        this.directionName = directionName;
        this.startStopId = startStopId;
        this.startStopName = startStopName;
        this.endStopId = endStopId;
        this.endStopName = endStopName;
        this.carTypeId = carTypeId;
        this.carTypeName = carTypeName;
        this.noOfSeat = noOfSeat;
        this.availableSeat = availableSeat;
        this.leaveDate = leaveDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDirectionId() {
        return directionId;
    }

    public void setDirectionId(int directionId) {
        this.directionId = directionId;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public int getStartStopId() {
        return startStopId;
    }

    public void setStartStopId(int startStopId) {
        this.startStopId = startStopId;
    }

    public String getStartStopName() {
        return startStopName;
    }

    public void setStartStopName(String startStopName) {
        this.startStopName = startStopName;
    }

    public int getEndStopId() {
        return endStopId;
    }

    public void setEndStopId(int endStopId) {
        this.endStopId = endStopId;
    }

    public String getEndStopName() {
        return endStopName;
    }

    public void setEndStopName(String endStopName) {
        this.endStopName = endStopName;
    }

    public int getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(int carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public int getNoOfSeat() {
        return noOfSeat;
    }

    public void setNoOfSeat(int noOfSeat) {
        this.noOfSeat = noOfSeat;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int availableSeat) {
        this.availableSeat = availableSeat;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    private String leaveDate;

}
