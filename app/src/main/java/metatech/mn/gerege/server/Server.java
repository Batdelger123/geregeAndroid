package metatech.mn.gerege.server;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import metatech.mn.gerege.login.Login;
import metatech.mn.gerege.login.Sign_up;

/**
 * Created by Coder-Erdenebayar on 8/14/2017.
 */

public class Server extends AsyncTask<String, Void, Void> {

    private ProgressDialog progress;
    private Activity activity;
    private Uri.Builder builder;
    private String sResponse = "";
    private String sActivityName;

    public Server(Activity activity){
        this.activity = activity;
    }

    public Server(Activity activity, Uri.Builder builder, String sActivityName){
        this.activity = activity;
        this.builder = builder;
        this.sActivityName =sActivityName;
    }

    protected void onPreExecute(){
        progress = new ProgressDialog(activity);
        progress.setMessage("Loading");
        progress.show();
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection client = (HttpURLConnection) url.openConnection();
            client.setRequestMethod(params[1]);
            try {
                if (params[2] != null) {
                    client.setRequestProperty("Authorization", params[2]);
                }
            }catch (Exception ex){

            }

            client.setDoOutput(true);
            String query = builder.build().getEncodedQuery();

            OutputStream os = client.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();

            int responseCode = client.getResponseCode();

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);


            //Read
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));

            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            bufferedReader.close();
            sResponse = sb.toString();

            System.out.println(sResponse);

            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    ReturnResponse();
                }
            });

        }catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ReturnResponse();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ReturnResponse();
        }

        return null;
    }

    protected void onPostExecute() {
        progress.dismiss();
    }

    public void ReturnResponse(){
        progress.dismiss();
        switch (sActivityName){
            case "Signup": {
                Sign_up sign_up = (Sign_up) activity;
                sign_up.Return(sResponse);
            }break;
            case "Signupphone": {
                Sign_up sign_up = (Sign_up) activity;
                sign_up.ReturnPhone(sResponse);
            }break;
            case "Login": {
                Login login = (Login) activity;
                login.ReturnServer(sResponse);
            }break;
            case "ChangePin": {
                Login login = (Login) activity;
                login.ReturnChangePin(sResponse);
            }break;

        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}