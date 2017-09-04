package metatech.mn.gerege.database;

/**
 * Created by Coder-Erdenebayar on 8/23/2017.
 */

public class Aimags {

    //private variables
    private int    id;
    private String name;
    private int    bvs;
    private int    iscountry;

    // Empty constructor
    public Aimags(){
        id = 0;
        name = "";
        bvs = 0;
        iscountry = 0;
    }

    // constructor
    public Aimags(int id, String name, int bvs, int iscountry){
        this.id = id;
        this.name = name;
        this.bvs = bvs;
        this.iscountry  = iscountry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBvs() {
        return bvs;
    }

    public void setBvs(int bvs) {
        this.bvs = bvs;
    }

    public int getIscountry() {
        return iscountry;
    }

    public void setIscountry(int iscountry) {
        this.iscountry = iscountry;
    }
}
