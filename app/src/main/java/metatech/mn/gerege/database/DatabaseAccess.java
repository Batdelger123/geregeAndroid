package metatech.mn.gerege.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coder-Erdenebayar on 6/8/2017.
 */

public class DatabaseAccess  {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    private String USERTABLE  = "user";
    private String PASSENGERTYPETABLE = "passenger_type";
    public static final String AIMAGSTABLE = "aimags";
    private String STOPSTABLE = "stops";
    public static final String TARIFFTABLE = "tariff";

    //Basic vale
    private static final String ID = "id";
    private static final String NAME = "name";


    // Contacts Table Columns names
    private static final String USER_TOKEN = "access_token";
    private static final String USER_USERCODE = "userid";
    private static final String USER_PASSWORD = "pin";
    private static final String USER_CHECKED = "checked";

    // PassengerType Table Column names
    private static final String TITLE_MN = "title_mn";
    private static final String TITLE_EN = "title_en";

    // Aimags Table Column names
    public static final String BVS = "bvs";
    public static final String ISCOUNTRY = "iscountry";

    // TARIFF Table Column names
    public static final String DIRECTION_ID = "direction_id";
    public static final String DIRECTION_NAME = "direction_name";
    public static final String START_STOP_ID = "start_stop_id";
    public static final String START_STOP_NAME = "start_stop_name";
    public static final String END_STOP_ID = "end_stop_id";
    public static final String END_STOP_NAME = "end_stop_name";
    public static final String AIMAG_ID = "aimag_id";
    public static final String ISCENTER = "iscenter";
    public static final String END_STOP_AIMAG_ID = "end_stop_aimag_id";

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }
    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    // Adding new user
    public void addUser(User user) {
        deleteAllUser();
        ContentValues values = new ContentValues();
        values.put(USER_TOKEN, user.getAccess_token()); // Userid
        values.put(USER_USERCODE, user.getUserid()); // Userid
        values.put(USER_PASSWORD, user.getPin()); // Pin
        values.put(USER_CHECKED, user.isChecked()); // Checked
        // Inserting Row
        database.insert(USERTABLE, null, values);
    }

    //delete all user
    public void deleteAllUser() {
        database.delete(USERTABLE, null, null);
    }

    // Updating single contact
    public int updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put(USER_USERCODE, user.getUserid()); // Userid
        values.put(USER_PASSWORD, user.getPin()); // Pin
        values.put(USER_CHECKED, user.isChecked());// Checked
        String where = USER_USERCODE + "=" + user.getUserid();
        int r = database.update(USERTABLE, values, where, null);
        return r;
    }

    public boolean checkUser(User user) {

        Cursor res = database.rawQuery("SELECT * FROM "+USERTABLE+" WHERE "+USER_USERCODE+" = ? AND "+USER_PASSWORD+" = ?", new String[] {user.getUserid(), user.getPin()});
        if (res.getCount() > 0) {
            res.moveToFirst();
            res.close();
            return true;
        }
        else{
            res.close();
            return false;
        }
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<User>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+USERTABLE, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User users = new User();
            users.setAccess_token(cursor.getString(0));
            users.setUserid(cursor.getString(1));
            users.setPin(cursor.getString(2));
            users.setChecked(Boolean.getBoolean(cursor.getString(3)));
            // Adding contact to list
            userList.add(users);
            cursor.moveToNext();
        }
        cursor.close();
        return userList;
    }

    public User getUser() {
        Cursor cursor = database.rawQuery( "SELECT * FROM "+USERTABLE, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(cursor.getInt(3) == 1) {
                User user1 = new User(cursor.getString(0),cursor.getString(1),cursor.getString(2),Boolean.getBoolean(cursor.getString(3)));
                return user1;
            }
            cursor.moveToNext();
        }
        cursor.close();
        return null;

    }

    // Adding new passengerType
    public void addPassengerType(PassengerType passengerType) {
        ContentValues values = new ContentValues();
        values.put(TITLE_MN, passengerType.getTitle_mn()); // title_mn
        values.put(TITLE_EN, passengerType.getTitle_en()); // title_en

        // Inserting Row
        database.insert(PASSENGERTYPETABLE, null, values);
    }

    //delete all passengerType
    public void deleteAllPassengerType() {
        database.delete(PASSENGERTYPETABLE, null, null);
    }

    //get all passengerType
    public List<PassengerType> getPassengerType() {
        List<PassengerType> passengerTypesList = new ArrayList<PassengerType>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+PASSENGERTYPETABLE, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            PassengerType passengerType = new PassengerType();
            passengerType.setTitle_mn(cursor.getString(1));
            passengerType.setTitle_en(cursor.getString(2));
            // Adding contact to list
            passengerTypesList.add(passengerType);
            cursor.moveToNext();
        }
        cursor.close();
        return passengerTypesList;
    }

    // Adding new aimags
    public void addAimags(Aimags aimags) {
        deleteAllUser();
        ContentValues values = new ContentValues();
        values.put(ID, aimags.getId());
        values.put(NAME, aimags.getName());
        values.put(BVS, aimags.getBvs());
        values.put(ISCOUNTRY, aimags.getIscountry());

        // Inserting Row
        database.insert(AIMAGSTABLE, null, values);
    }

    // Get Aimags
    public List<Aimags> getAimags() {

        List<Aimags> aimagsList = new ArrayList<Aimags>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+AIMAGSTABLE + " WHERE " + ISCOUNTRY + "==1"+" AND "+BVS+"<4", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Aimags aimags = new Aimags();
            aimags.setId(cursor.getInt(0));
            aimags.setName(cursor.getString(1));
            aimags.setBvs(cursor.getInt(2));
            aimags.setIscountry(cursor.getInt(3));
            aimagsList.add(aimags);
            cursor.moveToNext();
        }
        cursor.close();
        return aimagsList;
    }

    public List<Aimags> getEndAimags(String query, String[] args) {

        List<Aimags> aimagsList = new ArrayList<Aimags>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+AIMAGSTABLE + " WHERE " + ISCOUNTRY + "==1"+" AND "+BVS+"<4", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Aimags aimags = new Aimags();
            aimags.setId(cursor.getInt(0));
            aimags.setName(cursor.getString(1));
            aimags.setBvs(cursor.getInt(2));
            aimags.setIscountry(cursor.getInt(3));
            aimagsList.add(aimags);
            cursor.moveToNext();
        }
        cursor.close();
        return aimagsList;
    }

    // Get City
    public List<Aimags> getCity() {

        List<Aimags> cityList = new ArrayList<Aimags>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+AIMAGSTABLE+ " WHERE "+ISCOUNTRY+"==0", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Aimags aimags = new Aimags();
            aimags.setId(cursor.getInt(0));
            aimags.setName(cursor.getString(1));
            aimags.setBvs(cursor.getInt(2));
            aimags.setIscountry(cursor.getInt(3));
            cityList.add(aimags);
            cursor.moveToNext();
        }
        cursor.close();
        return cityList;
    }

    // Get ForiegnCountry
    public List<Aimags>  getForiegnCountry() {

        List<Aimags> aimagsList = new ArrayList<Aimags>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+AIMAGSTABLE+" WHERE "+BVS+">=4 AND "+ISCOUNTRY+"==1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Aimags aimags = new Aimags();
            aimags.setId(cursor.getInt(0));
            aimags.setName(cursor.getString(1));
            aimags.setBvs(cursor.getInt(2));
            aimags.setIscountry(cursor.getInt(3));
            aimagsList.add(aimags);
            cursor.moveToNext();
        }
        cursor.close();
        return aimagsList;
    }
    //delete all aimags
    public void deleteAllAimags() {
        database.delete(AIMAGSTABLE, null, null);
    }

    // Adding new stops
    public void addStops(Stops stops) {
        deleteAllUser();
        ContentValues values = new ContentValues();
        values.put(ID, stops.getId());
        values.put(NAME, stops.getName());
        values.put(ISCENTER, stops.getIscenter());

        // Inserting Row
        database.insert(STOPSTABLE, null, values);
    }

    // Get Stops
    public List<Stops> getStops(int aimag_id) {

        List<Stops> stopsList = new ArrayList<Stops>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+STOPSTABLE+ " WHERE "+AIMAG_ID+"=="+aimag_id, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Stops stops = new Stops();
            stops.setId(cursor.getInt(0));
            stops.setName(cursor.getString(1));
            stops.setIscenter(cursor.getInt(2));
            cursor.moveToNext();
        }
        cursor.close();
        return stopsList;
    }

    //delete all stops
    public void deleteAllStops() {
        database.delete(STOPSTABLE, null, null);
    }

    // Adding new tariff
    public void addTariff(Tariff tariff) {
        deleteAllUser();
        ContentValues values = new ContentValues();
        values.put(ID, tariff.getId());
        values.put(DIRECTION_ID, tariff.getDirection_id());
        values.put(DIRECTION_NAME, tariff.getDirection_name());
        values.put(START_STOP_ID, tariff.getStart_stop_id());
        values.put(START_STOP_NAME, tariff.getStart_stop_name());
        values.put(END_STOP_ID, tariff.getEnd_stop_id());
        values.put(END_STOP_NAME, tariff.getEnd_stop_name());
        values.put(AIMAG_ID, tariff.getAimag_id());
        values.put(END_STOP_AIMAG_ID, tariff.getEnd_aimag_id());
        values.put(ISCENTER, tariff.getIscenter());

        // Inserting Row
        database.insert(TARIFFTABLE, null, values);
    }

    // Get Stops
    public List<Tariff> getTariff() {
//TODO getTariff
        List<Tariff> stopsList = new ArrayList<Tariff>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+STOPSTABLE+ " WHERE "+AIMAG_ID+"==", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Stops stops = new Stops();
            stops.setId(cursor.getInt(0));
            stops.setName(cursor.getString(1));
            stops.setIscenter(cursor.getInt(2));
            cursor.moveToNext();
        }
        cursor.close();
        return stopsList;
    }

    //delete all stops
    public void deleteAllTariff() {
        database.delete(TARIFFTABLE, null, null);
    }

    public List<Aimags> getAimags(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having) {
        List<Aimags> aimagsList = new ArrayList<Aimags>();

        Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Aimags aimags = new Aimags();
            aimags.setId(cursor.getInt(0));
            aimags.setName(cursor.getString(1));
            aimags.setBvs(cursor.getInt(2));
            aimags.setIscountry(cursor.getInt(3));
            aimagsList.add(aimags);
            cursor.moveToNext();
        }
        cursor.close();
        return aimagsList;
    }

    // start_stop  set
    public List<Tariff> getStartStopTariffs (Integer aimagId) {
        List<Tariff> stopsList = new ArrayList<Tariff>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+TARIFFTABLE+ " GROUP BY start_stop_id HAVING " + AIMAG_ID + "= ? ", new String[]{ String.valueOf(aimagId)});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tariff tariff = new Tariff(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9));
            stopsList.add(tariff);
            cursor.moveToNext();
        }
        cursor.close();
        return stopsList;
    }

    // stop  set
    public List<Tariff> getStopTariffs (String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having) {
        List<Tariff> stopsList = new ArrayList<Tariff>();

        Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tariff tariff = new Tariff(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9));
            stopsList.add(tariff);
            cursor.moveToNext();
        }
        cursor.close();
        return stopsList;
    }

    // end_stop_aimag set

    public List<Aimags> getEndStopAimags (String where, int startStopId) {
        List<Aimags> stopsList = new ArrayList<Aimags>();
        Log.d("QUERY", "SELECT aimags.* FROM "+TARIFFTABLE+" JOIN "+AIMAGSTABLE+" ON aimags.id = tariff.end_stop_aimag_id GROUP BY "+END_STOP_AIMAG_ID+" HAVING "+ where +" = " + startStopId);
//        Cursor cursor = database.rawQuery("SELECT aimags.* FROM " + TARIFFTABLE +
//                " JOIN " + AIMAGSTABLE +
//                " ON " + AIMAGSTABLE + ".id = " + TARIFFTABLE + ".end_stop_aimag_id " +
//                "WHERE " + where + " = ? AND " + ISCOUNTRY + " = 1 AND " + BVS + " < 4 " +
//                "GROUP BY " + END_STOP_AIMAG_ID,
//                new String[]{ String.valueOf(startStopId)}
//        );
//       database.query(TARIFFTABLE, new String[] {"aimgas.*"}, where + " = ? AND " + ISCOUNTRY + " = 1 AND " + BVS + " < 4 ", )
        Cursor cursor = database.query(TARIFFTABLE, new String[]  {"aimags.*"}, where + " = ? AND " + ISCOUNTRY + " = 1 AND " + BVS + " < 4 ", new String[] {String.valueOf(startStopId)}, END_STOP_AIMAG_ID, null, null);

        cursor.moveToFirst();
        Log.d("Length", String.valueOf(cursor.getCount()));

        while (!cursor.isAfterLast()) {
            Aimags aimags = new Aimags(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3));
            Log.d("DATA", "" + cursor.getInt(0));
            Log.d("DATA", "" + cursor.getString(1));
            Log.d("DATA", "" + cursor.getInt(2));
            Log.d("DATA", "" + cursor.getString(3) + "\n________________________________");
            stopsList.add(aimags);
            cursor.moveToNext();
        }
        cursor.close();
        return stopsList;
    }

    // end_stop_city set
    public List<Aimags> getEndStopCity (String where, int startStopId) {
        List<Aimags> stopsList = new ArrayList<Aimags>();
        Log.d("QUERY", "SELECT aimags.* FROM " + TARIFFTABLE +
                " JOIN " + AIMAGSTABLE +
                " ON " + AIMAGSTABLE + ".id = " + TARIFFTABLE + ".end_stop_aimag_id " +
                "WHERE " + where + " = "+startStopId+" AND " + ISCOUNTRY + " = 0 AND " + BVS + " < 4 " +
                "GROUP BY " + END_STOP_AIMAG_ID);
        Cursor cursor = database.rawQuery("SELECT aimags.* FROM " + TARIFFTABLE +
                        " JOIN " + AIMAGSTABLE +
                        " ON " + AIMAGSTABLE + ".id = " + TARIFFTABLE + ".end_stop_aimag_id " +
                        "WHERE " + where + " = ? AND " + ISCOUNTRY + " = 0 AND " + BVS + " < 4 " +
                        "GROUP BY " + END_STOP_AIMAG_ID,
                new String[]{ String.valueOf(startStopId)}
        );
        cursor.moveToFirst();
        Log.d("Length", String.valueOf(cursor.getCount()));

        while (!cursor.isAfterLast()) {
            Aimags aimags = new Aimags(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3));
            Log.d("DATA", "" + cursor.getInt(0));
            Log.d("DATA", "" + cursor.getString(1));
            Log.d("DATA", "" + cursor.getInt(2));
            Log.d("DATA", "" + cursor.getString(3) + "\n________________________________");
            stopsList.add(aimags);
            cursor.moveToNext();
        }
        cursor.close();
        return stopsList;
    }

    // end_stop_city set
    public List<Aimags> getEndStopForiegnCountry(String where, int startStopId) {
        List<Aimags> stopsList = new ArrayList<Aimags>();
        Log.d("QUERY", "SELECT aimags.* FROM "+TARIFFTABLE+" JOIN "+AIMAGSTABLE+" ON aimags.id = tariff.end_stop_aimag_id GROUP BY "+END_STOP_AIMAG_ID+" HAVING "+ where +" = " + startStopId);
        Cursor cursor = database.rawQuery("SELECT aimags.* FROM " + TARIFFTABLE +
                        " JOIN " + AIMAGSTABLE +
                        " ON " + AIMAGSTABLE + ".id = " + TARIFFTABLE + ".end_stop_aimag_id " +
                        "WHERE " + where + " = ? AND " + ISCOUNTRY + " = 1 AND " + BVS + " >= 4 " +
                        "GROUP BY " + END_STOP_AIMAG_ID,
                new String[]{ String.valueOf(startStopId)}
        );
        cursor.moveToFirst();
        Log.d("Length", String.valueOf(cursor.getCount()));

        while (!cursor.isAfterLast()) {
            Aimags aimags = new Aimags(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3));
            Log.d("DATA", "" + cursor.getInt(0));
            Log.d("DATA", "" + cursor.getString(1));
            Log.d("DATA", "" + cursor.getInt(2));
            Log.d("DATA", "" + cursor.getString(3) + "\n________________________________");
            stopsList.add(aimags);
            cursor.moveToNext();
        }
        cursor.close();
        return stopsList;
    }

    // end_stop_city set
    public List<Tariff> getTariffEndStops(int startId, boolean isUB) {
        List<Tariff> stopsList = new ArrayList<Tariff>();
        String query = "SELECT * FROM " + TARIFFTABLE;
//        Log.d("QUERY", "SELECT * FROM "+TARIFFTABLE+" GROUP BY "+END_STOP_AIMAG_ID+" HAVING "+ where +" = " + startStopId);
        Cursor cursor = database.rawQuery("SELECT * FROM " + TARIFFTABLE + " WHERE GROUP BY " + END_STOP_AIMAG_ID, new String[]{ String.valueOf(startId)} );

        cursor.moveToFirst();
        Log.d("Length", String.valueOf(cursor.getCount()));

        while (!cursor.isAfterLast()) {
            Tariff tariff = new Tariff();
            Log.d("DATA", "" + cursor.getInt(0));
            Log.d("DATA", "" + cursor.getString(1));
            Log.d("DATA", "" + cursor.getInt(2));
            Log.d("DATA", "" + cursor.getString(3) + "\n________________________________");
            stopsList.add(tariff);
            cursor.moveToNext();
        }
        cursor.close();
        return stopsList;
    }
}