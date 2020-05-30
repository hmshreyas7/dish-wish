package com.example.dishwish;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.dishwish.data.DishContract.DishEntry;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DishFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DishFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String ARG_CATEGORY = "category";

    private static final int DISH_LOADER = 0;
    private DishCursorAdapter dishCursorAdapter;

    private int dishCategory;

    public DishFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param category Category of the dish
     * @return A new instance of fragment DishFragment
     */
    public static DishFragment newInstance(int category) {
        DishFragment fragment = new DishFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dishCategory = getArguments().getInt(ARG_CATEGORY);
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

        // Open AddDishActivity when ListView item is clicked
        dishListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getContext(), AddDishActivity.class);
                Uri currentDishUri = ContentUris.withAppendedId(DishEntry.CONTENT_URI, id);
                intent.setData(currentDishUri);
                startActivity(intent);
            }
        });
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
        String[] selectionArgs = {Integer.toString(dishCategory)};

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
