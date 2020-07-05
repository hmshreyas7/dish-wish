package com.dishwish;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.TypedValue;

import com.google.android.material.appbar.MaterialToolbar;

public class DishSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_settings);

        // Set material toolbar to act as actionbar
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        // Set color of Up button based on theme
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorOnPrimarySurface, typedValue, true);
        int color = typedValue.data;
        toolbar.getNavigationIcon().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

        // Set activity title
        setTitle(R.string.settings);

        // Replace existing container with settings fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new DishSettingsFragment())
                .commit();
    }
}
