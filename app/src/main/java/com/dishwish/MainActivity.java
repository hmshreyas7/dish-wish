package com.dishwish;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dishwish.data.DishContract.DishEntry;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static boolean isAppLaunch = true;
    private boolean isLaunchThemeDark;
    public static boolean isNightModeEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (isAppLaunch && Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            isNightModeEnabled = sharedPreferences.getBoolean(getString(R.string.night_mode_key), false);
            isLaunchThemeDark = isNightModeEnabled;

            if (isNightModeEnabled) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        // Define a custom adapter to power a view pager with fragments
        ViewPager viewPager = findViewById(R.id.pager);
        DishFragmentPagerAdapter dishFragmentPagerAdapter = new DishFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(dishFragmentPagerAdapter);

        // Use the view pager to power a tab layout
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton floatingActionButton = findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddDishActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu with custom menu resource file
        getMenuInflater().inflate(R.menu.main_app_bar_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                showShareOptionsDialog();
                return true;
            case R.id.settings:
                Intent intent = new Intent(this, DishSettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!isAppLaunch && isLaunchThemeDark && !isNightModeEnabled) {
            isLaunchThemeDark = false;
            recreate();
        }

        isAppLaunch = false;
    }

    public void showShareOptionsDialog() {
        // Create an AlertDialog.Builder and set the title, add click listeners
        // for the different options on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.share)
                .setItems(R.array.share_options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        String dishList;
                        DishFragment dishFrag = (DishFragment) getSupportFragmentManager()
                                .findFragmentByTag("android:switcher:" + R.id.pager + ":"
                                        + which);
                        dishList = dishFrag.getDishList();

                        if (which == DishEntry.CATEGORY_COOK - 1) {
                            dishList = getString(R.string.share_cook) + "\n" + dishList;
                        } else {
                            dishList = getString(R.string.share_eat) + "\n" + dishList;
                        }

                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, dishList);
                        sendIntent.setType("text/plain");

                        Intent shareIntent = Intent.createChooser(sendIntent, null);
                        startActivity(shareIntent);
                    }
                });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}