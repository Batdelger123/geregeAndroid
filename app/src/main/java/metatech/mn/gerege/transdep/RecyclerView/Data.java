package metatech.mn.gerege.transdep.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import metatech.mn.gerege.database.DBAimags;
import metatech.mn.gerege.database.Tariff;
import metatech.mn.gerege.server.DBUpdate;

/**
 * Created by Enkhtur on 9/13/2017.
 */

public class Data {

    private String SERVER_URL = "http://metakioskdb.intelmax.mn/ords/pdb1/testuser1/reference/avail_dispatchers";
    private Context context;
    private Tariff tariff;
    private String date;
    private int countTicket;

    public Data(Context context, Tariff tariff, String date, int countTicket) {
        this.context = context;
        this.tariff = tariff;
        this.date = date;
        this.countTicket = countTicket;
    }

    public List<ListItem> getDataList() {
        final List<ListItem> listItems = new ArrayList();

        try {
            final HashMap<String, String> requestProperties = new HashMap();
            requestProperties.put("Content-Type", "application/json");
            requestProperties.put("Authorization", "Bearer ku1T_DmelLW9UR36L_3GMQ..");

            Log.d("Direction_Id", String.valueOf(tariff.getDirection_id()));
            Log.d("Direction_name", tariff.getDirection_name());

            date = date.replace("/", "-");
            JSONObject json = new JSONObject();
            json.put("direction_id", tariff.getDirection_id());
            json.put("machine_id", 0);
            json.put("date1", date + "T00:00:00Z");
            json.put("date2", date + "T23:59:59Z");
            json.put("ticket_count", countTicket);

            Log.d("REQUEST", "1");
            String s = "aaa";
            Log.d("String-1", s);
            s = new CustomRequest(SERVER_URL, "POST", requestProperties, json.toString()).execute().get();

            Log.d("Result", s);
            JSONObject jsonResponse = new JSONObject(s);
            JSONArray jsonDispatcherArray = jsonResponse.optJSONArray("avail_dispatchers");
            for(int i = 0; i<jsonDispatcherArray.length();i++){
                JSONObject jsonDispatcher = jsonDispatcherArray.getJSONObject(i);
                if (jsonDispatcher.optInt("DIRECTION_START_STOP_ID") == tariff.getStart_stop_id() && jsonDispatcher.optInt("DIRECTION_END_STOP_ID") == tariff.getEnd_stop_id()) {

                    Log.d("DATATATA", jsonDispatcher.optString("DIRECTION_START_STOP_NAME"));
                    Log.d("DATATATA", jsonDispatcher.optString("DIRECTION_END_STOP_NAME"));
                    Log.d("DATATATA", jsonDispatcher.optString("LEAVE_DATE").replace("T", ", ").replace("Z", ""));
                    Log.d("DATATATA",  jsonDispatcher.optString("CAR_TYPE_NAME"));

                    String string = jsonDispatcher.optString("DIRECTION_END_STOP_NAME");
                    listItems.add(new ListItem(
                            jsonDispatcher.optString("DIRECTION_START_STOP_NAME"),
                            jsonDispatcher.optString("DIRECTION_END_STOP_NAME"),
                            jsonDispatcher.optString("LEAVE_DATE").replace("T", ", ").replace("Z", ""),
                            string.substring(string.indexOf('.') + 1),
                            jsonDispatcher.optString("CAR_TYPE_NAME"),
                            jsonDispatcher.optInt("SITCOUNT")
                    ));
                }
            }

        } catch (Exception e) {

        }
//        List<ListItem> listItems = new ArrayList();
//        // String date, String stop, String busType, int countSeat
//        String json = null;
//        try {
//            InputStream inputStream = context.getAssets().open("direction.json");
//            int size = inputStream.available();
//            byte[] buffer = new byte[size];
//            inputStream.read(buffer);
//            inputStream.close();
//            json = new String(buffer, "UTF-8");
//
//            try{
//                JSONObject jsonResponse = new JSONObject(json);
//                JSONArray jsonDispatcherArray = jsonResponse.optJSONArray("avail_dispatchers");
//
//                for(int i = 0; i < jsonDispatcherArray.length(); i++){
//                    JSONObject jsonDispatcher = jsonDispatcherArray.getJSONObject(i);
//
//                    if (
//                        jsonDispatcher.optInt("DIRECTION_START_STOP_ID") == tariff.getStart_stop_id() &&
//                        jsonDispatcher.optInt("DIRECTION_END_STOP_ID") == tariff.getEnd_stop_id()
//                    ) {
//                        String from = jsonDispatcher.optString("DIRECTION_START_STOP_NAME");
//                        String to = jsonDispatcher.optString("DIRECTION_END_STOP_NAME");
//                        String dateAndTime = jsonDispatcher.optString("DIRECTION_START_STOP_NAME");
//                        String parking = jsonDispatcher.optString("LEAVE_DATE");
//                        String busType = jsonDispatcher.optString("CAR_TYPE_NAME");
//                        int noSeat = jsonDispatcher.optInt("SITCOUNT");
//                        listItems.add(new ListItem(from, to, dateAndTime, parking, busType, noSeat));
//                    }
//                }
//
//            }
//            catch(JSONException e){
//                Toast.makeText(context, "Error"+e.toString(), Toast.LENGTH_SHORT).show();
//            }
//
//        } catch (IOException ex) {
//            Toast.makeText(context, "Error"+ex.toString(), Toast.LENGTH_SHORT).show();
//        }
//
        return listItems;
    }
}
