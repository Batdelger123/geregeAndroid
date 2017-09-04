package metatech.mn.gerege.database;

import android.content.Context;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public interface DatabaseUserInterface {

    boolean onCreateUser(Context context, String access_token, String userid, String pin, boolean checked);
    boolean deleteUser(Context context, String userid);
    User    getUser(Context context);
    void    getUnCheckedUserAll(Context context);
}
