package com.example.dishwish.data;

import android.provider.BaseColumns;

public final class DishContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DishContract() {
    }

    /* Inner class that defines the table contents */
    public static class DishEntry implements BaseColumns {
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
