package metatech.mn.gerege.database;

import android.content.Context;

import java.util.List;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public class DBTariff implements DBTariffInterface {

    @Override
    public boolean addTariff(Context context, int id, int direction_id, String direction_name, int start_stop_id, String start_stop_name, int end_stop_id, String end_stop_name, int aimag_id, int iscenter) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
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

}