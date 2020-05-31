package com.example.dishwish.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.dishwish.data.DishContract.DishEntry;

public class DishProvider extends ContentProvider {

    // Tag for log messages
    public static final String LOG_TAG = DishProvider.class.getSimpleName();
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

        // Set notification URI on the Cursor.
        // If the data at the specified URI changes, the Cursor needs to be updated.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    /**
     * Insert new data into the provider with the given ContentValues.
     */
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        // Get writable database
        SQLiteDatabase db = dishDbHelper.getWritableDatabase();

        // ID of the inserted row
        long newRowId;

        // Find what code the URI matches to
        int match = uriMatcher.match(uri);
        // Execute query based on the type of URI
        if (match == DISHES) {
            newRowId = db.insert(DishEntry.TABLE_NAME, null, contentValues);
        } else {
            throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }

        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (newRowId == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Notify all listeners that the data has changed at the specified URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the new URI with the newly inserted row's ID appended at the end
        return ContentUris.withAppendedId(uri, newRowId);
    }

    /**
     * Updates the data at the given selection and selection arguments, with the new ContentValues.
     */
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        // Get writable database
        SQLiteDatabase db = dishDbHelper.getWritableDatabase();

        // Number of rows updated
        int rows;

        // Find what code the URI matches to
        int match = uriMatcher.match(uri);
        // Execute query based on the type of URI
        if (match == DISH_ID) {
            selection = DishEntry._ID + " = ?";
            selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
            rows = db.update(DishEntry.TABLE_NAME, contentValues, selection, selectionArgs);
        } else {
            throw new IllegalArgumentException("Update is not supported for " + uri);
        }

        // Notify all listeners that the data has changed at the specified URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the number of rows updated
        return rows;
    }

    /**
     * Delete the data at the given selection and selection arguments.
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writable database
        SQLiteDatabase db = dishDbHelper.getWritableDatabase();

        // Number of rows deleted
        int rows;

        // Find what code the URI matches to
        int match = uriMatcher.match(uri);
        // Execute query based on the type of URI
        if (match == DISH_ID) {
            selection = DishEntry._ID + " = ?";
            selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
            rows = db.delete(DishEntry.TABLE_NAME, selection, selectionArgs);
        } else {
            throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        // Notify all listeners that the data has changed at the specified URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the number of rows deleted
        return rows;
    }

    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri) {
        return null;
    }
}
