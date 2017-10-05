package metatech.mn.gerege.database;

import java.io.Serializable;

/**
 * Created by Coder-Erdenebayar on 8/23/2017.
 */

public class Tariff implements Serializable{
    private int    id;
    private int    direction_id;
    private String direction_name;
    private int    start_stop_id;
    private String start_stop_name;
    private int    end_stop_id;
    private String end_stop_name;
    private int    aimag_id;
    private int    end_aimag_id;
    private int    iscenter;
    private int bigPrice;
    private int bigChildPrice;
    private int bigInsurance;
    private int bigChildInsurance;
    private int midPrice;
    private int midChildPrice;
    private int midInsurance;
    private int midChildInsurance;
    private int litPrice;
    private int litChildPrice;
    private int litInsurance;
    private int litChildInsurance;
    private int sitPrice;
    private int sitChildPrice;
    private int sitInsurance;
    private int sitChildInsurance;
    private int ticketTypeId;
    private int startStopSequence;
    private int endStopSequence;
    private int bigPricePercent;
    private int midPricePercent;
    private int litPricePercent;
    private int sitPricePercent;
    private boolean isStartTariff = true;

    public boolean isStartTariff() {
        return isStartTariff;
    }

    public void setStartTariff(boolean startTariff) {
        isStartTariff = startTariff;
    }

    @Override
    public String toString() {
        if (isStartTariff) {
            if (iscenter == 1)
                return start_stop_name + " (Төв)";
            return start_stop_name;
        }

        if (iscenter == 1)
            return end_stop_name + " (Төв)";
        return end_stop_name;
    }

    public Tariff(int id, int direction_id, String direction_name, int start_stop_id, String start_stop_name, int end_stop_id, String end_stop_name, int aimag_id, int end_aimag_id, int iscenter, int bigPrice, int bigChildPrice, int bigInsurance, int bigChildInsurance, int midPrice, int midChildPrice, int midInsurance, int midChildInsurance, int litPrice, int litChildPrice, int litInsurance, int litChildInsurance, int sitPrice, int sitChildPrice, int sitInsurance, int sitChildInsurance, int ticketTypeId, int startStopSequence, int endStopSequence, int bigPricePercent, int midPricePercent, int litPricePercent, int sitPricePercent) {
        this.id = id;
        this.direction_id = direction_id;
        this.direction_name = direction_name;
        this.start_stop_id = start_stop_id;
        this.start_stop_name = start_stop_name;
        this.end_stop_id = end_stop_id;
        this.end_stop_name = end_stop_name;
        this.aimag_id = aimag_id;
        this.end_aimag_id = end_aimag_id;
        this.iscenter = iscenter;
        this.bigPrice = bigPrice;
        this.bigChildPrice = bigChildPrice;
        this.bigInsurance = bigInsurance;
        this.bigChildInsurance = bigChildInsurance;
        this.midPrice = midPrice;
        this.midChildPrice = midChildPrice;
        this.midInsurance = midInsurance;
        this.midChildInsurance = midChildInsurance;
        this.litPrice = litPrice;
        this.litChildPrice = litChildPrice;
        this.litInsurance = litInsurance;
        this.litChildInsurance = litChildInsurance;
        this.sitPrice = sitPrice;
        this.sitChildPrice = sitChildPrice;
        this.sitInsurance = sitInsurance;
        this.sitChildInsurance = sitChildInsurance;
        this.ticketTypeId = ticketTypeId;
        this.startStopSequence = startStopSequence;
        this.endStopSequence = endStopSequence;
        this.bigPricePercent = bigPricePercent;
        this.midPricePercent = midPricePercent;
        this.litPricePercent = litPricePercent;
        this.sitPricePercent = sitPricePercent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDirection_id() {
        return direction_id;
    }

    public void setDirection_id(int direction_id) {
        this.direction_id = direction_id;
    }

    public String getDirection_name() {
        return direction_name;
    }

    public void setDirection_name(String direction_name) {
        this.direction_name = direction_name;
    }

    public int getStart_stop_id() {
        return start_stop_id;
    }

    public void setStart_stop_id(int start_stop_id) {
        this.start_stop_id = start_stop_id;
    }

    public String getStart_stop_name() {
        return start_stop_name;
    }

    public void setStart_stop_name(String start_stop_name) {
        this.start_stop_name = start_stop_name;
    }

    public int getEnd_stop_id() {
        return end_stop_id;
    }

    public void setEnd_stop_id(int end_stop_id) {
        this.end_stop_id = end_stop_id;
    }

    public String getEnd_stop_name() {
        return end_stop_name;
    }

    public void setEnd_stop_name(String end_stop_name) {
        this.end_stop_name = end_stop_name;
    }

    public int getAimag_id() {
        return aimag_id;
    }

    public void setAimag_id(int aimag_id) {
        this.aimag_id = aimag_id;
    }

    public int getIscenter() {
        return iscenter;
    }

    public void setIscenter(int iscenter) {
        this.iscenter = iscenter;
    }

    public int getEnd_aimag_id() {
        return end_aimag_id;
    }

    public void setEnd_aimag_id(int end_aimag_id) {
        this.end_aimag_id = end_aimag_id;
    }

    public int getBigPrice() {
        return bigPrice;
    }

    public void setBigPrice(int bigPrice) {
        this.bigPrice = bigPrice;
    }

    public int getBigChildPrice() {
        return bigChildPrice;
    }

    public void setBigChildPrice(int bigChildPrice) {
        this.bigChildPrice = bigChildPrice;
    }

    public int getBigInsurance() {
        return bigInsurance;
    }

    public void setBigInsurance(int bigInsurance) {
        this.bigInsurance = bigInsurance;
    }

    public int getBigChildInsurance() {
        return bigChildInsurance;
    }

    public void setBigChildInsurance(int bigChildInsurance) {
        this.bigChildInsurance = bigChildInsurance;
    }

    public int getMidPrice() {
        return midPrice;
    }

    public void setMidPrice(int midPrice) {
        this.midPrice = midPrice;
    }

    public int getMidChildPrice() {
        return midChildPrice;
    }

    public void setMidChildPrice(int midChildPrice) {
        this.midChildPrice = midChildPrice;
    }

    public int getMidInsurance() {
        return midInsurance;
    }

    public void setMidInsurance(int midInsurance) {
        this.midInsurance = midInsurance;
    }

    public int getMidChildInsurance() {
        return midChildInsurance;
    }

    public void setMidChildInsurance(int midChildInsurance) {
        this.midChildInsurance = midChildInsurance;
    }

    public int getLitPrice() {
        return litPrice;
    }

    public void setLitPrice(int litPrice) {
        this.litPrice = litPrice;
    }

    public int getLitChildPrice() {
        return litChildPrice;
    }

    public void setLitChildPrice(int litChildPrice) {
        this.litChildPrice = litChildPrice;
    }

    public int getLitInsurance() {
        return litInsurance;
    }

    public void setLitInsurance(int litInsurance) {
        this.litInsurance = litInsurance;
    }

    public int getLitChildInsurance() {
        return litChildInsurance;
    }

    public void setLitChildInsurance(int litChildInsurance) {
        this.litChildInsurance = litChildInsurance;
    }

    public int getSitPrice() {
        return sitPrice;
    }

    public void setSitPrice(int sitPrice) {
        this.sitPrice = sitPrice;
    }

    public int getSitChildPrice() {
        return sitChildPrice;
    }

    public void setSitChildPrice(int sitChildPrice) {
        this.sitChildPrice = sitChildPrice;
    }

    public int getSitInsurance() {
        return sitInsurance;
    }

    public void setSitInsurance(int sitInsurance) {
        this.sitInsurance = sitInsurance;
    }

    public int getSitChildInsurance() {
        return sitChildInsurance;
    }

    public void setSitChildInsurance(int sitChildInsurance) {
        this.sitChildInsurance = sitChildInsurance;
    }

    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(int ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public int getStartStopSequence() {
        return startStopSequence;
    }

    public void setStartStopSequence(int startStopSequence) {
        this.startStopSequence = startStopSequence;
    }

    public int getEndStopSequence() {
        return endStopSequence;
    }

    public void setEndStopSequence(int endStopSequence) {
        this.endStopSequence = endStopSequence;
    }

    public int getBigPricePercent() {
        return bigPricePercent;
    }

    public void setBigPricePercent(int bigPricePercent) {
        this.bigPricePercent = bigPricePercent;
    }

    public int getMidPricePercent() {
        return midPricePercent;
    }

    public void setMidPricePercent(int midPricePercent) {
        this.midPricePercent = midPricePercent;
    }

    public int getLitPricePercent() {
        return litPricePercent;
    }

    public void setLitPricePercent(int litPricePercent) {
        this.litPricePercent = litPricePercent;
    }

    public int getSitPricePercent() {
        return sitPricePercent;
    }

    public void setSitPricePercent(int sitPricePercent) {
        this.sitPricePercent = sitPricePercent;
    }
}
