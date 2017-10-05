package metatech.mn.gerege.transdep.RecyclerView.data;

import java.io.Serializable;

/**
 * Created by Enkhtur on 9/18/2017.
 */

public class Dispatcher implements Serializable{

    private int id;
    private int directionId;
    private int startStopId;
    private int endStopId;
    private int carTypeId;
    private int seatCount;
    private int companyId;
    private String directionName;
    private String startStopName;
    private String endStopName;
    private String carTypeName;
    private String leaveDate;
    private String companyName;

    public Dispatcher() {
    }

    public Dispatcher(int id, int directionId, int startStopId, int endStopId, int carTypeId, int seatCount, int companyId, String directionName, String startStopName, String endStopName, String carTypeName, String leaveDate, String companyName) {
        this.id = id;
        this.directionId = directionId;
        this.startStopId = startStopId;
        this.endStopId = endStopId;
        this.carTypeId = carTypeId;
        this.seatCount = seatCount;
        this.companyId = companyId;
        this.directionName = directionName;
        this.startStopName = startStopName;
        this.endStopName = endStopName;
        this.carTypeName = carTypeName;
        this.leaveDate = leaveDate;
        this.companyName = companyName;
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

    public int getStartStopId() {
        return startStopId;
    }

    public void setStartStopId(int startStopId) {
        this.startStopId = startStopId;
    }

    public int getEndStopId() {
        return endStopId;
    }

    public void setEndStopId(int endStopId) {
        this.endStopId = endStopId;
    }

    public int getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(int carTypeId) {
        this.carTypeId = carTypeId;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int sitCount) {
        this.seatCount = sitCount;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public String getStartStopName() {
        return startStopName;
    }

    public void setStartStopName(String startStopName) {
        this.startStopName = startStopName;
    }

    public String getEndStopName() {
        return endStopName;
    }

    public void setEndStopName(String endStopName) {
        this.endStopName = endStopName;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
