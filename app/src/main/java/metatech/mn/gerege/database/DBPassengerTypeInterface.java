package metatech.mn.gerege.database;

import android.content.Context;

import java.util.List;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public interface DBPassengerTypeInterface {
    boolean     onCreatePassengerType(Context context, String title_mn, String title_en);
    boolean     deleteAllPassengerType(Context context);
    List<PassengerType> getPassengerType(Context context);
}
