package metatech.mn.gerege.database;

import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public class DatabaseUser implements DatabaseUserInterface {

    @Override
    public boolean onCreateUser(Context context,String access_token, String userid, String pin, boolean checked) {
        //TODO- dsgsdfg
        // Inserting Row
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        databaseAccess.addUser(new User(access_token, userid, pin, checked));
        databaseAccess.close();
        return true;
    }

    @Override
    public boolean deleteUser(Context context,String usercode) {
        // Delete Row
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        databaseAccess.deleteAllStops();
        databaseAccess.close();
        return true;
    }

    @Override
    public User getUser(Context context) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        User user = databaseAccess.getUser();
        databaseAccess.close();
        return user;
    }

    @Override
    public void getUnCheckedUserAll(Context context) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        List<User> users=databaseAccess.getUsers();
        for (int i=0;i < users.size();i++)
        {
            if(users.get(i).isChecked() == true) {
                users.get(i).setChecked(false);
               databaseAccess.updateUser(users.get(i));
            }
        }

        databaseAccess.close();
    }
}
