package com.fullsail.android.ce06_starter.fragment;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fullsail.android.ce06_starter.object.Person;
import com.fullsail.android.ce06_starter.util.PersonStorageUtil;

import java.util.ArrayList;

public class PersonListFragment extends ListFragment {

    public static final String TAG = "PersonListFragment.TAG";

    public static PersonListFragment newInstance() {
        return new PersonListFragment();
    }

    public interface OnPersonSelectedListener {
        void onPersonSelected(Person _person);
    }

    private OnPersonSelectedListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnPersonSelectedListener) {
            mListener = (OnPersonSelectedListener)context;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText("No Data Available");
        refresh();
    }

    public void refresh() {
        ArrayList<Person> people = PersonStorageUtil.loadPeople(getActivity());
        ArrayAdapter<Person> peopleAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, people);
        setListAdapter(peopleAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Person person = (Person) l.getAdapter().getItem(position);
        if(mListener != null) {
            mListener.onPersonSelected(person);
        }
    }
}
