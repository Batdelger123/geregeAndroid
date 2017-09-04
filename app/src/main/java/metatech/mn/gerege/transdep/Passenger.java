package metatech.mn.gerege.transdep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import metatech.mn.gerege.R;
import metatech.mn.gerege.database.DBPassengerType;
import metatech.mn.gerege.database.PassengerType;

/**
 * Created by yyy on 7/17/2017.
 */

public class Passenger  extends Activity {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transdep_passenger);

        List<PassengerType> passengerTypes= new DBPassengerType().getPassengerType(Passenger.this);
        lv = (ListView) findViewById(R.id.lv_passenger);
        lv.setAdapter(new PassengerAdapter(this, passengerTypes));
        PassengerAdapter passengerAdapter = (PassengerAdapter) lv.getAdapter();
        passengerAdapter.InfoReturn();
    }


    public void Return(View v){
        PassengerAdapter passengerAdapter = (PassengerAdapter) lv.getAdapter();
        Intent intent=new Intent();
        intent.putExtra("MESSAGE", passengerAdapter.InfoReturn());
        setResult(2,intent);
        finish();//finishing activity
    }
}
