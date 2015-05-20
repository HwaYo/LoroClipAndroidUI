package info.devsusu.material;

import android.app.admin.DeviceAdminInfo;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class RecordActivity extends ActionBarActivity {

    RecordListAdapter bookmarkListAdpater;

    private Toolbar mToolbar;
    private BookmarkListAdapter bookmarkListAdapter;
    private RecyclerView bookmarkRecycler;
    private LinearLayoutManager manager;

    private TextView timeText;
    private ImageView trashImg;
    private ImageView recordImg;
    private ImageView doneImg;

    private Animation fadeIn;
    private Animation fadeOut;

    private boolean isRecording = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        bookmarkRecycler = (RecyclerView) findViewById(R.id.bookmark_list);
        bookmarkListAdapter = new BookmarkListAdapter(this, bookmarkRecycler);

        manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);

        bookmarkRecycler.setLayoutManager(manager);
        bookmarkRecycler.setAdapter(bookmarkListAdapter);
        bookmarkRecycler.addItemDecoration(
                new HorizontalDividerItemDecoration
                        .Builder(this)
                        .sizeResId(R.dimen.divider)
                        .color(R.color.myGrayColor)
                        .marginResId(R.dimen.leftmargin, R.dimen.rightmargin)
                        .build());

        timeText = (TextView) findViewById(R.id.record_time_text);

        trashImg = (ImageView) findViewById(R.id.record_trash_img);
        trashImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discardRecording();
            }
        });
        recordImg = (ImageView) findViewById(R.id.record_action_img);
        recordImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeRecordingStatus();
            }
        });
        doneImg = (ImageView) findViewById(R.id.record_done_img);
        doneImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishRecording();
            }
        });

        fadeOut = AnimationUtils.loadAnimation(RecordActivity.this, R.anim.fade_out);
        fadeIn = AnimationUtils.loadAnimation(RecordActivity.this, R.anim.fade_in);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                changeRecordingButton();

                // fade in animation
                recordImg.startAnimation(fadeIn);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_record, menu);
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

    private void changeRecordingStatus () {

        // Change record ImageView src
        if ( isRecording ) {

            pauseRecording();

            // fade out animation
            recordImg.startAnimation(fadeOut);

        } else {

            resumeRecording();

            recordImg.startAnimation(fadeOut);

        }

        isRecording = !isRecording;
    }

    private void changeRecordingButton() {
        if ( isRecording ) {
            recordImg.setImageResource(R.drawable.pause);
        } else {
            recordImg.setImageResource(R.drawable.record);
        }
    }

    private void finishRecording() {

        DateFormat format = new SimpleDateFormat("HH:mm, yyyy-MM-dd");
        String dateString = format.format(Calendar.getInstance().getTime());

        // show a dialog to set filename
        new MaterialDialog.Builder(this)
                .title(R.string.save_record)
                .content(R.string.set_record_name)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(dateString, dateString, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        pauseRecording();
                        saveRecording();
                    }
                }).show();

    }

    private void pauseRecording() {

        // TODO implement this function

    }

    private void saveRecording() {

        // TODO

    }

    private void resumeRecording() {

        // TODO implement this function

    }

    private void discardRecording() {

        // First make a dialog asking if you want to finish
        new MaterialDialog.Builder(this)
                .title(R.string.discard_record)
                .content(R.string.discard_confirm)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        // finish without saving
                        finish();
                    }
                })
                .positiveText(R.string.discard)
                .negativeText(R.string.cancel)
                .show();

        // Then finish the Recording Activity
    }
}
