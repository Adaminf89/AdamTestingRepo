//Adam S Infiesto
//JAVA 2
//FragDetail

package com.example.adaminfiesto.infiesto_adam_ce02.Fragmen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adaminfiesto.infiesto_adam_ce02.EditActivity;
import com.example.adaminfiesto.infiesto_adam_ce02.Employees;
import com.example.adaminfiesto.infiesto_adam_ce02.R;

public class FragDetail extends Fragment
{
    //need to hold class items coming in
    private Employees mEmployee;
    //set up interface var for deleting
    private BackData mdelete;
    // --Commented out by Inspection (5/3/18, 11:03 PM):private final int SCREEN_FORM = 1;
    private static final String FNAME = "FNAME";
    private static final String LNAME = "LNAME";
    // --Commented out by Inspection (5/3/18, 11:03 PM):private static final String NUMBER = "NUMBER";
    private static final String STATUS = "STATUS";
    private static final String CLASS = "CLASS";
    private static final String DATE = "DATE";
    // --Commented out by Inspection (5/3/18, 11:03 PM):Date date;

    public static FragDetail newInstance() {

        Bundle args = new Bundle();

        FragDetail fragment = new FragDetail();
        fragment.setArguments(args);
        return fragment;
    }

    public interface BackData
    {
        Employees getSelected();
        void Delete();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //select BackData to the current context
        mdelete = (BackData)context;

        //items in monster class is the contexted item
        mEmployee = mdelete.getSelected();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.detail_fragment,container, false);
        View daView = inflater.inflate(R.layout.detail_fragment, container, false);

        //set up elements on page
        TextView mNmae = daView.findViewById(R.id.txtName2);
        TextView mLname = daView.findViewById(R.id.txtLname2);
        TextView mStatus = daView.findViewById(R.id.txtStat);
        TextView mdate = daView.findViewById(R.id.txtDate2);


        //set user text
        mNmae.setText(mEmployee.getmName());
        mLname.setText(mEmployee.getmLast());
        mStatus.setText(mEmployee.getmStatus());
        mdate.setText(mEmployee.getmDate());


        return daView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.detailpage, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Integer itemId = item.getItemId();
        Intent daIntent;

        if(itemId == R.id.form_menu_delete)
        {
            //run the interface
            mdelete.Delete();
        }
        else
        {
            daIntent = new Intent(getActivity(), EditActivity.class);
            //store all data before passing
            daIntent.putExtra(CLASS, this.mEmployee);
            daIntent.putExtra(FNAME, mEmployee.getmName());
            daIntent.putExtra(LNAME, mEmployee.getmLast());
            daIntent.putExtra(STATUS, mEmployee.getmStatus());
            daIntent.putExtra(DATE, mEmployee.getmDate());

            daIntent.setType("text/plain");
            startActivity(daIntent);

        }
        return true;
    }
}
