//Adam S Infiesto
//JAVA 2
//MainActivity

package com.example.adaminfiesto.infiesto_adam_ce02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.adaminfiesto.infiesto_adam_ce02.Fragmen.ListFrag;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListFrag.daArrayInter
{

    private ArrayList<Employees> daEmployee;
    //helps defines which screen this one can go too
    private final int SCREEN_FORM = 1;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Spinner spinnerOne = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.picker_layoutarray,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //add the spinner
        spinnerOne.setAdapter(adapter);

        //right spinner for type of adapter
        Spinner spinnertwo = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.picker_adapterarray,
                android.R.layout.simple_spinner_item);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set spin 2
        spinnertwo.setAdapter(adapter2);

        spinnerOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0)
                {

                    getSupportFragmentManager().beginTransaction().replace(R.id.frameForm, ListFrag.newInstance()).commit();
                }
                else
                {

                    helper.SortOrder();
                    Log.i("MyAndroidClass", daEmployee.toString());
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameForm, ListFrag.newInstance()).commit();
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //????
            }
        });

        spinnertwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0)
                {
                    //all
                    getlist();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameForm, ListFrag.newInstance()).commit();
                }
                else
                {
                    helper.SelectActive();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameForm, ListFrag.newInstance()).commit();
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //????
            }
        });

    }


    //create the view menu item
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainpage, menu);
        return true;
    }

    //create the menuoptions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Integer itemId = item.getItemId();
        Intent daIntent;
        //GO TO the FormActivity/Frag
        if(itemId == R.id.form_menu_add)
        {
            daIntent = new Intent(MainActivity.this, FormActivity.class);
            startActivityForResult(daIntent, SCREEN_FORM);

        }
        else
        {
            daIntent = new Intent(MainActivity.this, PreferanceActivity.class);
            int SCREEN_PREF = 3;
            startActivityForResult(daIntent, SCREEN_PREF);
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //check if anyof this can actually happen
        if(resultCode == RESULT_OK)
        {
            //define which screen this one can go too
            Employees listEmp;

            if(requestCode == SCREEN_FORM)
            {
                listEmp = (Employees) data.getSerializableExtra(FormActivity.EXTRA_M_NAME);
                daEmployee.add(listEmp);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameForm, ListFrag.newInstance()).commit();
            }
            else
            {
                //we have to be going to the details if not the startup
                listEmp = (Employees) data.getSerializableExtra(DetailActivity.DELETEB);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameForm, ListFrag.newInstance()).commit();
                daEmployee.add(listEmp);
            }
        }
    }



    @Override
    public ArrayList<Employees> getlist()
    {

        helper = DatabaseHelper.getInstance(this);

        daEmployee = helper.getEmployee();

        return daEmployee;
    }
}
