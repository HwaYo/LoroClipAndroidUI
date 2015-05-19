package info.devsusu.material;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by susu on 5/19/15.
 */
public class RecordListAdapter extends BaseAdapter {

    List<Record> recordList;

    private Context mContext;

    public RecordListAdapter( Context context ) {
        mContext = context;
        recordList = getDataForListView();
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int position) {
        return recordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if( convertView == null ) {
            LayoutInflater inflater =
                    (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView title = (TextView)convertView.findViewById(R.id.list_item_title);
        TextView time = (TextView)convertView.findViewById(R.id.list_item_time);

        Record record = recordList.get(position);

        title.setText(record.title);
        time.setText(record.length);

        return convertView;
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
}
