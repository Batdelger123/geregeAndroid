package metatech.mn.gerege.plugin;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

import metatech.mn.gerege.R;

/**
 * Created by CODER-ERDENEBAYAR on 9/1/2017.
 */

public class MyDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar calendar = Calendar.getInstance();
        int iYear = calendar.get(Calendar.YEAR);
        int iMonth = calendar.get(Calendar.MONTH);
        int iDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                R.style.DatePicker,this,iYear,iMonth,iDay);

        return  datepickerdialog;
    }

    public void onDateSet(android.widget.DatePicker view, int year, int month, int day){
        if(getTag().equals("departing")){
            TextView textview = (TextView)getActivity().findViewById(R.id.etDeparting);
            textview.setText(year + "/" + (month+1) + "/" + day);
        } else if(getTag().equals("return")){
            TextView textview = (TextView)getActivity().findViewById(R.id.etReturnDate);
            textview.setText(year + "/" + (month+1) + "/" + day);
        }
    }
}
