package metatech.mn.gerege.transdep.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.ErrorManager;

/**
 * Created by Enkhtur on 9/14/2017.
 */

public class CustomRequest extends AsyncTask<String, Void, String> {

    private String requestUrl;
    private String requestMethod;
    private HashMap<String, String> requstProperties;
    private String requestBody;

    public CustomRequest(String url, String resuestMethod, HashMap requstProperties, String requestBody) {
        super();
        this.requestUrl = url;
        this.requestMethod = resuestMethod;
        this.requstProperties = requstProperties;
        this.requestBody = requestBody;
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(requestMethod);

            if (requstProperties != null) {
                Iterator iterator = requstProperties.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry pair = (Map.Entry)iterator.next();
                    httpURLConnection.setRequestProperty(pair.getKey().toString(), pair.getValue().toString());
                    iterator.remove();
                }
            }

            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches (false);

            if (requestBody != null) {
                httpURLConnection.setDoOutput(true);
                OutputStream out = new BufferedOutputStream(httpURLConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter (new OutputStreamWriter(out, "UTF-8"));
                writer.write(requestBody);
                writer.flush();
                writer.close();
                out.close();
            }

            httpURLConnection.connect();

            int statusCode = httpURLConnection.getResponseCode();

             /* HTTP_OK == 200 */
            if (statusCode == HttpURLConnection.HTTP_OK) {

//                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
//                String result = convertStreamToString(inputStream);
//
//                return result;
                //Read
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                String line = null;
                StringBuilder sb = new StringBuilder();

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                bufferedReader.close();
                return sb.toString();
            }

            return null;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

    public static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append((line + "\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
