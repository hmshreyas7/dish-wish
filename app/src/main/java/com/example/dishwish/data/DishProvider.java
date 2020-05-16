package com.example.dishwish.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.dishwish.data.DishContract.DishEntry;

public class DishProvider extends ContentProvider {

    private static final int DISHES = 100;
    private static final int DISH_ID = 101;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(DishContract.CONTENT_AUTHORITY, DishContract.PATH_DISHES, DISHES);
        uriMatcher.addURI(DishContract.CONTENT_AUTHORITY, DishContract.PATH_DISHES + "/#", DISH_ID);
    }

    private DishDbHelper dishDbHelper;

    /**
     * Initialize the provider and the database helper object.
     */
    @Override
    public boolean onCreate() {
        // Create and initialize a DishDbHelper object to gain access to the dishes database.
        // Make sure the variable is a global variable, so it can be referenced from other
        // ContentProvider methods.
        dishDbHelper = new DishDbHelper(getContext());
        return true;
    }

    /**
     * Perform the query for the given URI. Use the given projection, selection, selection arguments, and sort order.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Get readable database
        SQLiteDatabase db = dishDbHelper.getReadableDatabase();
        // Cursor to hold result of query
        Cursor cursor;

        // Find what code the URI matches to
        int match = uriMatcher.match(uri);
        // Execute query based on the type of URI
        switch (match) {
            case DISHES:
                cursor = db.query(DishEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case DISH_ID:
                selection = DishEntry._ID + " = ?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = db.query(DishEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        return cursor;
    }

    /**
     * Insert new data into the provider with the given ContentValues.
     */
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    /**
     * Updates the data at the given selection and selection arguments, with the new ContentValues.
     */
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * Delete the data at the given selection and selection arguments.
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri) {
        return null;
    }
}
