package info.devsusu.material;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MaterialListActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    RecordListAdapter recordListAdapter;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        
        ListView recordList = (ListView)findViewById(R.id.record_list);

        recordListAdapter = new RecordListAdapter();

        recordList.setAdapter(recordListAdapter);

//        mRecyclerView = (RecyclerView) findViewById(R.id.record_recycler);
//
//        mRecyclerView.setHasFixedSize(true);
//
//        mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager = new GridLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);

//        mAdapter = new RecycleAdapter(getDataForListView());
//        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(recordList);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_material_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class Record {
        String title;
        String length;

    }

    public List<Record> getDataForListView() {

        List<Record> recordList = new ArrayList<Record>();
        Random len = new Random();

        for(int i=0;i<10;i++)
        {

            Record record= new Record();
            record.title = "RECORD NUMBER : "+i;
            record.length = (int)(len.nextFloat()*60) + "m";
            recordList.add(record);
        }

        return recordList;
    }

    public class RecordListAdapter extends BaseAdapter {

        List<Record> recordList = getDataForListView();

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
                        (LayoutInflater) MaterialListActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item, parent, false);
            }

            TextView title = (TextView)convertView.findViewById(R.id.list_item_title);
            TextView time = (TextView)convertView.findViewById(R.id.list_item_time);

            Record record = recordList.get(position);

            title.setText(record.title);
            time.setText(record.length);

            return convertView;
        }
    }
}
