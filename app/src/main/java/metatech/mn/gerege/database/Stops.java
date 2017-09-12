package metatech.mn.gerege.database;

/**
 * Created by Coder-Erdenebayar on 8/23/2017.
 */

public class Stops {
    private int    id;
    private String name;
    private int    iscenter;
    private boolean isUB;

    public Stops (){
        id = 0;
        name = "";
        iscenter = 0;
    }

    public Stops (int id, String name, int iscenter){
        this.id = id;
        this.name = name;
        this.iscenter = iscenter;
    }

    public Stops (int id, String name, boolean isUB){
        this.id = id;
        this.name = name;
        this.isUB = isUB;
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

    public int getIscenter() {
        return iscenter;
    }

    public void setIscenter(int iscenter) {
        this.iscenter = iscenter;
    }

    public boolean isUB() {
        return isUB;
    }

    public void setUB(boolean UB) {
        isUB = UB;
    }
}
