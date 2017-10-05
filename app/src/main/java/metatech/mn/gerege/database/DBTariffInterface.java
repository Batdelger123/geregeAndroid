package metatech.mn.gerege.database;

import android.content.Context;

import java.util.List;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public interface DBTariffInterface {
    boolean     addTariff(Context context, Tariff tariff);
    boolean     deleteAllTariff(Context context);
    List<Tariff> getTariff(Context context);
}
