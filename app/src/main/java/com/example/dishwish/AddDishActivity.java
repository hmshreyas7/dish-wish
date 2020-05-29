package com.example.dishwish;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dishwish.data.DishContract.DishEntry;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

public class AddDishActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int DISH_LOADER = 0;

    private TextInputEditText dishTitleText;
    private AutoCompleteTextView dishType, category;
    private Uri currentDishUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        // Set material toolbar to act as actionbar
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        // Build list of dish types
        dishType = findViewById(R.id.dish_type);
        String[] dishTypeOptions = new String[]{getString(R.string.savory), getString(R.string.sweet)};
        buildMenuItems(dishType, dishTypeOptions);

        // Build list of categories
        category = findViewById(R.id.category);
        String[] categoryOptions = new String[]{getString(R.string.cook), getString(R.string.eat)};
        buildMenuItems(category, categoryOptions);

        toggleKeyboard();

        // Get data from MainActivity to decide which mode to run in
        Intent intent = getIntent();
        currentDishUri = intent.getData();

        if (currentDishUri == null) {
            setTitle(R.string.add_dish);
        } else {
            setTitle(R.string.edit_dish);
            LoaderManager.getInstance(this).initLoader(DISH_LOADER, null, this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu with custom menu resource file
        getMenuInflater().inflate(R.menu.app_bar_options, menu);

        // Handle events based on how the dish title changes
        final MenuItem doneItem = menu.findItem(R.id.done);
        dishTitleText = findViewById(R.id.dish_title_text);
        final int maxDishTitleLength = 50;
        dishTitleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String dishTitleString = dishTitleText.getText().toString();
                doneItem.setVisible(dishTitleString.trim().length() > 0 && dishTitleString.length() <= maxDishTitleLength);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.done) {
            saveDish();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Builds a list of items for a dropdown menu.
     *
     * @param menu  the view that displays the selected item
     * @param items the list of items to be displayed
     */
    public void buildMenuItems(AutoCompleteTextView menu, String[] items) {
        menu.setText(items[0], false);
        menu.setInputType(InputType.TYPE_NULL);
        ArrayAdapter<String> menuAdapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.dropdown_menu_items, items);
        menu.setAdapter(menuAdapter);
    }

    /**
     * Shows/hides the keyboard depending on what is focused.
     */
    public void toggleKeyboard() {
        LinearLayout addDishLayout = findViewById(R.id.add_dish_layout);
        addDishLayout.setClickable(true);
        addDishLayout.setFocusable(true);
        addDishLayout.setFocusableInTouchMode(true);

        dishTitleText = findViewById(R.id.dish_title_text);
        dishTitleText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        dishTitleText.requestFocus();

        dishTitleText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!dishTitleText.isFocused()) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(dishTitleText.getWindowToken(), 0);
                    dishTitleText.clearFocus();
                }
            }
        });
    }

    public void saveDish() {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DishEntry.COLUMN_DISH_TITLE, dishTitleText.getText().toString().trim());

        if (dishType.getText().toString().equals(getString(R.string.savory))) {
            values.put(DishEntry.COLUMN_DISH_TYPE, DishEntry.DISH_TYPE_SAVORY);
        } else {
            values.put(DishEntry.COLUMN_DISH_TYPE, DishEntry.DISH_TYPE_SWEET);
        }

        if (category.getText().toString().equals(getString(R.string.cook))) {
            values.put(DishEntry.COLUMN_CATEGORY, DishEntry.CATEGORY_COOK);
        } else {
            values.put(DishEntry.COLUMN_CATEGORY, DishEntry.CATEGORY_EAT);
        }

        if (currentDishUri == null) {
            // Insert the new row, returning the URI with that row's ID appended at the end
            Uri newUri = getContentResolver().insert(DishEntry.CONTENT_URI, values);

            if (newUri == null) {
                Toast.makeText(this, getString(R.string.insertion_fail), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.insertion_success), Toast.LENGTH_SHORT).show();
            }
        } else {
            // Update the chosen item, returning the number of rows updated
            int rows = getContentResolver().update(currentDishUri, values, null, null);

            if (rows == 0) {
                Toast.makeText(this, getString(R.string.update_fail), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.update_success), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Define a projection that specifies which columns from the database
        // will actually be used after this query
        String[] projection = {
                DishEntry._ID,
                DishEntry.COLUMN_DISH_TITLE,
                DishEntry.COLUMN_DISH_TYPE,
                DishEntry.COLUMN_CATEGORY
        };

        // Create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        return new CursorLoader(getApplicationContext(),
                currentDishUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        dishTitleText.setText(data.getString(data.getColumnIndex(DishEntry.COLUMN_DISH_TITLE)));
        int cursorPosition = dishTitleText.length();
        Selection.setSelection(dishTitleText.getText(), cursorPosition);

        if (data.getInt(data.getColumnIndex(DishEntry.COLUMN_DISH_TYPE)) == DishEntry.DISH_TYPE_SAVORY) {
            dishType.setText(getString(R.string.savory), false);
        } else {
            dishType.setText(getString(R.string.sweet), false);
        }

        if (data.getInt(data.getColumnIndex(DishEntry.COLUMN_CATEGORY)) == DishEntry.CATEGORY_COOK) {
            category.setText(getString(R.string.cook), false);
        } else {
            category.setText(getString(R.string.eat), false);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        dishTitleText.setText("");
        dishType.setText(getString(R.string.savory), false);
        dishType.setText(getString(R.string.cook), false);
    }
}
