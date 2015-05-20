package info.devsusu.material;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by susu on 5/19/15.
 */
public class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.ViewHolder> {

    private final static String TAG = "RecordListAdpater";

    List<Record> recordList;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private RecyclerView mRecyclerView;

    public RecordListAdapter( Context context, RecyclerView recyclerView ) {
        super();
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        recordList = getDataForListView();
        mRecyclerView = recyclerView;
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
        view.setOnClickListener(new RecyclerOnClickListener());
        view.setOnLongClickListener(new RecyclerOnLongClickListener());
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

    public class RecyclerOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Record record = recordList.get(findPosition(v));

            // TODO
            // Start Playing Activity
            Log.i(TAG, record.toString() + "OnClick");
            showToast(record.toString() + "OnClick");
        }
    }

    public class RecyclerOnLongClickListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            Record record = recordList.get(findPosition(v));

            Log.i(TAG, record.toString() + "OnLongClick");
            showToast(record.toString() + "OnLongClick");

            // TODO
            // Do something after Selection
            showListDialog();

            return true;
        }
    }

    public boolean showListDialog (){

        new MaterialDialog.Builder(mContext)
                .title(R.string.edit_record)
                .items(R.array.record_options)
                .itemsCallback(new MaterialDialogCallback())
                .show();

        return false;

    }

    public class MaterialDialogCallback implements MaterialDialog.ListCallback {

        @Override
        public void onSelection(MaterialDialog materialDialog, View view, int which, CharSequence text) {

            Log.i(TAG, which + ": " + text);
            showToast(which + ": " + text);
            // TODO
            // Do something after Selection ( Delete, Change Title, ... )

        }
    }

    private int findPosition ( View v ) {
        return mRecyclerView.getChildLayoutPosition(v);
    }

    public void showToast( String msg ) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
