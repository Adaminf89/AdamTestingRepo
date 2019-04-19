//Adam S Infiesto
//JAVA 2
//FragForm

package com.example.adaminfiesto.infiesto_adam_ce02.Fragmen;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adaminfiesto.infiesto_adam_ce02.Employees;
import com.example.adaminfiesto.infiesto_adam_ce02.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class FragForm extends Fragment implements DatePickerDialog.OnDateSetListener
{

    private EditText mNmae ;
    private EditText mLastName;
    private EditText mNumber;
    private EditText mStatus;
    //datestuff
    private EditText mDay;
    private EditText mMonth;
    private EditText mYear;
    // --Commented out by Inspection (5/3/18, 11:03 PM):final Calendar c = Calendar.getInstance();
    private FormData mActivity;

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    }


    //sending back the form Data
    public interface FormData
    {
        void passEmployee(Employees employ);
    }

    public static FragForm newInstance() {

        Bundle args = new Bundle();
        FragForm fragment = new FragForm();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.from_fragment,container,false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menusave, menu);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //set up elements on page
        mNmae = Objects.requireNonNull(getView()).findViewById(R.id.txtFname);
        mLastName = getView().findViewById(R.id.txtLname);
        mNumber = getView().findViewById(R.id.txtNumber);
        mStatus = getView().findViewById(R.id.txtStatus);
        mMonth = getView().findViewById(R.id.monthField);
        mDay =  getView().findViewById(R.id.dayField);
        mYear=  getView().findViewById(R.id.yearField);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //if past type is the same as whats required

        if(context instanceof  FormData)
        {    //let this act data = the context
            mActivity =(FormData) context;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Integer itemId = item.getItemId();

        //saves the user to activity and saves
        if(itemId == R.id.form_menu_save)
        {
            if(mNmae.getText().toString().isEmpty() || mLastName.getText().toString().isEmpty()||mNumber.getText().toString().isEmpty()
                    || mStatus.getText().toString().isEmpty() || mMonth.getText().toString().isEmpty() ||
                    mDay.getText().toString().isEmpty() || mYear.getText().toString().isEmpty())
            {
                Toast.makeText(getContext(), R.string.Toast, Toast.LENGTH_LONG).show();
            }
            else
                {
                //get user text
                String daName = mNmae.getText().toString();
                String daLegs = mLastName.getText().toString();
                Integer daENumber = Integer.parseInt(mNumber.getText().toString());
                String daStatus = mStatus.getText().toString();

//                    Integer month = Integer.parseInt(mMonth.getText().toString());
//                    Integer day = Integer.parseInt(mDay.getText().toString());
//                    Integer year = Integer.parseInt(mYear.getText().toString());

                String date = mMonth.getText().toString() + "/" + mDay.getText().toString() + "/" + mYear.getText().toString();

                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

                Date convertDate = new Date();
                try
                {
                    convertDate = dateFormat.parse(date);
                } catch (ParseException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                //send to class
                Employees newEmployee = new Employees(daName, daLegs, daENumber, daStatus, convertDate.toString());

                mActivity.passEmployee(newEmployee);
            }
        }

        return true;

    }



}


