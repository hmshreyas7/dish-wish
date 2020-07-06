package com.dishwish;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.shape.MaterialShapeDrawable;

public class DishSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_settings);

        // Set material toolbar to act as actionbar
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        // Get toolbar's current color
        Drawable toolbarBackground = toolbar.getBackground();
        ColorStateList toolbarColor = ((MaterialShapeDrawable) toolbarBackground).getFillColor();

        // Set that color as actionbar color in recent apps list
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(getString(R.string.app_name),
                bitmap, toolbarColor.getDefaultColor());
        this.setTaskDescription(taskDescription);

        // Get a support actionbar corresponding to this toolbar
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
