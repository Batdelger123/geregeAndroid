package metatech.mn.gerege.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import metatech.mn.gerege.R;

/**
 * Created by Coder-Erdenebayar on 6/5/2017.
 */

public class QrScan extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(R.layout.qr_scan);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.scan_view);
        linearLayout.addView(mScannerView);  // Set the scanner view as the content view
        mScannerView.setScaleY(linearLayout.getScaleY());
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        Intent intent=new Intent();
        intent.putExtra("MESSAGE",rawResult.getText());
        setResult(RESULT_OK,intent);
        onBackPressed();
    }
}