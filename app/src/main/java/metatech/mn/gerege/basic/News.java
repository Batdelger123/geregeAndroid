package metatech.mn.gerege.basic;

import android.app.Fragment;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by CODER-ERDENEBAYAR on 9/4/2017.
 */
public class News extends Fragment
{

    public final View onCreateView(LayoutInflater inflater,
                                   ViewGroup container,
                                   Bundle savedInstanceState)
    {
            /*
             * Just a button that starts an activity for result
             */
        Button button = new Button(getActivity());
        button.setText("Click me!");
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                startActivityForResult(intent, 0);
            }
        });

        return button;
    }

    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data)
    {
        // This does NOT get called
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(getActivity(),
                "Consumed by nested fragment",
                Toast.LENGTH_SHORT).show();
    }
}
