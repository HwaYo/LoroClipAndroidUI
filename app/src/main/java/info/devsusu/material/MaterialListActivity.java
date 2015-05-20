package info.devsusu.material;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;


public class MaterialListActivity extends ActionBarActivity {

    private static final String tag = "RecordListView";

    private Toolbar mToolbar;
    private RecordListAdapter recordListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_list);

        // Android L Style Title Bar
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        RecyclerView recordList = (RecyclerView)findViewById(R.id.record_list);

        // Change this Adapter to fit LoroClip
        recordListAdapter = new RecordListAdapter(this, recordList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);

        recordList.setLayoutManager(manager);
        recordList.setAdapter(recordListAdapter);
        recordList.addItemDecoration(
                new HorizontalDividerItemDecoration
                        .Builder(this)
                        .sizeResId(R.dimen.divider)
                        .color(R.color.myGrayColor)
                        .marginResId(R.dimen.leftmargin, R.dimen.rightmargin)
                        .build());

        // Floating Button on Bottom Right Corner
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getBaseContext(), "New Recording", Toast.LENGTH_SHORT).show();

                // TODO
                // Start new Recording
                Intent intent = new Intent(MaterialListActivity.this,RecordActivity.class);
                startActivity(intent);
            }
        });

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
        switch (id) {
            case R.id.action_settings : /* Do something like showing SettingsActivity */ break;
            case R.id.action_sync :

                // TODO
                // Sync with Server

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
