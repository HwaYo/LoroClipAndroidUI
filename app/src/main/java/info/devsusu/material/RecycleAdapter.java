package info.devsusu.material;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by susu on 5/18/15.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private List<MaterialListActivity.Record> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public ViewHolder(View v) {
            super(v);
            mView = v;
        }
    }

    public RecycleAdapter(List<MaterialListActivity.Record> Dataset){
        mDataset = Dataset;
    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecycleAdapter.ViewHolder holder, int position) {

        TextView title = (TextView) holder.mView.findViewById(R.id.list_item_title);
        title.setText(mDataset.get(position).title);

        TextView time = (TextView) holder.mView.findViewById(R.id.list_item_time);
        time.setText(mDataset.get(position).length);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}