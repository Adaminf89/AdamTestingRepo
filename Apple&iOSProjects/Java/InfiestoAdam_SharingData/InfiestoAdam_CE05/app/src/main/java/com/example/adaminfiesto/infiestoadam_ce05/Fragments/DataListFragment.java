/*Adam S Infiesto
 * Java 2
 * DataListFragment */
package com.example.adaminfiesto.infiestoadam_ce05.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adaminfiesto.infiestoadam_ce05.DataBase.InBoundContract;
import com.example.adaminfiesto.infiestoadam_ce05.GoogleBooks.Books;
import com.example.adaminfiesto.infiestoadam_ce05.R;

import java.util.ArrayList;
import java.util.Objects;

//Note: dont name list fragment ListFragment
// TODO: 4/30/18 refactor. 
public class DataListFragment extends ListFragment
{
    // --Commented out by Inspection (5/12/18, 7:40 PM):private static final String ARG_People = "ARGP";

    private ArrayList<Books> daData;
    
    public DataListFragment() {
    }


    public static DataListFragment newInstance() {

        Bundle args = new Bundle();

        DataListFragment fragment = new DataListFragment();

        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        //Instance for book data
        daData = new ArrayList<>();

        //set cursor for to get data from google books
        Cursor bCur = Objects.requireNonNull(getContext()).getContentResolver().query(InBoundContract.BOOK_CONTENT_URI,null,null,null,null);

        //get cursor data with contrack keys
        while(Objects.requireNonNull(bCur).moveToNext())
        {

            String bTitle = bCur.getString(bCur.getColumnIndex(InBoundContract.BOOK_TITLE));
            String bDesc = bCur.getString(bCur.getColumnIndex(InBoundContract.BOOK_DESCRIPTION));
            String bImage = bCur.getString(bCur.getColumnIndex(InBoundContract.BOOK_THUMBNAIL));
            //String bDesc = "test";
            //add to to array
            daData.add(new Books(bTitle, bImage, bDesc));
        }


        //setAdapter
        DisplayAdapter bookAdapter = new DisplayAdapter(getContext(), daData);
        setListAdapter(bookAdapter);

    }
}
