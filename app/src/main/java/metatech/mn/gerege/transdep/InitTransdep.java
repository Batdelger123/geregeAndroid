package metatech.mn.gerege.transdep;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import metatech.mn.gerege.database.Stops;
import metatech.mn.gerege.database.Tariff;
import metatech.mn.gerege.transdep.Bus.Bus;
import metatech.mn.gerege.transdep.Dialog.EndStopDialog;
import metatech.mn.gerege.transdep.Dialog.PassengerDialog;
import metatech.mn.gerege.transdep.Dialog.SearchDialog;
import metatech.mn.gerege.transdep.Dialog.StartStopDialog;
import metatech.mn.gerege.R;
import metatech.mn.gerege.Start;
import metatech.mn.gerege.transdep.RecyclerView.data.Dispatcher;
import metatech.mn.gerege.transdep.RecyclerView.data.Data;
import metatech.mn.gerege.transdep.RecyclerView.data.DispatcherRequest;

/**
 * Created by Coder-Erdenebayar on 8/9/2017.
 */


public class InitTransdep extends Fragment implements StartStopDialog.StartStopDialogListener, EndStopDialog.EndStopDialogListener, PassengerDialog.PassengerDialogListener, View.OnClickListener {

    private final static int REQUEST_FROM_CODE = 100;
    private final static int REQUEST_TO_CODE = 200;
    private final static int REQUEST_PASSENGER = 300;
    public static String START_STOP_ID = "start_stop_id";
    public static String START_STOP_AIMAG_ID = "start_stop_aimag_id";

    // info data
    private int start_stop_id;
    private int end_stop_id;

    private Start parentActivity;
    private View view;
    private EditText etFrom;
    private EditText etTo;
    private EditText etDeparting;
    private EditText etReturn;
    private TextView tvReturn;
    private EditText etPassenger;
    private Button btnSearch;

    // Store results from dialog
    private Stops startStop;
    private Stops endStop;
    private Tariff endTariff;
    private int countAdult;
    private int countChild;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = (Start) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.transdep_front, container, false);

        btnSearch = (Button) view.findViewById(R.id.btnSearch);
        etFrom = (EditText) view.findViewById(R.id.etFrom);
        etTo = (EditText) view.findViewById(R.id.etTo);
        etDeparting = (EditText) view.findViewById(R.id.etDeparting);
        etPassenger = (EditText) view.findViewById(R.id.etPassenger);

        btnSearch.setOnClickListener(this);
        etFrom.setOnClickListener(this);
        etTo.setOnClickListener(this);
        etDeparting.setOnClickListener(this);
        etPassenger.setOnClickListener(this);

        tvReturn = (TextView) view.findViewById(R.id.tvReturn);
        etReturn = (EditText) view.findViewById(R.id.etReturnDate);

        Switch onOffSwitch = (Switch) view.findViewById(R.id.swReturn);
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etReturn.setVisibility(View.VISIBLE);
                    tvReturn.setVisibility(View.VISIBLE);
                } else {
                    etReturn.setVisibility(View.INVISIBLE);
                    tvReturn.setVisibility(View.INVISIBLE);
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.etFrom:
                StartStopDialog dialogFrom = new StartStopDialog(this);
                dialogFrom.show(getFragmentManager(), "StartStopDialog");
//                intent = new Intent(view.getContext(), DirectionView.class);
//                intent.putExtra("editText", "From");
//                view.getContext().startActivity(intent);
                break;
            case R.id.etTo:
                EndStopDialog dialogTo;

                if (startStop != null) {
                    dialogTo = new EndStopDialog(this, startStop);
                    dialogTo.show(getFragmentManager(), "StartStopDialog");
                }
//                intent = new Intent(getActivity(), DirectionView.class);
//                intent.putExtra("editText", "To");
//                startActivity(intent);
                break;
            case R.id.etDeparting:
//                MyDatePicker dialogfragment = new MyDatePicker();
//                dialogfragment.show(getFragmentManager(), "departing");
//                java.util.Calendar calendar = java.util.Calendar.getInstance();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(java.util.Calendar.YEAR);
                int month = calendar.get(java.util.Calendar.MONTH);
                int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        String strMonth = String.valueOf(month);
                        if (month < 10) {
                            strMonth = "0" + month;
                        }

                        String strDay = String.valueOf(dayOfMonth);
                        if (dayOfMonth < 10) {
                            strDay = "0" + dayOfMonth;
                        }

                        etDeparting.setText(String.valueOf(year + "-" + strMonth + "-" + strDay));
                    }
                };
                new DatePickerDialog(getContext(), dateSetListener, year, month, day).show();
                break;
            case R.id.etPassenger:
                PassengerDialog passengerDialog = new PassengerDialog(this);
                passengerDialog.show(getFragmentManager(), "PassengerDialog");
//                intent = new Intent(getActivity(), Passenger.class);
//                try {
//                    getActivity().startActivityForResult(intent, REQUEST_PASSENGER);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                break;
            case R.id.btnSearch:

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Loading ...");
                progressDialog.show();

                Data data = new Data(
                        getContext(),
                        "http://metakioskdb.intelmax.mn/ords/pdb1/testuser1/reference/avail_dispatchers",
                        "Bearer PudRXKOLozgXuh547qZotQ..",
                        endTariff,
                        etDeparting.getText().toString(),
                        (countAdult + countChild)
                );
                data.requestAvailDispatchers(
                        new DispatcherRequest.DispatcherRequestListener() {
                            @Override
                            public void resultFromDispatcherRequest(List<Dispatcher> dispatcherList) {
                                progressDialog.dismiss();
                                List<Dispatcher> list = new ArrayList<Dispatcher>();

                                for (int i = 0; i < dispatcherList.size(); i++) {
                                    Dispatcher dispatcher = dispatcherList.get(i);
                                    if (dispatcher.getStartStopId() == endTariff.getStart_stop_id() && dispatcher.getEndStopId() == endTariff.getEnd_stop_id()) {
                                        list.add(dispatcherList.get(i));
                                    }
                                }

                                SearchDialog dialog = new SearchDialog(list);
                                dialog.show(getFragmentManager(), "SearchDialog");
                            }
                        }
                );
                break;
        }
    }

    @Override
    public void resultFromStartStopDialog(int startStopId, String startStopName, boolean isUB) {
        if (startStopId == -1 && startStopName == null)
            return;
        startStop = new Stops(startStopId, startStopName, isUB);
        etFrom.setText(startStopName);
        etTo.setText("");
        endStop = null;
    }

    @Override
    public void resultFromEndStopDialog(Tariff tariff, boolean isUB) {
        this.endTariff = tariff;

        if (tariff == null) {
            etTo.setText("");
            return;
        }

        if (isUB) {
            etTo.setText(endTariff.getEnd_stop_name());
//            List<Aimags> aimag = new DBAimags().getAimags(getContext(), DatabaseAccess.AIMAGSTABLE, null, " id = ?", new String[]{String.valueOf(endTariff.getEnd_aimag_id())}, null, null);
//            if (!aimag.isEmpty()) {
//                etTo.setText(aimag.get(0).getName());
//            } else {
//                etTo.setText(endTariff.getEnd_stop_name());
//            }
        } else {
            etTo.setText(endTariff.getEnd_stop_name());
        }
    }

    @Override
    public void resultFromPassengerDialog(int adult, int child) {
        this.countAdult = adult;
        this.countChild = child;
        String string = "";
        if (adult != 0)
            string += adult + " Том хүн";
        if (child != 0) {
            if (!string.equals(""))
                string += ", ";
            string += child + " Хүүхэд";
        }
        etPassenger.setText(string);
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