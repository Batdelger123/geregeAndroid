package metatech.mn.gerege.database;

import android.content.Context;

import java.util.List;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public class DBPassengerType implements DBPassengerTypeInterface {

    @Override
    public boolean onCreatePassengerType(Context context, String title_mn,String title_en) {
        //TODO-databse create
        // Inserting Row
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        databaseAccess.addPassengerType(new PassengerType(title_mn, title_en));
        databaseAccess.close();
        return true;
    }

    @Override
    public boolean deleteAllPassengerType(Context context) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        databaseAccess.deleteAllPassengerType();
        databaseAccess.close();
        return true;
    }

    @Override
    public List<PassengerType> getPassengerType(Context context) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        List<PassengerType> passengerTypes=databaseAccess.getPassengerType();
        databaseAccess.close();
        return passengerTypes;
    }
}