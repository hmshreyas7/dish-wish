package com.example.dishwish.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class DishContract {

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.example.dishwish";
    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.dishwish/dishes/ is a valid path for
     * looking at dish data. content://com.example.dishwish/menu/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "menu".
     */
    public static final String PATH_DISHES = "dishes";

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DishContract() {
    }

    /* Inner class that defines the table contents */
    public static class DishEntry implements BaseColumns {

        /**
         * The content URI to access the dish data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_DISHES);

        // Name of the table for dishes
        public static final String TABLE_NAME = "dishes";

        /**
         * Unique ID number for the dish (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * Name of the dish.
         * <p>
         * Type: TEXT
         */
        public static final String COLUMN_DISH_TITLE = "dish_title";

        /**
         * Type of the dish.
         * <p>
         * The only possible values are {@link #DISH_TYPE_SAVORY} or {@link #DISH_TYPE_SWEET}.
         * <p>
         * Type: INTEGER
         */
        public static final String COLUMN_DISH_TYPE = "dish_type";

        /**
         * Category of the dish.
         * <p>
         * The only possible values are {@link #CATEGORY_COOK} or {@link #CATEGORY_EAT}.
         * <p>
         * Type: INTEGER
         */
        public static final String COLUMN_CATEGORY = "category";

        /**
         * Possible values for dish type.
         */
        public static final int DISH_TYPE_SAVORY = 1;
        public static final int DISH_TYPE_SWEET = 2;

        /**
         * Possible values for dish category.
         */
        public static final int CATEGORY_COOK = 1;
        public static final int CATEGORY_EAT = 2;
    }
}
