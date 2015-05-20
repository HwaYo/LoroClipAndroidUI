package info.devsusu.material;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by susu on 5/20/15.
 */
public class BookmarkListAdapter extends RecyclerView.Adapter<BookmarkListAdapter.ViewHolder> {

    private final static String TAG = "BookmarkListAdapter";

    List<Bookmark> bookmarkList;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private RecyclerView mRecyclerView;

    public BookmarkListAdapter(Context mContext, RecyclerView mRecyclerView) {
        this.bookmarkList = getDataForListView();
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mRecyclerView = mRecyclerView;
    }

    private List<Bookmark> getDataForListView() {

        List<Bookmark> bookmarks = new ArrayList<Bookmark>();

        bookmarks.add( new Bookmark("Important", Color.RED) ); // Bookmark Important
        bookmarks.add( new Bookmark("Didn't Understand", Color.BLUE) ); // Bookmark Don't UnderStand
        bookmarks.add( new Bookmark("Can't Hear", Color.YELLOW) ); // Bookmark Can't Hear
        bookmarks.add( new Bookmark("Bookmark 4", Color.GRAY) ); // Bookmark Can't Hear
        bookmarks.add( new Bookmark("Bookmark 5", Color.BLACK) ); // Bookmark Can't Hear
        bookmarks.add( new Bookmark("Bookmark 6", Color.GREEN) ); // Bookmark Can't Hear

        return bookmarks;

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        View viewHolder;

        public ViewHolder(View view) {
            super(view);
            viewHolder = view;
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate view and Attach Click Listeners
        View view = mLayoutInflater.inflate(R.layout.bookmark_list_item, parent, false);
        view.setOnClickListener(new BookmarkOnClickListener());
//        view.setOnLongClickListener(new RecyclerOnLongClickListener());

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Bookmark bookmark = bookmarkList.get(position);
        View view = holder.viewHolder;

        Drawable circle = mContext.getResources().getDrawable(R.drawable.circle);
        circle.setColorFilter(
                new PorterDuffColorFilter(bookmark.getColor(),PorterDuff.Mode.OVERLAY)
        );

        ImageView img = (ImageView) view.findViewById(R.id.bookmark_image);
        img.setBackground(circle);

        TextView name = (TextView) view.findViewById(R.id.bookmark_name);
        name.setText(bookmark.getName());

    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }

    public class BookmarkOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO
            // Leave Bookmark
            Bookmark bookmark = bookmarkList.get(findPosition(v));
            // Do Something with Bookmark

            showToast( bookmarkList.get(findPosition(v)).toString() );
        }
    }

    private int findPosition ( View v ) {
        return mRecyclerView.getChildLayoutPosition(v);
    }

    public void showToast( String msg ) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
