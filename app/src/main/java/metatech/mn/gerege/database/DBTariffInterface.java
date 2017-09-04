package metatech.mn.gerege.database;

import android.content.Context;

import java.util.List;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public interface DBTariffInterface {
    boolean     addTariff(Context context, int id, int direction_id, String direction_name, int start_stop_id, String start_stop_name, int end_stop_id, String end_stop_name, int aimag_id, int iscenter);
    boolean     deleteAllTariff(Context context);
    List<Tariff> getTariff(Context context);
}
