package metatech.mn.gerege.server;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import metatech.mn.gerege.database.Aimags;
import metatech.mn.gerege.database.DBAimags;
import metatech.mn.gerege.database.DBTariff;
import metatech.mn.gerege.database.Tariff;
import metatech.mn.gerege.transdep.DirectionView;

/**
 * Created by Coder-Erdenebayar on 8/24/2017.
 */

public class DBUpdate extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initList();
        loadDataFromAssets();
        loadTariffDataFromAssets();
    }

    public void loadDataFromAssets () {
        String json = null;
        try {
            InputStream is = getAssets().open("aimags.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            new DBAimags().deleteAllAimags(DBUpdate.this);

            try{
                JSONObject jsonResponse = new JSONObject(json);
                JSONArray jsonMainNode = jsonResponse.optJSONArray("aimags");

                for(int i = 0; i<jsonMainNode.length();i++){
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    new DBAimags().addAimags(DBUpdate.this, jsonChildNode.optInt("ID"), jsonChildNode.optString("NAME"), jsonChildNode.optInt("BVS"), jsonChildNode.getInt("IS_COUNTRY"));
                }
            }
            catch(JSONException e){
                Toast.makeText(getApplicationContext(), "Error"+e.toString(), Toast.LENGTH_SHORT).show();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadTariffDataFromAssets () {
        String json = null;
        try {
            InputStream is = getAssets().open("tariff.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            new DBTariff().deleteAllTariff(DBUpdate.this);

            try{
                JSONObject jsonResponse = new JSONObject(json);
                JSONArray jsonMainNode = jsonResponse.optJSONArray("tariff");

                for(int i = 0; i<jsonMainNode.length();i++){
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                 //   new DBAimags().addAimags(DBUpdate.this, jsonChildNode.optInt("ID"), jsonChildNode.optString("NAME"), jsonChildNode.optInt("BVS"), jsonChildNode.getInt("IS_COUNTRY"));
                    new DBTariff().addTariff(
                            DBUpdate.this,
                            new Tariff(
                                    jsonChildNode.getInt("ID"),
                                    jsonChildNode.getInt("DIRECTION_ID"),
                                    jsonChildNode.getString("DIRECTION_NAME"),
                                    jsonChildNode.getInt("START_STOP_ID"),
                                    jsonChildNode.getString("START_STOP_NAME"),
                                    jsonChildNode.getInt("END_STOP_ID"),
                                    jsonChildNode.getString("END_STOP_NAME"),
                                    jsonChildNode.getInt("AIMAG_ID"),
                                    jsonChildNode.getInt("END_STOP_AIMAG_ID"),
                                    jsonChildNode.getInt("IS_CENTER"),
                                    jsonChildNode.optInt("BIG_PRICE", 0),
                                    jsonChildNode.optInt("BIG_CHILDPRICE", 0),
                                    jsonChildNode.optInt("BIG_INSURANCE", 0),
                                    jsonChildNode.optInt("BIG_CHILDINSURANCE", 0),
                                    jsonChildNode.optInt("MID_PRICE", 0),
                                    jsonChildNode.optInt("MID_CHILDPRICE", 0),
                                    jsonChildNode.optInt("MID_INSURANCE", 0),
                                    jsonChildNode.optInt("MID_CHILDINSURANCE", 0),
                                    jsonChildNode.optInt("LIT_PRICE", 0),
                                    jsonChildNode.optInt("LIT_CHILDPRICE", 0),
                                    jsonChildNode.optInt("LIT_INSURANCE", 0),
                                    jsonChildNode.optInt("LIT_CHILDINSURANCE", 0),
                                    jsonChildNode.optInt("SIT_PRICE", 0),
                                    jsonChildNode.optInt("SIT_CHILDPRICE", 0),
                                    jsonChildNode.optInt("SIT_INSURANCE", 0),
                                    jsonChildNode.optInt("SIT_CHILDINSURANCE", 0),
                                    jsonChildNode.getInt("TICKET_TYPE_ID"),
                                    jsonChildNode.optInt("START_STOP_SEQUENCE"),
                                    jsonChildNode.optInt("END_STOP_SEQUENCE"),
                                    jsonChildNode.getInt("BIG_PRICE_PERCENT"),
                                    jsonChildNode.getInt("MID_PRICE_PERCENT"),
                                    jsonChildNode.getInt("LIT_PRICE_PERCENT"),
                                    jsonChildNode.getInt("SIT_PRICE_PERCENT")
                            )
                    );
                }
            }
            catch(JSONException e){
                Toast.makeText(getApplicationContext(), "Error"+e.toString(), Toast.LENGTH_SHORT).show();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    List<Map<String,String>> countryList = new ArrayList<Map<String,String>>();
//    private void initList(){
//
//        try{
//            JSONObject jsonResponse = new JSONObject(localjsonString);
//            JSONArray jsonMainNode = jsonResponse.optJSONArray("aimags");
//            new DBAimags().deleteAllAimags(DBUpdate.this);
//            for(int i = 0; i<jsonMainNode.length();i++){
//                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
//                int id = jsonChildNode.optInt("ID");
//                String name = jsonChildNode.optString("NAME");
//                int bvs = jsonChildNode.optInt("BVS");
//                int iscountry = jsonChildNode.optInt("IS_COUNTRY");
//                String outPut = name + "--->" ;
//                //countryList.add(createEmployee("country", outPut));
//
//                new DBAimags().addAimags(DBUpdate.this, id, name, bvs, iscountry);
//            }
//
//            int s=0;
//        }
//        catch(JSONException e){
//            Toast.makeText(getApplicationContext(), "Error"+e.toString(), Toast.LENGTH_SHORT).show();
//        }
//    }


    private HashMap<String, String>createEmployee(String name,String number){
        HashMap<String, String> employeeNameNo = new HashMap<String, String>();
        employeeNameNo.put(name, number);
        return employeeNameNo;
    }
}
