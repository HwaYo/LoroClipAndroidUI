package info.devsusu.material;

import android.graphics.Color;

/**
 * Created by susu on 5/20/15.
 */
public class Bookmark {

    private String name;
    private int color;

    public Bookmark(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getColor() { return color; }

    @Override
    public String toString() {
        return "bookmark " + name;
    }
}
