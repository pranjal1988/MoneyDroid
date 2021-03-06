package com.moneydroid.app.ui;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.text.Selection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import com.moneydroid.app.R;
import com.moneydroid.app.provider.TransactionContract;

/**
 * Created by ashu on 23/5/14.
 */
public class ExpenseListFragment extends ListFragment implements
        LoaderManager.LoaderCallbacks<Cursor>{

    SimpleCursorAdapter mTransactionAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTransactionAdapter = new SimpleCursorAdapter(getActivity(),
                R.layout.list_item_transaction,
                null,
                null,
                null);

    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        String selection = null;
        return new CursorLoader(getActivity(),
                TransactionContract.CONTENT_URI,
                TransactionQuery.PROJECTION,
                null, // current selection is null, will be changed later
                null,
                null);

    }

    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {
        if(!isAdded()) {
            return;
        }

        mTransactionAdapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

     private interface TransactionQuery {

        String [] PROJECTION = {
                TransactionContract.TransactionColumns.TRANSACTION_ID,
                TransactionContract.TransactionColumns.TITLE,
                TransactionContract.TransactionColumns.DESC,
                TransactionContract.TransactionColumns.AMOUNT
        };
    }
}
