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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import metatech.mn.gerege.R;
import metatech.mn.gerege.database.DatabaseUser;
import metatech.mn.gerege.database.User;
import metatech.mn.gerege.server.Server;

import static android.content.ContentValues.TAG;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public class Login extends Activity {

    String SERVER_URL = "http://49.0.223.7:8080/ords/metakioskdb/testuser1/users/";
    final private static int DIALOG_LOGIN = 1;
    private String token;
    private EditText etUserid;
    private EditText etUserPin;
    private CheckBox cbRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_login);
        etUserid = (EditText) findViewById(R.id.etUsercode);
        etUserPin = (EditText) findViewById(R.id.etPassword);
        cbRemember = (CheckBox) findViewById(R.id.cbReminder);

        User user = new DatabaseUser().getUser(Login.this);
        if(user != null){
            etUserid.setText(user.getUserid().toString());
            etUserPin.setText(user.getPin().toString());
            cbRemember.setChecked(true);
        } else {
            etUserid.setText("");
            etUserPin.setText("");
            cbRemember.setChecked(false);
        }
    }

    public void qrScanner(View view) {
        Intent iQrScan = new Intent(Login.this, QrScan.class);
        startActivityForResult(iQrScan, 2);
    }

    public void clkLogin(View view) {

        if(etUserid.getText().toString().matches("")){
            etUserid.setError("");
            Toast.makeText(Login.this, R.string.enter_your_usercode, Toast.LENGTH_SHORT).show();
            return;
        } else if(etUserPin.getText().toString().matches("")){
            etUserPin.setError(getText(R.string.enter_your_password));
            return;
        }else {
            if(new Server(this).isOnline() == false) {
                Toast.makeText(getApplicationContext(), R.string.not_internet, Toast.LENGTH_LONG).show();
            }else {
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("userid", etUserid.getText().toString()).appendQueryParameter("pin", etUserPin.getText().toString());
                Server server = new Server(Login.this, builder, "Login");
                server.execute(SERVER_URL + "login", "POST");
            }
        }
    }

    public void clkSignup(View view) {
        if(new Server(this).isOnline() == false) {
            Toast.makeText(getApplicationContext(), R.string.not_internet, Toast.LENGTH_LONG).show();
        }else {
            Intent iSignup = new Intent(Login.this, Sign_up.class);
            startActivity(iSignup);
        }
    }

    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2)
        {
            String message = data.getStringExtra("MESSAGE");
            etUserid.setText(message);
            etUserid.setError(null);//removes error
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }

    public void ReturnServer(String response){
        try {
            JSONObject jsonObj = new JSONObject(response);
            token = jsonObj.getString("access_token");
            if(jsonObj.getString("msg").equals("INVALID USER")){
                Toast.makeText(getApplicationContext(),
                        R.string.can_not_access,
                        Toast.LENGTH_LONG).show();
            }else {
                // login info save database;
                boolean isChecked = ((CheckBox) findViewById(R.id.cbReminder)).isChecked();
                new DatabaseUser().onCreateUser(this, token.toString(), etUserid.getText().toString(), etUserPin.getText().toString(), isChecked);

                if(jsonObj.getString("msg").equals("FIRST LOGON")){
                    Toast.makeText(getApplicationContext(), "Анхны", Toast.LENGTH_LONG).show();
                    showDialog(DIALOG_LOGIN);
                }else {
                    Toast.makeText(getApplicationContext(), R.string.successfully_entered, Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }catch (final JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            R.string.can_not_access,
                            Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    public void ReturnChangePin(String response){
        try {
            JSONObject jsonObj = new JSONObject(response);
            if(jsonObj.getString("msg").equals("PIN CHANGED")){
                Toast.makeText(getApplicationContext(), R.string.change_pin, Toast.LENGTH_LONG).show();
                finish();
            }else {
                Toast.makeText(getApplicationContext(), R.string.can_not_change_pin, Toast.LENGTH_LONG).show();
            }
        }catch (final JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), R.string.can_not_change_pin, Toast.LENGTH_LONG).show();
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

        final AlertDialog alertDialog = (AlertDialog) dialog;
        Button loginbutton = (Button) alertDialog
                .findViewById(R.id.btn_login);
        Button cancelbutton = (Button) alertDialog
                .findViewById(R.id.btn_cancel);
        final EditText pin = (EditText) alertDialog
                .findViewById(R.id.etValue);
        TextView tvInfo = (TextView) alertDialog.findViewById(R.id.tvInfo);

        loginbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

                Uri.Builder builder = new Uri.Builder().appendQueryParameter("pin", etUserPin.getText().toString()).appendQueryParameter("newpin", pin.getText().toString());
                Server server = new Server(Login.this, builder, "ChangePin");
                server.execute(SERVER_URL+"changepin","POST", "Bearer "+token);

            }
        });

        cancelbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                finish();
            }
        });

        switch (id) {
            case DIALOG_LOGIN:
                tvInfo.setText(R.string.first_login);
                pin.setHint(R.string.enter_pin);
                loginbutton.setText(R.string.change);
                break;
        }
    }
}