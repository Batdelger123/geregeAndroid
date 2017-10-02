package metatech.mn.gerege.transdep.Dialog;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import metatech.mn.gerege.R;
import metatech.mn.gerege.transdep.Bus.Bus;
import metatech.mn.gerege.transdep.RecyclerView.SingletonRequestQueue;
import metatech.mn.gerege.transdep.RecyclerView.data.Dispatcher;
import metatech.mn.gerege.transdep.RecyclerView.CustomAdapter;

/**
 * Created by Enkhtur on 9/12/2017.
 */

public class SearchDialog extends DialogFragment implements CustomAdapter.CustomAdapterListener{

    private View view;
    private RecyclerView recyclerView;
    private List<Dispatcher> listItems;
    private int noOfPassenger;

    public SearchDialog(List<Dispatcher> listItems, int ticket) {
        this.listItems = listItems;
        this.noOfPassenger = ticket;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.transdep_directions_dialog, null);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        getDialog().setTitle(R.string.avail_dispatchers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CustomAdapter customAdapter = new CustomAdapter(LayoutInflater.from(getContext()), this, listItems);
        recyclerView.setAdapter(customAdapter);
        return view;
    }

    @Override
    public void listItemClicked(final int position) {
//        Toast.makeText(getContext(), listItems.get(position).getStartStopName() + " - " + listItems.get(position).getEndStopName() + "-" + listItems.get(position).getId(), Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = SingletonRequestQueue.getInstance(getContext()).getRequestQueue();

        final HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("machine_id", "0");
        requestBody.put("dispatcher_id", "" + listItems.get(position).getId());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                "http://49.0.223.22:8080/ords/metapos/testuser1/reference/avail_seats1",
                new JSONObject(requestBody),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ArrayList<Integer> availSeats = new ArrayList<>();
                            JSONArray jsonArray = response.getJSONArray("avail_seats");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                availSeats.add(jsonObject.getInt("SEAT_NO"));
                            }

                            Intent intent = new Intent(getActivity(), Bus.class);
                            intent.putExtra(Bus.BUS_AVAIL_SEATS, availSeats);
                            intent.putExtra(Bus.BUS_PASSENGER, noOfPassenger);
                            intent.putExtra(Bus.BUS_DISPATCHER, listItems.get(position));

                            startActivity(intent);

                        } catch (JSONException e) {
                            Log.d("Error", "The key doesn't exist !");
                            Toast.makeText(getContext(), "Error - JSON key doesn't exist.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Resutlttt", "eririr");
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("Content-Type", "application/json");
                hashMap.put("Authorization", "Bearer vdDMwYuQOpsRAvdkzxxGLA..");

                return hashMap;
            }
        };

        requestQueue.add(jsonObjectRequest);

    }
}
