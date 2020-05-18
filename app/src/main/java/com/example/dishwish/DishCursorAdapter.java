package com.example.dishwish;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

import com.example.dishwish.data.DishContract.DishEntry;

/**
 * {@link DishCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of dish data as its data source. This adapter knows
 * how to create list items for each row of dish data in the {@link Cursor}.
 */
public class DishCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link DishCursorAdapter}.
     *
     * @param context The context
     * @param cursor  The cursor from which to get the data.
     */
    public DishCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_dish, parent, false);
    }

    /**
     * This method binds the dish data (in the current row pointed to by cursor) to the given
     * list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct position.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView dishTitleView = view.findViewById(R.id.dish_title_view);
        String dishTitle = cursor.getString(cursor.getColumnIndex(DishEntry.COLUMN_DISH_TITLE));
        dishTitleView.setText(dishTitle);
    }
}
