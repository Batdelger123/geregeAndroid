package metatech.mn.gerege.database;

/**
 * Created by Coder-Erdenebayar on 6/8/2017.
 */

public class User {
    //private variables
    private String  access_token;
    private String  userid;
    private String  pin;
    private boolean checked;

    // Empty constructor
    public User(){
        access_token = "";
        userid = "";
        pin = "";
        checked = false;
    }
    // constructor
    public User(String access_token, String userid, String pin, boolean checked){
        this.access_token = access_token;
        this.userid = userid;
        this.pin = pin;
        this.checked  = checked;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
