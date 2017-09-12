package metatech.mn.gerege.database;

import android.content.Context;

import java.util.List;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public interface DBAimagsInterface {
    boolean     addAimags(Context context, int id, String name, int bvs, int iscountry);
    boolean     deleteAllAimags(Context context);
    List<Aimags> getAimags(Context context);
    List<Aimags> getCity(Context context);
    List<Aimags> getForeignCountry(Context context);
}
