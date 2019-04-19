package com.example.adaminfiesto.infiestoadam_ce04;
//ADam S Infiesto
//Java 1
//MainActivity
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FS_MA_DEMO";
    // Reference to the current layout's list view
    private UserItem daClick;
    private ListView mListView = null;
    private GridView mGridView = null;
    private Context mContxt = null;
    // The data collection which contains the data to be displayed

    private final ArrayList<UserItem> mUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        mContxt = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateDataCollection();

        //reference to the list view n grid biew
        findListView();
        findGridView();


        //setupSimpleAdapterView();
        setupCustomAdapterView();
        //add spin one
        Spinner spinnerOne = findViewById(R.id.spinOne);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.picker_layoutarray,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //add the spinner
        spinnerOne.setAdapter(adapter);




        //right spinner for type of adapter
        Spinner spinnertwo = findViewById(R.id.spinnerTwo);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.picker_adapterarray,
                android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set spin 2
        spinnertwo.setAdapter(adapter2);

        //when the user would "SELECT" no click item
        spinnerOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0)
                {
                    mListView.setVisibility(View.VISIBLE);
                    mGridView.setVisibility(View.GONE);



                }
                else
                {
                    mListView.setVisibility(View.GONE);
                    mGridView.setVisibility(View.VISIBLE);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //????
            }
        });

        spinnertwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(position == 0)
                {
                    //cusstom adapter everthing
                    UserAdapter uA = new UserAdapter(mContxt, mUsers);
                    // Inform the list view of its new adapter
                    mListView.setAdapter(uA);
                    mGridView.setAdapter(uA);


                }
                else if(position == 1)
                {
                    //simple adapter name and bday
                    setupSimpleAdapterView();
                }
                else
                {

                    //Array last Name
                    ArrayAdapter<UserItem> aA = new ArrayAdapter<>(mContxt, android.R.layout.simple_list_item_1, mUsers);
                    mListView.setAdapter(aA);
                    mListView.setAdapter(aA);

                }

                mListView.setOnItemClickListener(indexUser);
                mGridView.setOnItemClickListener(indexUser);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        if(mListView.getSelectedItem() == true)
//        {
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            builder.setIcon()
//        }

    }

    private final AdapterView.OnItemClickListener indexUser = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            //this use will be the index of clicked pos
            daClick = (UserItem)parent.getItemAtPosition(position);

            //add alert info
            alertView();

            //clear the index
            daClick = null;
        }


    };
    //ref from starter project
    private void setupSimpleAdapterView() {
        Log.i(TAG, "setupAdapterView()");
       // Field identifiers used as "keys" in the hash map
        // They never change after declaration so don't let them
        final String keyName = "nickName";
        final String keyAge = "age";
        final String keyImage = "balance";

        // A list of hash maps, one map per entry in the list view
        ArrayList<HashMap<String, Object>> dataCollection = new ArrayList<>();

        // Iterate through each account and map its data to the appropriate keys
        // This will be the simple adapters internal storage of our data
        for(UserItem account : mUsers)
        {
            // New hash map for this specific account
            HashMap<String, Object> map = new HashMap<>();


            // Associate the account data with the appropriate key
            map.put(keyName, account.getUserName());
            map.put(keyAge, account.getUserDesc());
            map.put(keyImage, account.getPicId());

            dataCollection.add(map);
        }

        // Create collection of our keys so the adapter knows what to
        // map between the above hashmap and the views.
        // NOTE: Order matters. First key maps to first view, second key second view, etc...
        String[] keys = new String[] {
                keyName,
                keyAge,
                keyImage
        };

        // Create collection of the list view's item layout IDs so the adapter knows where to
        // map the data to.
        // NOTE: Order matters. First ID retrieves data using the first key, 2nd ID from 2nd key, etc...
        int[] viewIds = new int[] {
                R.id.txtLTitle,
                R.id.txtLDesc,
                R.id.picLView
        };

        // Instantiate a new SimpleAdapter which will translate the data from the hasmaps to our views
        SimpleAdapter adapter = new SimpleAdapter(this, dataCollection, R.layout.array_temp, keys, viewIds);

        // Inform the list view of its new adapter
        mListView.setAdapter(adapter);
        SimpleAdapter adapter2 = new SimpleAdapter(this, dataCollection, R.layout.gridview_temp, keys, viewIds);
        mGridView.setAdapter(adapter2);
    }

    private void setupCustomAdapterView()
    {
        if(mListView != null)
        {
            //Create the adapter
            UserAdapter uA = new UserAdapter(this, mUsers);
            //Inform the list view of its new adapter
            mListView.setAdapter(uA);
        }
    }

    private void findListView()
    {
        Log.i(TAG, "findListView()");
        try
        {
            mListView = findViewById(R.id.listview_temp);
        }
        catch (Exception e) {
            Log.e(TAG, "Error finding view for list demo.\n");
            e.printStackTrace();
        }
    }

    private void findGridView()
    {
        Log.i(TAG, "findListView()");
        try
        {
            mGridView = findViewById(R.id.gridview_temp);
        }
        catch (Exception e)
        {
            Log.e(TAG, "Error finding view for list demo.\n");
            e.printStackTrace();
        }
    }

    private void alertView() {
        //that user current

        if(daClick != null)
        {
            try
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(daClick.picId);
                builder.setMessage(daClick.getUserDesc());
                builder.setTitle(daClick.getUserName());
                builder.setPositiveButton("Close", null);
                builder.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

    }


    private void populateDataCollection() {
        Log.i(TAG, "populateDataCollection()");
        mUsers.add(new UserItem(getDrawable(R.drawable.ic_launcher_background),"Saber", "5/16/24"));
        mUsers.add(new UserItem(getDrawable(R.drawable.ic_launcher_background),"Jenna", "2/19/18"));
        mUsers.add(new UserItem(getDrawable(R.drawable.ic_launcher_background),"Theo", "2/16/20"));
        mUsers.add(new UserItem(getDrawable(R.drawable.ic_launcher_background),"Jane", "2/16/18"));
        mUsers.add(new UserItem(getDrawable(R.drawable.ic_launcher_background),"Urza", "1/16/24"));
        mUsers.add(new UserItem(getDrawable(R.drawable.ic_launcher_background),"Scarlett", "2/16/18"));
        mUsers.add(new UserItem(getDrawable(R.drawable.ic_launcher_background),"Cu Culaian", "2/16/24"));
        mUsers.add(new UserItem(getDrawable(R.drawable.ic_launcher_background),"Sasori", "9/21/89"));
        mUsers.add(new UserItem(getDrawable(R.drawable.ic_launcher_background),"Kammie", "2/16/24"));
        mUsers.add(new UserItem(getDrawable(R.drawable.ic_launcher_background),"Guile", "9/21/89"));
    }
}
