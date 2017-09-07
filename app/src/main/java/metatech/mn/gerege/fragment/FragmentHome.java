package metatech.mn.gerege.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import metatech.mn.gerege.R;
import metatech.mn.gerege.Start;

/**
 * Created by Enkhtur on 9/7/2017.
 */

public class FragmentHome extends Fragment implements View.OnClickListener{

    private Start parentActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = (Start) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_home, container, false);

        ImageButton btnTransdepButton = (ImageButton) view.findViewById(R.id.ibTransdep);
        btnTransdepButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ibTransdep:
                parentActivity.setTab(5);   //  transdep/InitTransdep
                break;

            default:
                Toast.makeText(getActivity(), "Error !", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
