package metatech.mn.gerege.transdep.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import metatech.mn.gerege.R;
import metatech.mn.gerege.transdep.RecyclerView.data.Dispatcher;

/**
 * Created by Enkhtur on 9/12/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private LayoutInflater inflater;
    private List<Dispatcher> dataSet;
    private CustomAdapterListener customAdapterListener;

    public interface CustomAdapterListener {
        public void listItemClicked(int position);
    }

    public CustomAdapter(LayoutInflater inflater, CustomAdapterListener customAdapterListener, List<Dispatcher> dataSet) {
        this.inflater = inflater;
        this.dataSet = dataSet;
        this.customAdapterListener = customAdapterListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.transdep_list_item, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Dispatcher item = dataSet.get(position);

        holder.tvDirection.setText(item.getStartStopName() + " - " + item.getEndStopName());
        holder.tvParking.setText(item.getEndStopName().substring(item.getEndStopName().indexOf(".") + 1));
        holder.tvDate.setText(item.getLeaveDate().replace("T", ", ").replace("Z", ""));
        holder.tvBus.setText(item.getSeatCount() + " хүний суудалтай " + item.getCarTypeName());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private View container;
        private TextView tvDirection;
        private TextView tvParking;
        private TextView tvDate;
        private TextView tvBus;

        public CustomViewHolder(View itemView) {
            super(itemView);

            container = (View) itemView.findViewById(R.id.root_list_item);
            tvDirection = (TextView) itemView.findViewById(R.id.tv_direction);
            tvParking = (TextView) itemView.findViewById(R.id.tv_parking);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date_and_time);
            tvBus = (TextView) itemView.findViewById(R.id.tv_bus_type_and_seat);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            customAdapterListener.listItemClicked(getAdapterPosition());
        }
    }

}
