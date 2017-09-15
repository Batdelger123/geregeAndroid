package metatech.mn.gerege.transdep.Dialog;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import metatech.mn.gerege.R;
import metatech.mn.gerege.database.Tariff;
import metatech.mn.gerege.transdep.RecyclerView.CustomAdapter;
import metatech.mn.gerege.transdep.RecyclerView.Data;
import metatech.mn.gerege.transdep.RecyclerView.ListItem;

/**
 * Created by Enkhtur on 9/12/2017.
 */

public class SearchDialog extends DialogFragment implements CustomAdapter.CustomAdapterListener{

    private View view;
    private RecyclerView recyclerView;
    private List<ListItem> listItems;
    private Tariff tariff;
    private String date;
    private int countTicket;

    public SearchDialog(Tariff tariff, String date, int countTicket) {
        this.tariff = tariff;
        this.date = date;
        this.countTicket = countTicket;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.transdep_directions_dialog, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        getDialog().setTitle("Title");

        listItems = new ArrayList<ListItem>();
        // String date, String stop, String busType, int countSeat
        listItems.add(new ListItem("УБ.Сонгинохайрхан", "До.Замын-Үүд", "2017-10-25, 10:00", "Уб.Сонгинохайрхан", "Дунд оврын автобус", 50));
        listItems.add(new ListItem("УБ.Сонгинохайрхан", "Сэ.Сүхбаатар", "2018-01-05, 15:30", "Улаанбаатар", "Том оврын автобус", 50));
        listItems.add(new ListItem("УБ.Сонгинохайрхан", "Хөвсгэл", "2017-07-20, 21:00", "Хө.Галт", "Дунд оврын автобус", 50));
        listItems.add(new ListItem("УБ.Сонгинохайрхан", "Говь-Алтай", "2015-10-04, 17:20", "Вокзал", "Дунд оврын автобус", 50));
        listItems.add(new ListItem("УБ.Сонгинохайрхан", "Ховд", "2014-12-05, 12:40", "Уб.Сонгинохайрхан", "Том оврын автобус", 50));
        listItems.add(new ListItem("TestFrom", "TestTo", "2014-12-05, 12:40", "Уб.Сонгинохайрхан", "Том оврын автобус", 50));
        listItems.add(new ListItem());
        listItems.add(new ListItem());
        listItems.add(new ListItem());
        listItems.add(new ListItem());
        listItems.add(new ListItem());
        listItems.add(new ListItem());
        listItems.add(new ListItem());
        listItems.add(new ListItem());
        listItems.add(new ListItem());
        listItems.add(new ListItem());
        listItems.add(new ListItem());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        listItems = (new Data(getContext(), tariff, date, countTicket)).getDataList();

        CustomAdapter customAdapter = new CustomAdapter(LayoutInflater.from(getContext()), this, listItems);
        recyclerView.setAdapter(customAdapter);
        return view;
    }

    @Override
    public void listItemClicked(int position) {
        Toast.makeText(getContext(), listItems.get(position).getFrom() + " - " + listItems.get(position).getTo(), Toast.LENGTH_SHORT).show();
    }
}
