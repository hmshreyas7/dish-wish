package com.example.dishwish.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dishwish.data.DishContract.DishEntry;

public class DishDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Dish.db";

    // Queries for creating and dropping the table
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DishEntry.TABLE_NAME + " (" +
                    DishEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DishEntry.COLUMN_DISH_TITLE + " TEXT," +
                    DishEntry.COLUMN_DISH_TYPE + " INTEGER," +
                    DishEntry.COLUMN_CATEGORY + " INTEGER);";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DishEntry.TABLE_NAME;

    public DishDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
