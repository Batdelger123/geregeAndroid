package metatech.mn.gerege.transdep;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import metatech.mn.gerege.R;
import metatech.mn.gerege.database.Aimags;
import metatech.mn.gerege.database.DBAimags;

/**
 * Created by Coder-Erdenebayar on 8/22/2017.
 */

public class DirectionView extends Activity {

    private TableLayout mTlayout;
    private TableRow tr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.transdep_direction);
        mTlayout = (TableLayout) findViewById(R.id.tlDirection);

        TextView tvTitle = new TextView(this);
        tvTitle.setText("Орон нутаг /аймаг, дүүрэг/".toUpperCase());
        tvTitle.setTextSize(20);
        LinearLayout.LayoutParams lastTxtParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 60);
        tvTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvTitle.setLayoutParams(lastTxtParams);
        tvTitle.setBackgroundResource(R.color.colorAccent);
        tvTitle.setTextColor(Color.WHITE);
        mTlayout.addView(tvTitle);

        List<Aimags> direction= new DBAimags().getAimags(DirectionView.this);

        for(int i=0; i< direction.size(); i++){
            if (i % 3 == 0) {
                tr = new TableRow(this);
                mTlayout.addView(tr);
            }
            final String aimag = direction.get(i).getName();
            final Integer aimagId = direction.get(i).getId();

            Button btn = new Button(this);
            btn.setText(aimag);

            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    System.out.println("v.getid is:- " + v.getId());
                    Intent intent = new Intent(getApplicationContext(), DirectionStart.class);
                    intent.putExtra("Aimag", aimag);
                    intent.putExtra("AimagId", aimagId);
                    startActivity(intent);
                }
            });
            tr.addView(btn);
        }

        tvTitle = new TextView(this);
        tvTitle.setText("Хотууд".toUpperCase());
        tvTitle.setTextSize(20);
        tvTitle.setLayoutParams(lastTxtParams);
        tvTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvTitle.setBackgroundResource(R.color.colorAccent);
        tvTitle.setTextColor(Color.WHITE);
        mTlayout.addView(tvTitle);

        direction= new DBAimags().getCity(DirectionView.this);

        for(int i=0; i< direction.size(); i++){
            if (i % 3 == 0) {
                tr = new TableRow(this);
                mTlayout.addView(tr);
            }
            Button btn = new Button(this);
            btn.setText(direction.get(i).getName());
            btn.setId(direction.get(i).getId());
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    System.out.println("v.getid is:- " + v.getId());
                }
            });
            tr.addView(btn);
        }

        tvTitle = new TextView(this);
        tvTitle.setText("Олон улс".toUpperCase());
        tvTitle.setTextSize(20);
        tvTitle.setLayoutParams(lastTxtParams);
        tvTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvTitle.setBackgroundResource(R.color.colorAccent);
        tvTitle.setTextColor(Color.WHITE);
        mTlayout.addView(tvTitle);

        direction= new DBAimags().getForiegnCountry(DirectionView.this);

        for(int i=0; i< direction.size(); i++){
            if (i % 3 == 0) {
                tr = new TableRow(this);
                mTlayout.addView(tr);
            }
            Button btn = new Button(this);
            btn.setText(direction.get(i).getName());
            btn.setId(direction.get(i).getId());
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    System.out.println("v.getid is:- " + v.getId());
                }
            });
            tr.addView(btn);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}