package metatech.mn.gerege.database;

/**
 * Created by Coder-Erdenebayar on 8/23/2017.
 */

public class Stops {
    private int    id;
    private String name;
    private int    iscenter;

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
}
