package metatech.mn.gerege.transdep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.List;

import metatech.mn.gerege.R;
import metatech.mn.gerege.Start;
import metatech.mn.gerege.database.DBTariff;
import metatech.mn.gerege.database.Tariff;

public class DirectionStart extends AppCompatActivity {

    TableLayout tableLayout;
    TableRow tableRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.transdep_direction_start);

        Bundle bundle = getIntent().getExtras();
        final String aimag = bundle.getString("Aimag");
        final Integer aimagId = bundle.getInt("AimagId");
        final String editText = bundle.getString("editText");

        tableLayout = (TableLayout) findViewById(R.id.tlDirectionStart);

    //    List<Aimags> direction= new DBAimags().getAimags(DirectionStart.this);
        List<Tariff> direction = new DBTariff().getStartStops(DirectionStart.this, aimagId);

        for(int i=0; i< direction.size(); i++){
            if (i % 3 == 0) {
                tableRow = new TableRow(this);
                tableLayout.addView(tableRow);
            }

            final String startStopName = direction.get(i).getStart_stop_name();
            final Integer startStopId = direction.get(i).getId();

            Button btn = new Button(this);
            btn.setText(startStopName.substring( startStopName.indexOf(".") + 1));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    // startStopName
                    // startStopId
                    Intent intent = new Intent(getApplicationContext(), Start.class);
                    intent.putExtra("Aimag", aimag);
                    intent.putExtra("AimagId", aimagId);

                    if (editText.equals("From")) {
                        intent.putExtra("startStopName", startStopName);
                        intent.putExtra("startStopId", startStopId);
                        Toast.makeText(getApplicationContext(), "From", Toast.LENGTH_LONG).show();
                    } else {
                        intent.putExtra("endStopName", startStopName);
                        intent.putExtra("endStopId", startStopId);
                        Toast.makeText(getApplicationContext(), "To", Toast.LENGTH_LONG).show();
                    }

                    startActivity(intent);
                }
            });
            tableRow.addView(btn);
        }

    }
}
