package info.devsusu.material;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.melnykov.fab.FloatingActionButton;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;


public class MaterialListActivity extends ActionBarActivity {

    private static final String tag = "RecordListView";

    private Toolbar mToolbar;
    RecordListAdapter recordListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_list);

        // Android L Style Title Bar
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        // Change this Adapter to fit LoroClip
        recordListAdapter = new RecordListAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);

        RecyclerView recordList = (RecyclerView)findViewById(R.id.record_list);
        recordList.setLayoutManager(manager);
        recordList.setAdapter(recordListAdapter);
        recordList.addItemDecoration(
                new HorizontalDividerItemDecoration
                        .Builder(this)
                        .sizeResId(R.dimen.divider)
                        .color(Color.GRAY)
                        .marginResId(R.dimen.leftmargin, R.dimen.rightmargin)
                        .build());

        // Click Listeners
        recordList.setClickable(true);
//        recordList.addOnItemTouchListener(
//            new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
//                @Override public void onItemClick(View view, int position) {
//                    showToast("onTouch");
//                }
//            })
//        );
        recordList.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                showToast("List Number " + position);
                showToast("onLongClick");

                // TODO
                // Open Dialog

                showListDialog();

                return false;
            }
        });

        // Floating Button on Bottom Right Corner
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getBaseContext(), "New Recording", Toast.LENGTH_SHORT).show();

                // TODO
                // Start new Recording
            }
        });

        // Floating button disappers if you enable code below
//         fab.attachToRecyclerView(recordList);

    }

    public boolean showListDialog (){

        new MaterialDialog.Builder(this)
                .title(R.string.edit_record)
                .items(R.array.record_options)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        showToast(which + ": " + text);
                    }
                })
                .show();;

        return false;

    }

    public void showToast( String msg ) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
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
}
