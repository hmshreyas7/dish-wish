package com.example.dishwish;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.dishwish.data.DishContract.DishEntry;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EatFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EatFragment newInstance(String param1, String param2) {
        EatFragment fragment = new EatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        readDishInfo();
    }

    private void readDishInfo() {
        // Define a projection that specifies which columns from the database
        // will actually be used after this query
        String[] projection = {
                DishEntry.COLUMN_DISH_TITLE,
                DishEntry.COLUMN_DISH_TYPE
        };

        // Filter results
        String selection = DishEntry.COLUMN_CATEGORY + " = ?";
        String[] selectionArgs = {Integer.toString(DishEntry.CATEGORY_EAT)};

        // How the results should be sorted in the resulting Cursor
        String sortOrder = DishEntry.COLUMN_DISH_TITLE;

        Cursor cursor = getContext().getContentResolver().query(DishEntry.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                sortOrder);

        TextView databaseInfoView = getView().findViewById(R.id.database_info);
        databaseInfoView.setText("");

        try {
            // Get index of each column in Cursor
            int dishTitleColumnIndex = cursor.getColumnIndex(DishEntry.COLUMN_DISH_TITLE);
            int dishTypeColumnIndex = cursor.getColumnIndex(DishEntry.COLUMN_DISH_TYPE);

            // Read values from Cursor
            while (cursor.moveToNext()) {
                String dishTitle = cursor.getString(dishTitleColumnIndex);
                int dishType = cursor.getInt((dishTypeColumnIndex));
                String values = dishTitle + " " + dishType + "\n";
                databaseInfoView.append(values);
            }
        } finally {
            cursor.close();
        }
    }
}
