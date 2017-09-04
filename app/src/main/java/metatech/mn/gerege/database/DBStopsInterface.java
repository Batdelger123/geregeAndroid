package metatech.mn.gerege.database;

import android.content.Context;

import java.util.List;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public interface DBStopsInterface {
    boolean     addStops(Context context, int id, String name, int iscenter);
    boolean     deleteAllStops(Context context);
    List<Stops> getStops(Context context, int aimag_id);
}
