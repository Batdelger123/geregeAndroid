package metatech.mn.gerege.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import metatech.mn.gerege.R;

/**
 * Created by Enkhtur on 9/7/2017.
 */

public class FragmentTicket extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_ticket, container, false);
    }
}

