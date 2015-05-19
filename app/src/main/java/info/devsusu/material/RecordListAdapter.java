package info.devsusu.material;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by susu on 5/19/15.
 */
public class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.ViewHolder> {

    List<Record> recordList;

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public RecordListAdapter( Context context ) {
        super();
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        recordList = getDataForListView();
    }

    public List<Record> getDataForListView() {

        List<Record> recordList = new ArrayList<Record>();
        Random len = new Random();

        for(int i=0;i<20;i++)
        {

            Record record= new Record();
            record.title = "May "+ i +", 2015 10_" + (int)(len.nextFloat()*60);
            record.length = (int)(len.nextFloat()*60) + "m";
            recordList.add(record);
        }

        return recordList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TextView title = (TextView) holder.viewHolder.findViewById(R.id.list_item_title);
        title.setText(recordList.get(position).title);

        TextView length = (TextView) holder.viewHolder.findViewById(R.id.list_item_time);
        length.setText(recordList.get(position).length);
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        View viewHolder;

        public ViewHolder(View view) {
            super(view);
            viewHolder = view;
        }

    }
}
