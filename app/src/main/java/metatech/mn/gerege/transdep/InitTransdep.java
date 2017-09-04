package metatech.mn.gerege.transdep;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

import metatech.mn.gerege.R;
import metatech.mn.gerege.plugin.MyDatePicker;

/**
 * Created by Coder-Erdenebayar on 8/9/2017.
 */


public class InitTransdep extends Fragment {

    private final static int REQUEST_FROM_CODE = 100;
    private final static int REQUEST_TO_CODE = 200;
    private final static int REQUEST_PASSENGER = 300;

    // info data
    private int start_stop_id;
    private int end_stop_id;


    private EditText etFrom;
    private EditText etTo;
    private EditText etDeparting;
    private EditText etReturn;
    private TextView tvReturn;
    private EditText etPassenger;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transdep_front, container, false);

        tvReturn = (TextView) view.findViewById(R.id.tvReturn);
        etReturn = (EditText) view.findViewById(R.id.etReturnDate);

        Switch onOffSwitch = (Switch)  view.findViewById(R.id.swReturn);
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    etReturn.setVisibility(View.VISIBLE);
                    tvReturn.setVisibility(View.VISIBLE);
                } else {
                    etReturn.setVisibility(View.INVISIBLE);
                    tvReturn.setVisibility(View.INVISIBLE);
                }
            }

        });

        etFrom=(EditText) view.findViewById(R.id.etFrom);
        etFrom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DirectionView.class);
                view.getContext().startActivity(intent);
            }
        });

        etTo=(EditText) view.findViewById(R.id.etTo);
        etTo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DirectionView.class);
                view.getContext().startActivity(intent);
            }
        });

        etDeparting = (EditText) view.findViewById(R.id.etDeparting);
        etDeparting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                DialogFragment dialogfragment = new MyDatePicker();

                dialogfragment.show(getFragmentManager(), "departing");
            }
        });

        etPassenger = (EditText) view.findViewById(R.id.etPassenger);
        etPassenger.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), Passenger.class);
        try {
            getActivity().startActivityForResult(intent, REQUEST_PASSENGER);
        }catch (Exception e){
            e.printStackTrace();
        }
            }
        });

        return view;
    }


    // Call Back method  to get the Message form other Activity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        {
            super.onActivityResult(requestCode, resultCode, data);
            // check if the request code is same as what is passed  here it is 2
            if (resultCode == 2) {
                String message = data.getStringExtra("MESSAGE");
                EditText etFrom = (EditText) getView().findViewById(R.id.etFrom);
                EditText etTo = (EditText) getView().findViewById(R.id.etTo);
                EditText etPassenger = (EditText) getView().findViewById(R.id.etPassenger);

                if (requestCode == REQUEST_FROM_CODE) {
                    start_stop_id = data.getIntExtra("STOPID", 0);
                    etFrom.setText(message);
                    etTo.setText("");
                }
                if (requestCode == REQUEST_TO_CODE) {
                    end_stop_id = data.getIntExtra("STOPID", 0);
                    etTo.setText(message);
                }

                if (requestCode == REQUEST_PASSENGER) {
                    end_stop_id = data.getIntExtra("STOPID", 0);
                    etPassenger.setText(message);
                }

            }
        }
    }
}