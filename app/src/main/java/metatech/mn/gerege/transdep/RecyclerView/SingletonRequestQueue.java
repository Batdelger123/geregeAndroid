package metatech.mn.gerege.transdep.RecyclerView;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Enkhtur on 9/19/2017.
 */

public class SingletonRequestQueue {

    private static Context context;
    private RequestQueue requestQueue;
    private static SingletonRequestQueue instance;

    private SingletonRequestQueue(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public static synchronized SingletonRequestQueue getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonRequestQueue(context);
        }
        return instance;
    };

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
