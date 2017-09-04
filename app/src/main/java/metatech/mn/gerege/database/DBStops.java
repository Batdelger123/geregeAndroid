package metatech.mn.gerege.database;

import android.content.Context;

import java.util.List;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public class DBStops implements DBStopsInterface {

    @Override
    public boolean addStops(Context context, int id, String name, int iscenter) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        databaseAccess.addStops(new Stops(id, name, iscenter));
        databaseAccess.close();
        return true;
    }

    @Override
    public boolean deleteAllStops(Context context) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        databaseAccess.deleteAllStops();
        databaseAccess.close();
        return true;
    }

    @Override
    public List<Stops> getStops(Context context, int aimag_id) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        List<Stops> stopsList = databaseAccess.getStops(aimag_id);
        databaseAccess.close();
        return stopsList;
    }
}