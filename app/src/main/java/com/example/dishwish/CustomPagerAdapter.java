package com.example.dishwish;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CustomPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public CustomPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    /**
     * Returns the number of fragments compiled together in the adapter.
     *
     * @return the number of fragments
     */
    @Override
    public int getCount() {
        return 2;
    }

    /**
     * Returns an instance of the appropriate fragment based on its position in the adapter.
     *
     * @param position the position of a fragment in the adapter
     * @return a new fragment instance
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new CookFragment();
        } else {
            return new EatFragment();
        }
    }

    /**
     * Returns the relevant page title for a fragment in the adapter based on its position.
     *
     * @param position the position of a fragment in the adapter
     * @return a fragment's page title
     */
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.cook);
            case 1:
                return mContext.getString(R.string.eat);
            default:
                return null;
        }
    }
}
