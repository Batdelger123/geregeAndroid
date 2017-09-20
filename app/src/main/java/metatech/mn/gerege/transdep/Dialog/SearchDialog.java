package metatech.mn.gerege.transdep.Dialog;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import metatech.mn.gerege.R;
import metatech.mn.gerege.transdep.RecyclerView.data.Dispatcher;
import metatech.mn.gerege.transdep.RecyclerView.CustomAdapter;

/**
 * Created by Enkhtur on 9/12/2017.
 */

public class SearchDialog extends DialogFragment implements CustomAdapter.CustomAdapterListener{

    private View view;
    private RecyclerView recyclerView;
    private List<Dispatcher> listItems;

    public SearchDialog(List<Dispatcher> listItems) {
        this.listItems = listItems;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.transdep_directions_dialog, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        getDialog().setTitle("Title");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CustomAdapter customAdapter = new CustomAdapter(LayoutInflater.from(getContext()), this, listItems);
        recyclerView.setAdapter(customAdapter);
        return view;
    }

    @Override
    public void listItemClicked(int position) {
        Toast.makeText(getContext(), listItems.get(position).getStartStopName() + " - " + listItems.get(position).getEndStopName(), Toast.LENGTH_SHORT).show();
    }
}
