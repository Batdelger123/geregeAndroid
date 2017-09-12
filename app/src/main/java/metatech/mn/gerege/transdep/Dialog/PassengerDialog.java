package metatech.mn.gerege.transdep.Dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import metatech.mn.gerege.R;
import metatech.mn.gerege.transdep.InitTransdep;

/**
 * Created by Enkhtur on 9/9/2017.
 */

public class PassengerDialog extends DialogFragment implements View.OnClickListener, NumberPicker.OnValueChangeListener{

    private NumberPicker npAdult, npChild;
    private Button btnCancel, btnOk;
    private PassengerDialogListener initTransdep;

    public PassengerDialog(InitTransdep initTransdep) {
        if (initTransdep instanceof PassengerDialogListener)
            this.initTransdep = (PassengerDialogListener)initTransdep;
        else
            throw new RuntimeException("Must implement PassengerDialogListener");
    }

    public interface PassengerDialogListener{
        public void resultFromPassengerDialog(int adult, int child);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transdep_passener_dialog, null);

        btnOk = (Button) view.findViewById(R.id.btn_ok);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        npAdult = (NumberPicker) view.findViewById(R.id.npAdult);
        npChild= (NumberPicker) view.findViewById(R.id.npChild);

        npAdult.setMinValue(0);
        npAdult.setMaxValue(4);
        npChild.setMinValue(0);
        npChild.setMaxValue(4);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        npAdult.setOnValueChangedListener(this);
        npChild.setOnValueChangedListener(this);

        npAdult.setWrapSelectorWheel(false);
        npChild.setWrapSelectorWheel(false);

        getDialog().setTitle("Title");

        return view;
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        NumberPicker picker2 = (picker == npAdult) ? npChild : npAdult;

        if ( (picker.getValue() + picker2.getValue()) > 4)
            picker2.setValue(4 - picker.getValue());

//        picker2.setMaxValue(4 - picker.getValue());
//        if ( (picker.getValue() + picker2.getValue()) > 4)
//            picker2.setMaxValue(4 - picker.getValue());
    }

    @Override
    public void onClick(View v) {
        if (v == btnOk) {
            initTransdep.resultFromPassengerDialog(npAdult.getValue(), npChild.getValue());
            dismiss();
        }
        if (v == btnCancel) {
            dismiss();
        }
    }
}
