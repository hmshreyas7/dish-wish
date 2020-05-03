package com.example.dishwish;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.appbar.MaterialToolbar;

public class AddDishActivity extends AppCompatActivity {

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
        AutoCompleteTextView dishType = findViewById(R.id.dish_type);
        String[] dishTypeOptions = new String[]{getString(R.string.savory), getString(R.string.sweet)};
        buildMenuItems(dishType, dishTypeOptions);

        // Build list of categories
        AutoCompleteTextView category = findViewById(R.id.category);
        String[] categoryOptions = new String[]{getString(R.string.cook), getString(R.string.eat)};
        buildMenuItems(category, categoryOptions);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu with custom menu resource file
        getMenuInflater().inflate(R.menu.app_bar_options, menu);
        return true;
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
}
