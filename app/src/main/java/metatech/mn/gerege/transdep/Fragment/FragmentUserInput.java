package metatech.mn.gerege.transdep.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import metatech.mn.gerege.R;
import metatech.mn.gerege.database.Tariff;
import metatech.mn.gerege.transdep.RecyclerView.data.Dispatcher;
import metatech.mn.gerege.transdep.UserInput;

/**
 * Created by Enkhtur on 10/5/2017.
 */

public class FragmentUserInput extends Fragment implements TextWatcher{

    private EditText etUserRegister;
    private EditText etUserName;
    private TextView tvSeat;
    private TextView tvPrice;
    private CheckBox cbInsurance;  // Амь даатгал
    private CheckBox cbChild;  // Хүүхэд эсэх

    private Tariff tariff;
    private Dispatcher dispatcher;
    private int seatNumber;
    private boolean isChild;

    private boolean isFieldsFilled;
    private FragmentUserInputListener fragmentUserInputListener;

    public interface FragmentUserInputListener {
        public void fragmentUserInputChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentUserInputListener)
            fragmentUserInputListener = (FragmentUserInputListener) context;
        else
            fragmentUserInputListener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transdep_fragment_user_input, container, false);

        etUserRegister = (EditText) view.findViewById(R.id.et_user_register);
        etUserName = (EditText) view.findViewById(R.id.et_user_name);
        tvSeat = (TextView) view.findViewById(R.id.tv_seat);
        tvPrice = (TextView) view.findViewById(R.id.tv_price);
        cbChild = (CheckBox) view.findViewById(R.id.cb_child);
        cbInsurance = (CheckBox) view.findViewById(R.id.cb_insurance);

        Bundle bundle = getArguments();

        tariff = (Tariff) bundle.getSerializable(UserInput.USER_INPUT_TARIFF);
        dispatcher = (Dispatcher) bundle.getSerializable(UserInput.USER_INPUT_DISPATCHER);
        seatNumber = bundle.getInt(UserInput.USER_INPUT_SEAT_NUMBER);
        isChild = bundle.getBoolean(UserInput.USER_INPUT_IS_CHILD);

        cbChild.setChecked(isChild);
        cbChild.setEnabled(false);

        updatePrice();
        tvSeat.setText(String.valueOf(seatNumber));


        etUserName.addTextChangedListener(this);
        etUserRegister.addTextChangedListener(this);
        cbInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePrice();
            }
        });


        return view;
    }

    //_______________________
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        if (etUserName.getText().toString().length() >= 2 && etUserRegister.getText().toString().length() >= 2) {
            if (!isFieldsFilled()) {
                isFieldsFilled = true;
                fragmentUserInputListener.fragmentUserInputChanged();
            }
        } else {
            if (isFieldsFilled()) {
                isFieldsFilled = false;
                fragmentUserInputListener.fragmentUserInputChanged();
            }
        }
    }
    //___________________________

    public void updatePrice() {
        int price = 0;

        if (Dispatcher.CAR_TYPE_ID_BIG == dispatcher.getCarTypeId()) {
            if (isChild) {
                price = tariff.getBigChildPrice();
                price += (cbInsurance.isChecked()) ? tariff.getBigChildInsurance() : 0;
            } else {
                price = tariff.getBigPrice();
                price += (cbInsurance.isChecked()) ? tariff.getBigInsurance() : 0;
            }

            tvPrice.setText(String.valueOf(price));
            return;
        }

        if (Dispatcher.CAR_TYPE_ID_MID == dispatcher.getCarTypeId()) {
            if (isChild) {
                price = tariff.getMidChildPrice();
                price += (cbInsurance.isChecked()) ? tariff.getMidChildInsurance() : 0;
            } else {
                price = tariff.getMidPrice();
                price += (cbInsurance.isChecked()) ? tariff.getMidInsurance() : 0;
            }

            tvPrice.setText(String.valueOf(price));
            return;
        }

        if (Dispatcher.CAR_TYPE_ID_LIT == dispatcher.getCarTypeId()) {
            if (isChild) {
                price = tariff.getLitChildPrice();
                price += (cbInsurance.isChecked()) ? tariff.getLitChildInsurance() : 0;
            } else {
                price = tariff.getLitPrice();
                price += (cbInsurance.isChecked()) ? tariff.getLitInsurance() : 0;
            }

            tvPrice.setText(String.valueOf(price));
            return;
        }

        if (Dispatcher.CAR_TYPE_ID_SIT == dispatcher.getCarTypeId()) {
            if (isChild) {
                price = tariff.getSitChildPrice();
                price += (cbInsurance.isChecked()) ? tariff.getSitChildInsurance() : 0;
            } else {
                price = tariff.getSitPrice();
                price += (cbInsurance.isChecked()) ? tariff.getSitInsurance() : 0;
            }

            tvPrice.setText(String.valueOf(price));
            return;
        }

        tvPrice.setText(String.valueOf(price));
    }

    public String getUserName() {
        return etUserName.getText().toString();
    }

    public String getUserRegister() {
        return etUserRegister.getText().toString();
    }

    public int getPrice() {
        return Integer.parseInt(tvPrice.getText().toString());
    }

    public boolean isChild() {
        return cbChild.isChecked();
    }

    public boolean hasInsurance() {
        return cbInsurance.isChecked();
    }

    public boolean isFieldsFilled() {
        return isFieldsFilled;
    }

    public void setFieldsFilled(boolean fieldsFilled) {
        isFieldsFilled = fieldsFilled;
    }
}
