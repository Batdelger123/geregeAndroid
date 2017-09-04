package metatech.mn.gerege.database;

/**
 * Created by Coder-Erdenebayar on 6/8/2017.
 */

public class PassengerType {
    //private variables
    private String title_mn;
    private String title_en;

    // constructor
    public PassengerType(){
        this.title_mn = "";
        this.title_en = "";
    }

    // constructor
    public PassengerType(String title_mn, String title_en){
        this.title_mn = title_mn;
        this.title_en = title_en;
    }

    public String getTitle_mn() {
        return title_mn;
    }

    public void setTitle_mn(String title_mn) {
        this.title_mn = title_mn;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }
}