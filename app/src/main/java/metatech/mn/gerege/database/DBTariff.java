package metatech.mn.gerege.database;

import android.content.Context;

import java.util.List;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public class DBTariff implements DBTariffInterface {

    @Override
    public boolean addTariff(Context context, Tariff tariff) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        databaseAccess.addTariff(tariff);
        databaseAccess.close();
        return false;
    }

    @Override
    public boolean deleteAllTariff(Context context) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        databaseAccess.deleteAllTariff();
        databaseAccess.close();
        return false;
    }

    @Override
    public List<Tariff> getTariff(Context context) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        List<Tariff> tariffList = databaseAccess.getTariff();
        databaseAccess.close();
        return tariffList;
    }

    public List<Tariff> getStartStops(Context context, Integer aimagId) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        List<Tariff> tariffList = databaseAccess.getStartStopTariffs(aimagId);
        databaseAccess.close();
        return tariffList;
    }

    public List<Tariff> getStopTariffs(Context context, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        List<Tariff> tariffList = databaseAccess.getStopTariffs(table, columns, selection, selectionArgs, groupBy, having);
        databaseAccess.close();
        return tariffList;
    }

}