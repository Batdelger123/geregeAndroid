package metatech.mn.gerege.transdep.Dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import metatech.mn.gerege.R;
import metatech.mn.gerege.database.Aimags;
import metatech.mn.gerege.database.DBAimags;
import metatech.mn.gerege.database.DBTariff;
import metatech.mn.gerege.database.DatabaseAccess;
import metatech.mn.gerege.database.Tariff;
import metatech.mn.gerege.transdep.InitTransdep;

/**
 * Created by Enkhtur on 9/7/2017.
 */

public class StartStopDialog extends DialogFragment implements View.OnClickListener {

    private TextView textView1, textView2;
    private Button btnOk, btnCancel;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private Spinner spinner1, spinner2;
    private ArrayAdapter<Aimags> arrayAdapter1;
    private ArrayAdapter<Tariff> arrayAdapter2;

    private StartStopDialogListener initTransdep;

    public interface StartStopDialogListener{
        public void resultFromStartStopDialog(int startStopId, String startStopName, boolean isUB);
    }

    public StartStopDialog(InitTransdep initTransdep) {
        if (initTransdep instanceof StartStopDialogListener){
            this.initTransdep = (StartStopDialogListener)initTransdep;
        } else {
            throw new RuntimeException("must implement StartStopDialogListener");
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

        arrayAdapter1 = new ArrayAdapter<Aimags>(this.getContext(),  android.R.layout.simple_spinner_item, new ArrayList<Aimags>());
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);

        arrayAdapter2 = new ArrayAdapter<Tariff>(this.getContext(),  android.R.layout.simple_spinner_item, new ArrayList<Tariff>());
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);

        getDialog().setTitle("Title");
        loadStartStopAimags(DatabaseAccess.AIMAGSTABLE, null, DatabaseAccess.ISCOUNTRY + " = 1 AND " + DatabaseAccess.BVS + " < 4", null, null, null);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (!radioButton2.isChecked()) {
                    loadStartStops( (arrayAdapter1.getItem(position)).getId());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        radioButton1.setChecked(true);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_ok:
                try {
                    if (radioButton2.isChecked()) {     // Ulaanbaatar
                        initTransdep.resultFromStartStopDialog(
                                arrayAdapter1.getItem(spinner1.getSelectedItemPosition()).getId(),
                                arrayAdapter1.getItem(spinner1.getSelectedItemPosition()).getName(),
                                true
                        );
                    } else {    // Busad
                        initTransdep.resultFromStartStopDialog(
                                arrayAdapter2.getItem(spinner2.getSelectedItemPosition()).getStart_stop_id(),
                                arrayAdapter2.getItem(spinner2.getSelectedItemPosition()).getStart_stop_name(),
                                false
                        );
                    }
                } catch (Exception e) {
                    initTransdep.resultFromStartStopDialog(-1, null, false);
                }
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

                loadStartStopAimags(DatabaseAccess.AIMAGSTABLE, null, DatabaseAccess.ISCOUNTRY + " = 1 AND " + DatabaseAccess.BVS + " < 4", null, null, null);      // load Aimag
             //   loadStartStops(arrayAdapter1.getItem(spinner1.getSelectedItemPosition()).getId());
                break;

            case R.id.radioButton2:
                textView1.setText(R.string.start_stop_city);
                textView2.setVisibility(View.INVISIBLE);
                spinner2.setVisibility(View.INVISIBLE);

                loadStartStopAimags(DatabaseAccess.AIMAGSTABLE, null, DatabaseAccess.ISCOUNTRY + " = 0", null, null, null);         // load City
                break;

            case R.id.radioButton3:
                textView1.setText(R.string.start_stop_foreign);
                textView2.setText(R.string.start_stop_city);
                textView2.setVisibility(View.VISIBLE);
                spinner2.setVisibility(View.VISIBLE);

                loadStartStopAimags(DatabaseAccess.AIMAGSTABLE, null, DatabaseAccess.ISCOUNTRY + " = 1 AND " + DatabaseAccess.BVS + " >= 4", null, null, null);     // load Foreign Country
            //    loadStartStops(arrayAdapter1.getItem(spinner1.getSelectedItemPosition()).getId());
                break;
        }
    }

    public void loadStartStopAimags(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having) {
        List<Aimags> direction= new DBAimags().getAimags(getContext(), table, columns, selection, selectionArgs, groupBy, having);
        arrayAdapter1.clear();
        arrayAdapter1.addAll(direction);
        arrayAdapter1.notifyDataSetChanged();
    }

    public void loadStartStops(int aimagId) {
        arrayAdapter2.clear();
        List<Tariff> direction = new DBTariff().getStopTariffs(
                getContext(),
                DatabaseAccess.TARIFFTABLE,
                new String[] {"*"},
                null,
                null,
                DatabaseAccess.START_STOP_ID,
                DatabaseAccess.AIMAG_ID + " = " + String.valueOf(aimagId)
        );

        arrayAdapter2.addAll(direction);
        arrayAdapter2.notifyDataSetChanged();
    }
}
