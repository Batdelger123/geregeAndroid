package metatech.mn.gerege.transdep.RecyclerView.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import metatech.mn.gerege.database.Tariff;
import metatech.mn.gerege.transdep.RecyclerView.SingletonRequestQueue;

/**
 * Created by Enkhtur on 9/13/2017.
 */

public class Data implements Response.ErrorListener{

    private Context context;
    private String url;
    private String authorization;
    private Tariff tariff;
    private String date;
    private int countTicket;

    public Data(Context context, String url, String authorization, Tariff tariff, String date, int countTicket) {
        this.context = context;
        this.url = url;
        this.authorization = authorization;
        this.tariff = tariff;
        this.date = date;
        this.countTicket = countTicket;
    }

    public void requestAvailDispatchers(DispatcherRequest.DispatcherRequestListener dispatcherRequestListener) {

        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("direction_id", String.valueOf(tariff.getDirection_id()));
        requestBody.put("machine_id", "0");
        requestBody.put("date1", date.replace("/", "-") + "T00:00:00Z");
        requestBody.put("date2", date.replace("/", "-") + "T23:59:59Z");
        requestBody.put("ticket_count", String.valueOf(countTicket));

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Authorization", authorization);
        RequestFuture<JSONObject> future = RequestFuture.newFuture();

        DispatcherRequest dispatcherRequest = new DispatcherRequest(
                Request.Method.POST,
                url,
                header,
                new JSONObject(requestBody).toString(),
                dispatcherRequestListener,
                this
        );

        SingletonRequestQueue.getInstance(context.getApplicationContext()).getRequestQueue().add(dispatcherRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("Error", "Error");
    }
}
