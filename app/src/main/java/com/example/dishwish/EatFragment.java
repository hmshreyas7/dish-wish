package com.example.dishwish;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.dishwish.data.DishContract.DishEntry;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EatFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int DISH_LOADER = 0;
    private DishCursorAdapter dishCursorAdapter;

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

        // Prepare the loader. Either re-connect with an existing one,
        // or start a new one.
        LoaderManager.getInstance(this).initLoader(DISH_LOADER, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Use ListView to display details from database
        ListView dishListView = view.findViewById(R.id.database_info);
        dishCursorAdapter = new DishCursorAdapter(getContext(), null);
        dishListView.setAdapter(dishCursorAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // URI for the content to retrieve
        Uri uri = DishEntry.CONTENT_URI;

        // Define a projection that specifies which columns from the database
        // will actually be used after this query
        String[] projection = {
                DishEntry._ID,
                DishEntry.COLUMN_DISH_TITLE,
                DishEntry.COLUMN_DISH_TYPE
        };

        // Filter results
        String selection = DishEntry.COLUMN_CATEGORY + " = ?";
        String[] selectionArgs = {Integer.toString(DishEntry.CATEGORY_EAT)};

        // How the results should be sorted in the resulting Cursor
        String sortOrder = DishEntry.COLUMN_DISH_TITLE;

        // Create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        return new CursorLoader(getActivity(),
                uri,
                projection,
                selection,
                selectionArgs,
                sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Update {@link DishCursorAdapter} with this new cursor containing updated dish data
        dishCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Callback called when the data needs to be deleted
        dishCursorAdapter.swapCursor(null);
    }
}
