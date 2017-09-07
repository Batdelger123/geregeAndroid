package metatech.mn.gerege.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import metatech.mn.gerege.R;
import metatech.mn.gerege.server.DBUpdate;

/**
 * Created by Enkhtur on 9/7/2017.
 */

public class FragmentMore extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_more, container, false);

        Button btnLogout = (Button) view.findViewById(R.id.button2);
        btnLogout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button2:
                Intent iDBUpdate = new Intent(getActivity(), DBUpdate.class);
                startActivity(iDBUpdate);// Activity
                break;

            default:
                Toast.makeText(getActivity(), "Error !", Toast.LENGTH_LONG).show();
        }

    }
}
