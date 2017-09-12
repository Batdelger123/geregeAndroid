package metatech.mn.gerege.database;

import android.content.Context;

import java.util.List;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public class DBTariff implements DBTariffInterface {

    @Override
    public boolean addTariff(Context context, int id, int direction_id, String direction_name, int start_stop_id, String start_stop_name, int end_stop_id, String end_stop_name, int aimag_id, int end_aimag_id, int iscenter) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        databaseAccess.addTariff(new Tariff(id, direction_id, direction_name, start_stop_id, start_stop_name, end_stop_id, end_stop_name, aimag_id, end_aimag_id, iscenter));
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

    public List<Aimags> getEndStopAimags(Context context, String where, int startStopId) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        List<Aimags> aimagsList = databaseAccess.getEndStopAimags(where, startStopId);
        databaseAccess.close();
        return aimagsList;
    }

    public List<Aimags> getEndStopCity(Context context, String where, int startStopId) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        List<Aimags> aimagsList = databaseAccess.getEndStopCity(where, startStopId);
        databaseAccess.close();
        return aimagsList;
    }

    public List<Aimags> getEndStopForiegns(Context context, String where, int startStopId) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        List<Aimags> aimagsList = databaseAccess.getEndStopForiegnCountry(where, startStopId);
        databaseAccess.close();
        return aimagsList;
    }

    public List<Tariff> getEndStops(Context context, int startStopId) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        List<Tariff> tariffList = databaseAccess.getTariffEndStops(startStopId, false);
        databaseAccess.close();
        return tariffList;
    }
}