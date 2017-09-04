package metatech.mn.gerege.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import metatech.mn.gerege.R;
import metatech.mn.gerege.server.Server;

import static android.content.ContentValues.TAG;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public class Sign_up extends Activity {

    private String SERVER_URL = "http://49.0.223.7:8080/ords/metakioskdb/testuser1/users/";
    final private static int DIALOG_PHONE = 1;
    final private static int DIALOG_SUCCESS = 2;
    final private static int DIALOG_ERROR = 3;
    private int REQUEST_CODE = 100;
    private String userid = "";
    private int dialogID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent iQrScan = new Intent(Sign_up.this, QrScan.class);
        startActivityForResult(iQrScan,REQUEST_CODE);
    }

    public void qrScanSignup(View view){
        Intent iQrScan = new Intent(Sign_up.this, QrScan.class);
        startActivityForResult(iQrScan,REQUEST_CODE);
    }

    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            {
                userid = data.getStringExtra("MESSAGE");
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("userid", userid);
                Server server = new Server(Sign_up.this, builder, "Signup");
                server.execute(SERVER_URL+"qrcode","POST");
            }
        }
    }

    public void Return(String response){
        try {
            JSONObject jsonObj = new JSONObject(response);
            if(jsonObj.getString("msg").equals("PIN?")){
                Toast.makeText(getApplicationContext(), "Бүртгэлтэй хэрэглэгч байна.", Toast.LENGTH_LONG).show();
            }else {
                showDialog(DIALOG_PHONE);
            }
        }catch (final JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Json parsing error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    public void ReturnPhone(String response){
        try {
            JSONObject jsonObj = new JSONObject(response);
            if(jsonObj.getString("msg").equals("PIN SENT")){
                showDialog(DIALOG_SUCCESS);
            }else {
                showDialog(DIALOG_ERROR);
            }
        }catch (final JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showDialog(DIALOG_ERROR);
                }
            });

        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        AlertDialog dialogDetails = null;
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogview = inflater.inflate(R.layout.dialog_phone, null);

        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        //dialogbuilder.setTitle(R.string.login);
        dialogbuilder.setView(dialogview);
        dialogDetails = dialogbuilder.create();

        return dialogDetails;
    }

    @Override
    protected void onPrepareDialog(int id, final Dialog dialog) {

        dialogID =id;
        final AlertDialog alertDialog = (AlertDialog) dialog;
        Button loginbutton = (Button) alertDialog
                .findViewById(R.id.btn_login);
        Button cancelbutton = (Button) alertDialog
                .findViewById(R.id.btn_cancel);
        final EditText phone = (EditText) alertDialog
                .findViewById(R.id.etValue);
        TextView tvInfoDialog = (TextView) alertDialog.findViewById(R.id.tvInfo);

        loginbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

                switch (dialogID){
                    case DIALOG_PHONE: {
                        Uri.Builder builder = new Uri.Builder().appendQueryParameter("userid", userid).appendQueryParameter("phone", phone.getText().toString());
                        Server server = new Server(Sign_up.this, builder, "Signupphone");
                        server.execute(SERVER_URL+"phone","POST");
                    }break;
                    case DIALOG_SUCCESS: {
                        finish();
                    }
                }

            }
        });

        cancelbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent iQrScan = new Intent(Sign_up.this, QrScan.class);
                startActivityForResult(iQrScan,REQUEST_CODE);
            }
        });

        switch (id) {
            case DIALOG_PHONE:
                phone.setHint(getResources().getString(R.string.enter_your_phone));
                tvInfoDialog.setText(getResources().getString(R.string.enter_your_phone));
                phone.setVisibility(View.VISIBLE);
                loginbutton.setText(R.string.next);
                break;
            case DIALOG_SUCCESS:
                tvInfoDialog.setText(getResources().getString(R.string.dialog_success_sign));
                phone.setVisibility(View.INVISIBLE);
                loginbutton.setText(R.string.login);
                cancelbutton.setVisibility(View.INVISIBLE);
                break;
            case DIALOG_ERROR:
                tvInfoDialog.setText(getResources().getString(R.string.dialog_error_sign));
                phone.setVisibility(View.VISIBLE);
                loginbutton.setText(R.string.next);
                break;


        }
    }

}