package metatech.mn.gerege.transdep.RecyclerView.data;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import metatech.mn.gerege.database.Tariff;

/**
 * Created by Enkhtur on 9/18/2017.
 */

public class DispatcherRequest extends Request<List<Dispatcher>> {

    private Tariff tariff;
    private HashMap<String, String> requestHeader;
    private String requestBody;
    private DispatcherRequestListener dispatcherRequestListener;

    public interface DispatcherRequestListener {
        public void resultFromDispatcherRequest(List<Dispatcher> dispatcherList);
    }

    public DispatcherRequest(HashMap<String, String> requestHeader, String requestBody, DispatcherRequestListener dispatcherRequestListener, Response.ErrorListener listener) {
        super(Method.POST, "http://metakioskdb.intelmax.mn/ords/pdb1/testuser1/reference/avail_dispatchers", listener);
        this.requestBody = requestBody;
        this.requestHeader = requestHeader;
        this.dispatcherRequestListener = dispatcherRequestListener;
    }

    public DispatcherRequest(int method, String url, HashMap<String, String> requestHeader, String requestBody, DispatcherRequestListener dispatcherRequestListener, Response.ErrorListener listener) {
        super(method, url, listener);
        this.requestHeader = requestHeader;
        this.requestBody = requestBody;
        this.dispatcherRequestListener = dispatcherRequestListener;
    }

    @Override
    protected Response<List<Dispatcher>> parseNetworkResponse(NetworkResponse response) {
        List<Dispatcher> dispatchers = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(new String(response.data, HttpHeaderParser.parseCharset(response.headers)));
            JSONArray jsonDispatcherArray = jsonObject.optJSONArray("avail_dispatchers");
            for(int i = 0; i<jsonDispatcherArray.length();i++){
                JSONObject jsonDispatcher = jsonDispatcherArray.getJSONObject(i);
              //  if (jsonDispatcher.optInt("DIRECTION_START_STOP_ID") == tariff.getStart_stop_id() && jsonDispatcher.optInt("DIRECTION_END_STOP_ID") == tariff.getEnd_stop_id()) {
                if (jsonDispatcher.optInt("CAR_TYPE_ID") != 4) {
                    dispatchers.add(new Dispatcher(
                            jsonDispatcher.optInt("ID"),
                            jsonDispatcher.optInt("DIRECTION_ID"),
                            jsonDispatcher.optInt("DIRECTION_START_STOP_ID"),
                            jsonDispatcher.optInt("DIRECTION_END_STOP_ID"),
                            jsonDispatcher.optInt("CAR_TYPE_ID"),
                            jsonDispatcher.optInt("SITCOUNT"),
                            jsonDispatcher.optString("DIRECTION_NAME"),
                            jsonDispatcher.optString("DIRECTION_START_STOP_NAME"),
                            jsonDispatcher.optString("DIRECTION_END_STOP_NAME"),
                            jsonDispatcher.optString("CAR_TYPE_NAME"),
                            jsonDispatcher.optString("LEAVE_DATE")
                    ));
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Response.success(dispatchers, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(List<Dispatcher> response) {
        dispatcherRequestListener.resultFromDispatcherRequest(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return requestHeader;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return requestBody.getBytes();
    }
}
