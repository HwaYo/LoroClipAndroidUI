package info.devsusu.material;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
        recordListAdapter = new RecordListAdapter(getBaseContext());

        ListView recordList = (ListView)findViewById(R.id.record_list);
        recordList.setAdapter(recordListAdapter);

        // Click Listeners
        recordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ListView Item Click", "List Number " + position);
                showToast("List Number " + position);

            // TODO
            // Play the Recording
        }
        });
        recordList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i("ListView Item Long Click", "List Number " + position);
                showToast("List Number " + position);

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

                Toast.makeText(getBaseContext(),"New Recording",Toast.LENGTH_SHORT).show();

                // TODO
                // Start new Recording
            }
        });

        // Floating button disappers if you enable code below
        // fab.attachToListView(recordList);
        // fab.show();

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
