//Adam S Infiesto
//JAVA 2
//ListFrag

package com.example.adaminfiesto.infiesto_adam_ce02.Fragmen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.adaminfiesto.infiesto_adam_ce02.DatabaseHelper;
import com.example.adaminfiesto.infiesto_adam_ce02.DetailActivity;
import com.example.adaminfiesto.infiesto_adam_ce02.Employees;
import com.example.adaminfiesto.infiesto_adam_ce02.R;

import java.util.ArrayList;

public class ListFrag extends ListFragment
{
    private ArrayList<Employees> mUserEmployee = new ArrayList<>();
    private ArrayAdapter<Employees> mEmployeeAdapater;


    //key stream forward
    private static final String DELETEF = "DELETEF";
    //key stream back
    private static final String DELETEB = "DELETEB";

    private DatabaseHelper dbh;

    public ListFrag()
    {
    }

    public interface daArrayInter
    {
        ArrayList<Employees> getlist();
    }

    public static ListFrag newInstance() {

        Bundle args = new Bundle();

        ListFrag fragment = new ListFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //get the currect context we're looking for
        daArrayInter mArray = (daArrayInter)context;

        dbh = DatabaseHelper.getInstance(context);
        mUserEmployee = mArray.getlist();

        setListAdapter(mEmployeeAdapater = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mUserEmployee));
    }

    @Override
    public void onResume() {
        super.onResume();
        mEmployeeAdapater.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.list_fragment, container, false);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1)
        {
            int index = data.getIntExtra(DELETEB, 0);

            //delete item
            dbh.deleteName(index);
            //mUserEmployee.remove(index);
        }
        else
            {

            mEmployeeAdapater.notifyDataSetChanged();
            return;
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //get the intent of where u where to go
        Intent daIntent = new Intent(getActivity(), DetailActivity.class);

        //current items selected matches the employee by the position
        Employees currentEmp = mUserEmployee.get(position);

        //send the employee to the place u want
        daIntent.putExtra(Intent.EXTRA_TEXT, currentEmp);

        //start the activity by key
        daIntent.putExtra(DELETEF, position);
        startActivityForResult(daIntent, 2);
    }
}

