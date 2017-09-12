package metatech.mn.gerege.server;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

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
        Log.d("Tariff", "end");
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
                    new DBTariff().addTariff(DBUpdate.this, jsonChildNode.optInt("ID"), jsonChildNode.optInt("DIRECTION_ID"), jsonChildNode.optString("DIRECTION_NAME"), jsonChildNode.optInt("START_STOP_ID"), jsonChildNode.optString("START_STOP_NAME"), jsonChildNode.optInt("END_STOP_ID"),jsonChildNode.optString("END_STOP_NAME"), jsonChildNode.optInt("AIMAG_ID"), jsonChildNode.optInt("END_STOP_AIMAG_ID"), jsonChildNode.optInt("IS_CENTER"));
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
