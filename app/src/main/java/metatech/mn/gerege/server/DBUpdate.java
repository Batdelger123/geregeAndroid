package metatech.mn.gerege.server;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import metatech.mn.gerege.database.DBAimags;
import metatech.mn.gerege.transdep.DirectionView;

/**
 * Created by Coder-Erdenebayar on 8/24/2017.
 */

public class DBUpdate extends Activity {

    String SERVER_URL = "http://49.0.223.7:8080/ords/metakioskdb/testuser1/";
    private String localjsonString = "{\n" +
            "\"version\": \"2017-08-17T19:21:10Z\",\n" +
            "\"aimags\": [\n" +
            "  {\n" +
            "\"ID\": 1,\n" +
            "\"NAME\": \"????????\",\n" +
            "\"BVS\": 1,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 2,\n" +
            "\"NAME\": \"????-?????\",\n" +
            "\"BVS\": 3,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 3,\n" +
            "\"NAME\": \"??????????\",\n" +
            "\"BVS\": 3,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 4,\n" +
            "\"NAME\": \"??????\",\n" +
            "\"BVS\": 1,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 5,\n" +
            "\"NAME\": \"????-?????\",\n" +
            "\"BVS\": 3,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 6,\n" +
            "\"NAME\": \"?????????\",\n" +
            "\"BVS\": 1,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 7,\n" +
            "\"NAME\": \"??????\",\n" +
            "\"BVS\": 2,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 8,\n" +
            "\"NAME\": \"????????\",\n" +
            "\"BVS\": 1,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 9,\n" +
            "\"NAME\": \"??????\",\n" +
            "\"BVS\": 3,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 10,\n" +
            "\"NAME\": \"??????????\",\n" +
            "\"BVS\": 1,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 11,\n" +
            "\"NAME\": \"????????\",\n" +
            "\"BVS\": 1,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 12,\n" +
            "\"NAME\": \"?????????\",\n" +
            "\"BVS\": 2,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 13,\n" +
            "\"NAME\": \"???????\",\n" +
            "\"BVS\": 1,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 14,\n" +
            "\"NAME\": \"???\",\n" +
            "\"BVS\": 1,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 15,\n" +
            "\"NAME\": \"???\",\n" +
            "\"BVS\": 3,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 16,\n" +
            "\"NAME\": \"????\",\n" +
            "\"BVS\": 3,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 17,\n" +
            "\"NAME\": \"???????\",\n" +
            "\"BVS\": 3,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 18,\n" +
            "\"NAME\": \"??????\",\n" +
            "\"BVS\": 2,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 19,\n" +
            "\"NAME\": \"??????-???\",\n" +
            "\"BVS\": 1,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 20,\n" +
            "\"NAME\": \"?????\",\n" +
            "\"BVS\": 1,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 21,\n" +
            "\"NAME\": \"??????????\",\n" +
            "\"BVS\": 1,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 22,\n" +
            "\"NAME\": \"???????????\",\n" +
            "\"BVS\": 1,\n" +
            "\"IS_COUNTRY\": 0\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 23,\n" +
            "\"NAME\": \"????????\",\n" +
            "\"BVS\": 1,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 24,\n" +
            "\"NAME\": \"??????????\",\n" +
            "\"BVS\": 1,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 25,\n" +
            "\"NAME\": \"???\",\n" +
            "\"BVS\": 4,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 26,\n" +
            "\"NAME\": \"?????\",\n" +
            "\"BVS\": 5,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "},\n" +
            "  {\n" +
            "\"ID\": 30,\n" +
            "\"NAME\": \"??????\",\n" +
            "\"BVS\": 6,\n" +
            "\"IS_COUNTRY\": 1\n" +
            "}\n" +
            "]\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initList();



/*



        String json = null;
        try {
            InputStream is = getAssets().open("aimags.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            System.out.println(json);
            List<Aimags> aimagsList = new ArrayList<Aimags>();
            try{
                JSONObject jsonResponse = new JSONObject(json);
                JSONArray jsonMainNode = jsonResponse.optJSONArray("aimags");

                for(int i = 0; i<jsonMainNode.length();i++){
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    Aimags aimags = new Aimags();
                    aimags.setId(jsonChildNode.optInt("ID"));
                    aimags.setName(jsonChildNode.optString("NAME"));
                    aimags.setBvs(jsonChildNode.optInt("BVS"));
                    aimags.setIscountry(jsonChildNode.getInt("IS_COUNTRY"));
                    aimagsList.add(aimags);
                }
            }
            catch(JSONException e){
                Toast.makeText(getApplicationContext(), "Error"+e.toString(), Toast.LENGTH_SHORT).show();
            }

            int s=0;


        } catch (IOException ex) {
            ex.printStackTrace();
        }
*/

    }


    List<Map<String,String>> countryList = new ArrayList<Map<String,String>>();
    private void initList(){

        try{
            JSONObject jsonResponse = new JSONObject(localjsonString);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("aimags");
            new DBAimags().deleteAllAimags(DBUpdate.this);
            for(int i = 0; i<jsonMainNode.length();i++){
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                int id = jsonChildNode.optInt("ID");
                String name = jsonChildNode.optString("NAME");
                int bvs = jsonChildNode.optInt("BVS");
                int iscountry = jsonChildNode.optInt("IS_COUNTRY");
                String outPut = name + "--->" ;
                //countryList.add(createEmployee("country", outPut));

                new DBAimags().addAimags(DBUpdate.this, id, name, bvs, iscountry);
            }

            int s=0;
        }
        catch(JSONException e){
            Toast.makeText(getApplicationContext(), "Error"+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    private HashMap<String, String>createEmployee(String name,String number){
        HashMap<String, String> employeeNameNo = new HashMap<String, String>();
        employeeNameNo.put(name, number);
        return employeeNameNo;
    }
}
