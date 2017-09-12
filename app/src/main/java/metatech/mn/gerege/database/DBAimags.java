package metatech.mn.gerege.database;

import android.content.Context;

import java.util.List;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public class DBAimags implements DBAimagsInterface {

    @Override
    public boolean addAimags(Context context, int id, String name, int bvs, int iscountry) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        databaseAccess.addAimags(new Aimags(id, name, bvs, iscountry));
        databaseAccess.close();
        return false;
    }

    @Override
    public boolean deleteAllAimags(Context context) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        databaseAccess.deleteAllAimags();
        databaseAccess.close();
        return false;
    }

    @Override
    public List<Aimags> getAimags(Context context) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        List<Aimags> aimags = databaseAccess.getAimags();
        databaseAccess.close();
        return aimags;
    }

    @Override
    public List<Aimags> getCity(Context context) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        List<Aimags> aimags = databaseAccess.getCity();
        databaseAccess.close();
        return aimags;
    }

    @Override
    public List<Aimags> getForeignCountry(Context context) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        List<Aimags> aimags = databaseAccess.getForiegnCountry();
        databaseAccess.close();
        return aimags;
    }

    public List<Aimags> getAimags(Context context, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        List<Aimags> aimags = databaseAccess.getAimags(table, columns, selection, selectionArgs, groupBy, having);
        databaseAccess.close();
        return aimags;
    }

    public List<Aimags> getEndAimags(Context context, String query, String args[]) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        List<Aimags> aimags = databaseAccess.getEndAimags(query, args);
        databaseAccess.close();
        return aimags;
    }
}