package metatech.mn.gerege.transdep.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import metatech.mn.gerege.R;
import metatech.mn.gerege.database.Aimags;
import metatech.mn.gerege.database.DBAimags;
import metatech.mn.gerege.database.DBTariff;
import metatech.mn.gerege.database.DatabaseAccess;
import metatech.mn.gerege.database.Stops;
import metatech.mn.gerege.database.Tariff;
import metatech.mn.gerege.transdep.InitTransdep;

/**
 * Created by Enkhtur on 9/8/2017.
 */

public class EndStopDialog extends DialogFragment implements View.OnClickListener {

    private TextView textView1, textView2;
    private Button btnOk, btnCancel;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private Spinner spinner1, spinner2;
    private List<String> listNames1, listNames2;
    private List<Integer> listId1, listId2;
    private InitTransdep initTransdep;
    private ArrayAdapter<Aimags> arrayAdapter1;
    private ArrayAdapter<Tariff> arrayAdapter2;
    private Stops startStop;

    public interface EndStopDialogListener {
        public void resultFromEndStopDialog(int endStopId, String endStopName, boolean isUB);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public EndStopDialog(InitTransdep initTransdep, Stops startStop) {
        if (initTransdep instanceof StartStopDialog.StartStopDialogListener) {
            this.initTransdep = initTransdep;
            this.startStop = startStop;
        } else {
            throw new RuntimeException("Must implement EndStopDialogListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.trabsdep_start_stop_dialog, null);

        textView1 = (TextView) view.findViewById(R.id.textView1);
        textView2 = (TextView) view.findViewById(R.id.textView2);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        btnOk = (Button) view.findViewById(R.id.btn_ok);
        radioButton1 = (RadioButton) view.findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) view.findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) view.findViewById(R.id.radioButton3);
        spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);

        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        radioButton1.setOnClickListener(this);
        radioButton2.setOnClickListener(this);
        radioButton3.setOnClickListener(this);

        arrayAdapter1 = new ArrayAdapter<Aimags>(this.getContext(), android.R.layout.simple_spinner_item, new ArrayList<Aimags>());
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);

        arrayAdapter2 = new ArrayAdapter<Tariff>(this.getContext(), android.R.layout.simple_spinner_item, new ArrayList<Tariff>());
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);

        getDialog().setTitle("Title");

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!radioButton2.isChecked()) {
//                    loadEndStops((arrayAdapter1.getItem(position)).getId());
                    loadEndStops(startStop.getId(), (arrayAdapter1.getItem(position)).getId());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        String table = DatabaseAccess.TARIFFTABLE + " JOIN " + DatabaseAccess.AIMAGSTABLE + " ON " + DatabaseAccess.TARIFFTABLE + "." + DatabaseAccess.END_STOP_AIMAG_ID + " = " + DatabaseAccess.AIMAGSTABLE + ".id";
        String[] columns = new String[] {"aimags.*"};
        String selection = (startStop.isUB()) ? DatabaseAccess.AIMAG_ID : DatabaseAccess.START_STOP_ID;
        String[] selectionArgs = new String[] { String.valueOf(startStop.getId()) };
        String groupBy = DatabaseAccess.END_STOP_AIMAG_ID;

        switch (v.getId()) {

            case R.id.btn_ok:
                try {
                    if (radioButton2.isChecked()) {     // Ulaanbaatar
                        initTransdep.resultFromEndStopDialog(arrayAdapter1.getItem(spinner1.getSelectedItemPosition()).getId(), arrayAdapter1.getItem(spinner1.getSelectedItemPosition()).getName(), true);
                    } else {    // Busad
                        initTransdep.resultFromEndStopDialog(arrayAdapter2.getItem(spinner2.getSelectedItemPosition()).getEnd_stop_id(), arrayAdapter2.getItem(spinner2.getSelectedItemPosition()).getEnd_stop_name(), false);
                    }
                } catch (Exception e) {}
                dismiss();
                break;

            case R.id.btn_cancel:
                dismiss();
                break;

            case R.id.radioButton1:
                textView1.setText(R.string.start_stop_aimag);
                textView2.setText(R.string.start_stop);
                textView2.setVisibility(View.VISIBLE);
                spinner2.setVisibility(View.VISIBLE);

                loadEndStopAimags(table, columns, selection + " = ? AND " + DatabaseAccess.ISCOUNTRY + " = 1 AND " + DatabaseAccess.BVS + " < 4", selectionArgs, groupBy, null);
                break;
            case R.id.radioButton2:
                textView1.setText(R.string.start_stop_city);
                textView2.setVisibility(View.INVISIBLE);
                spinner2.setVisibility(View.INVISIBLE);

                loadEndStopAimags(table, columns, selection + " = ? AND " + DatabaseAccess.ISCOUNTRY + " = 0", selectionArgs, groupBy, null);
                break;

            case R.id.radioButton3:
                textView1.setText(R.string.start_stop_foreign);
                textView2.setText(R.string.start_stop_city);
                textView2.setVisibility(View.VISIBLE);
                spinner2.setVisibility(View.VISIBLE);

                loadEndStopAimags(table, columns, selection + " = ? AND " + DatabaseAccess.ISCOUNTRY + " = 1 AND " + DatabaseAccess.BVS + " >= 4", selectionArgs, groupBy, null);
                break;
        }
    }

    public void loadEndStopAimags(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having) {
        arrayAdapter1.clear();
        List<Aimags> direction = new DBAimags().getAimags(getContext(), table, columns, selection, selectionArgs, groupBy, having);
        arrayAdapter1.clear();
        arrayAdapter1.addAll(direction);
        arrayAdapter1.notifyDataSetChanged();
    }

    public void loadEndStops(int startId, int endAimagId ) {
        arrayAdapter2.clear();
        String selection = (startStop.isUB()) ? DatabaseAccess.AIMAG_ID : DatabaseAccess.START_STOP_ID;

        List<Tariff> directions = new DBTariff().getStopTariffs(
                getContext(),
                DatabaseAccess.TARIFFTABLE,
                new String[] {"*"},
                selection + " = ? AND " + DatabaseAccess.END_STOP_AIMAG_ID + " = ?",
                new String[] {String.valueOf(startId), String.valueOf(endAimagId)},
                DatabaseAccess.END_STOP_ID,
                null
        );
        for (Tariff direction:directions) {
            direction.setStartTariff(false);
        }
        arrayAdapter2.addAll(directions);
        arrayAdapter2.notifyDataSetChanged();
    }

    //    public boolean loadEndStopAimags(int id, boolean isCapital) {
//        arrayAdapter1.clear();
//
//        List<Aimags> direction;
//        if (isCapital) {
//            direction = new DBTariff().getEndStopAimags(getContext(), "aimag_id", id);
//        } else {
//            direction = new DBTariff().getEndStopAimags(getContext(), "start_stop_id", id);
//        }
//        arrayAdapter1.clear();
//        arrayAdapter1.addAll(direction);
//        arrayAdapter1.notifyDataSetChanged();
//
//        return  arrayAdapter1.isEmpty();
//    }
//
//    public boolean loadEndStopCity(int id, boolean isCapital) {
//        arrayAdapter1.clear();
//        List<Aimags> direction;
//        if (isCapital) {
//            direction = new DBTariff().getEndStopCity(getContext(), "aimag_id", id);
//        } else {
//            direction = new DBTariff().getEndStopCity(getContext(), "start_stop_id", id);
//        }
//        arrayAdapter1.clear();
//        arrayAdapter1.addAll(direction);
//        arrayAdapter1.notifyDataSetChanged();
//
//        return  arrayAdapter1.isEmpty();
//    }
//
//    public boolean loadENdStopForiegnCountry(int id, boolean isCapital) {
//        arrayAdapter1.clear();
//        List<Aimags> direction;
//        if (isCapital) {
//            direction = new DBTariff().getEndStopForiegns(getContext(), "aimag_id", id);
//        } else {
//            direction = new DBTariff().getEndStopForiegns(getContext(), "start_stop_id", id);
//        }
//        arrayAdapter1.clear();
//        arrayAdapter1.addAll(direction);
//        arrayAdapter1.notifyDataSetChanged();
//
//        return  arrayAdapter1.isEmpty();
//    }
}