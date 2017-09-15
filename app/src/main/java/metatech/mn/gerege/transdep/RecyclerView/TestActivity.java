package metatech.mn.gerege.transdep.RecyclerView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import metatech.mn.gerege.R;

public class TestActivity extends AppCompatActivity implements CustomAdapter.CustomAdapterListener{

    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transdep_directions_dialog);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CustomAdapter customAdapter = new CustomAdapter(getLayoutInflater(), this, listItems);
        recyclerView.setAdapter(customAdapter);
    }

    @Override
    public void listItemClicked(int position) {
        Toast.makeText(this, listItems.get(position).getFrom() + " - " + listItems.get(position).getTo(), Toast.LENGTH_SHORT).show();
    }
}
