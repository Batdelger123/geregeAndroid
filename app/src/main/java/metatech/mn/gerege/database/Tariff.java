package metatech.mn.gerege.database;

/**
 * Created by Coder-Erdenebayar on 8/23/2017.
 */

public class Tariff {
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

    // Empty constructor
    public Tariff(){
        id = 0;
        direction_id = 0;
        direction_name = "";
        start_stop_id = 0;
        start_stop_name = "";
        end_stop_id = 0;
        end_stop_name = "";
        aimag_id = 0;
        iscenter = 0;
    }

    // constructor
    public Tariff(int id, int direction_id, String direction_name, int start_stop_id, String start_stop_name, int end_stop_id, String end_stop_name, int aimag_id, int end_aimag_id, int iscenter){
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
}
