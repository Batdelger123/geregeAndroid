package metatech.mn.gerege.transdep;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import metatech.mn.gerege.R;
import metatech.mn.gerege.database.Tariff;
import metatech.mn.gerege.transdep.Fragment.FragmentUserInput;
import metatech.mn.gerege.transdep.RecyclerView.data.Dispatcher;

public class UserInput extends AppCompatActivity implements FragmentUserInput.FragmentUserInputListener, View.OnClickListener {

    public static String USER_INPUT_BUNDLE = "bundle";
    public static String USER_INPUT_TARIFF = "tariff";
    public static String USER_INPUT_DISPATCHER = "dispatcher";
    public static String USER_INPUT_SELECTED_SEATS = "selectedSeatsNumber";
    public static String USER_INPUT_COUNT_PASSENGER = "countPassenger";
    public static String USER_INPUT_COUNT_CHILD_PASSENGER = "countChildPassenger";
    public static String USER_INPUT_SEAT_NUMBER = "seatNumber";
    public static String USER_INPUT_IS_CHILD = "isChild";

    private Tariff tariff;
    private Dispatcher dispatcher;
    private ArrayList<Integer> selectedSeatsNo;
    private int countPassenger;
    private int countChildPassenger;

    List<FragmentUserInput> userInputList;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transdep_activity_user_input);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        button.setEnabled(false);

        if (savedInstanceState != null) {
            return;
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(USER_INPUT_BUNDLE);
        selectedSeatsNo = new ArrayList<Integer>();
        userInputList = new ArrayList<>();

        tariff = (Tariff) bundle.getSerializable(USER_INPUT_TARIFF);
        dispatcher = (Dispatcher) bundle.getSerializable(USER_INPUT_DISPATCHER);
        selectedSeatsNo = bundle.getIntegerArrayList(USER_INPUT_SELECTED_SEATS);
        countPassenger = bundle.getInt(USER_INPUT_COUNT_PASSENGER);
        countChildPassenger = bundle.getInt(USER_INPUT_COUNT_CHILD_PASSENGER);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        int temp = 0;

        for (int i = 0; i < selectedSeatsNo.size(); i++) {

            FragmentUserInput fragment = new FragmentUserInput();

            Bundle fragmentBundle = new Bundle();
            fragmentBundle.putSerializable(USER_INPUT_TARIFF, tariff);
            fragmentBundle.putSerializable(USER_INPUT_DISPATCHER, dispatcher);
            fragmentBundle.putInt(USER_INPUT_SEAT_NUMBER, selectedSeatsNo.get(i));
            fragmentBundle.putBoolean(USER_INPUT_IS_CHILD, (temp++ >= countPassenger));

            fragment.setArguments(fragmentBundle);

            fragmentTransaction.add(R.id.container_user_input_fragment,fragment);

            userInputList.add(fragment);
        }

        fragmentTransaction.commit();
    }

    @Override
    public void fragmentUserInputChanged() {
        for (FragmentUserInput fragment : userInputList) {
            if (!fragment.isFieldsFilled()) {
                button.setEnabled(false);
                return;
            }
        }
        button.setEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if (v == button) {

            /*
            {
                "dispatcher_id":    447219,
                "start_stop_id":    1,
                "end_stop_id":      334,
                "ticket_type_id":   1,
                "is_child":         0,
                "ticket_num":       1234567,
                "register":         "РЭ99090909",
                "passenger_name":   "А.Гончиг",
                "price92":          41400,
                "price8":           3600,
                "total":            45000,
                "car_type_id":      2,
                "insurance":        800,
                "seat_no":          22,
                "out_date":         "2017-08-17T19:21:09Z",
                "i_company_id":         25,
                "start_stop_sequence":  1,
                "end_stop_sequence":    8
            }
            */


            for (FragmentUserInput f : userInputList) {
                Log.d("qweqweqweqw", "w " + f.getUserName());
                Log.d("qweqweqweqw", "w " + f.getUserRegister());
                Log.d("qweqweqweqw", "w " + f.getPrice());
                Log.d("qweqweqweqw", "w " + f.isChild());
                Log.d("qweqweqweqw", "w " + f.hasInsurance());
            }
        }
    }
}
