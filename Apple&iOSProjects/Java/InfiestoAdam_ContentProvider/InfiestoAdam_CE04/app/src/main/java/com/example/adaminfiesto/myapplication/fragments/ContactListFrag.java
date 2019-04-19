/*
Adam S Infiesto
JAVA 2
ContactListFrag
* */
package com.example.adaminfiesto.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adaminfiesto.myapplication.Class.Contact;
import com.example.adaminfiesto.myapplication.R;

import java.util.ArrayList;
import java.util.Objects;

public class ContactListFrag extends ListFragment
{


    private static final String ARG_People = "ARGP";

    private ArrayList<Contact> daData;

    private ListFragmentItemClickListener mfaceItemClickListener;

    public ContactListFrag()
    {
    }

    /** An interface for defining the callback method */
    public interface ListFragmentItemClickListener
    {
        /** This method will be invoked when an item in the ListFragment is clicked */
        void onListFragmentItemClick(String position);
    }

    public static ContactListFrag newInstance(ListFragmentItemClickListener clickListener, ArrayList<Contact> daData)
    {
        Bundle args = new Bundle();

        args.putSerializable(ARG_People, daData);

        ContactListFrag fragment = new ContactListFrag();

        fragment.mfaceItemClickListener = clickListener;

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {
            daData = (ArrayList<Contact>) getArguments().getSerializable(ARG_People);
            //baseadapter to display contact
            ContactAdapter cA = new ContactAdapter(getActivity(),daData);
            //set adapter
            setListAdapter(cA);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.listview_frag, container, false);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        daData = (ArrayList<Contact>) getArguments().getSerializable(ARG_People);

        Contact currentContact = Objects.requireNonNull(daData).get(position);

        mfaceItemClickListener.onListFragmentItemClick(currentContact.getId());

    }
}
