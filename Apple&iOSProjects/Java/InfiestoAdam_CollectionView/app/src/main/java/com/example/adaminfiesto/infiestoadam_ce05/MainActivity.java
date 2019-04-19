/*Adam Infiesto
Java 1
MainActivty
* */



package com.example.adaminfiesto.infiestoadam_ce05;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FS_MA_DEMO";
    private UserItem daClick;
    private ListView mListView = null;

    // The data collection which contains the data to be displayed
    private final ArrayList<UserItem> mUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //member context will be this

        super.onCreate(savedInstanceState);

        int orientation = getResources().getConfiguration().orientation;

        Context mContxt = null;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            mContxt = this;

            populateDataCollection();
            setContentView(R.layout.newlayout_protrait);

            //values for drop down
            ArrayList<String> userView = new ArrayList<>();

            //get user names for the space into spinner
            for(int i = 0; i< mUsers.size(); ++i)
            {

                String temp = mUsers.get(i).getUserName();
                userView.add(temp);
                temp = "";
            }

            Spinner spinnerOne = findViewById(R.id.spinner);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(mContxt, android.R.layout.simple_list_item_1, userView);

            spinnerOne.setAdapter(adapter);

            spinnerOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    TextView ids = findViewById(R.id.daIDs3);
                    TextView name = findViewById(R.id.daName3);
                    TextView date = findViewById(R.id.daDate3);
                    TextView desc = findViewById(R.id.actualDesc3);
                    ImageView pic = findViewById(R.id.imageView3);

                    ids.setText(mUsers.get(position).getUserID());
                    name.setText(mUsers.get(position).getUserName());
                    date.setText(mUsers.get(position).getUserDate());
                    desc.setText(mUsers.get(position).getUserDesc());
                    pic.setImageDrawable(mUsers.get(position).getPicId());

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        else
        {
            mContxt = this;
            setContentView(R.layout.activity_mainland);

            populateDataCollection();
            //reference to the list view n grid biew
            findListView();

            setupCustomAdapterView();
            mListView.setOnItemClickListener(indexUser);
        }


    }



    private final AdapterView.OnItemClickListener indexUser = new AdapterView.OnItemClickListener()
    {
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
    //setting up cview
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

    //on load layout
    private void findListView()
    {
        Log.i(TAG, "findListView()");
        try
        {
            mListView = findViewById(R.id.listView);
        }
        catch (Exception e) {
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

                TextView id = findViewById(R.id.daIDs);
                TextView name = findViewById(R.id.daName);
                TextView date = findViewById(R.id.daDate);
                TextView desc = findViewById(R.id.actualDesc);
                ImageView pic = findViewById(R.id.imageView);

                id.setText(daClick.getUserID());
                name.setText(daClick.getUserName());
                date.setText(daClick.getUserDate());
                desc.setText(daClick.getUserDesc());
                pic.setImageDrawable(daClick.getPicId());

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

    }


    private void populateDataCollection()
    {
        Log.i(TAG, "populateDataCollection()");
        mUsers.add(new UserItem(getDrawable(R.drawable.iri),"Iri", "Caster Class Servant S-","5/16/24","0012300"));
        mUsers.add(new UserItem(getDrawable(R.drawable.kinghassan),"Hassan", "Assassin Class Servant A","2/19/18","0230910"));
        mUsers.add(new UserItem(getDrawable(R.drawable.istar),"Istar", "Archer Class Servant S","2/16/20","9800147"));
        mUsers.add(new UserItem(getDrawable(R.drawable.kiara),"Kiara", "Lancer Class Servant A+","2/16/18","8167900"));
        mUsers.add(new UserItem(getDrawable(R.drawable.tamamocat),"Tamamo Cat","Caster Class servant S+" ,"1/16/24","5568036"));
        mUsers.add(new UserItem(getDrawable(R.drawable.jailter),"Jennae D'Arc", "Avenger Class servant SS","2/16/18","7463002"));
        mUsers.add(new UserItem(getDrawable(R.drawable.kiyohime),"Kiyohime", "Berserker Class sservant S","2/16/24","772903"));
        mUsers.add(new UserItem(getDrawable(R.drawable.lobo),"Lobo", "Rider Class servant A","9/21/89","2839400"));
        mUsers.add(new UserItem(getDrawable(R.drawable.merlin),"Merlin", "Caster Class servant SS+","2/16/24","2393000"));
        mUsers.add(new UserItem(getDrawable(R.drawable.sieg),"Sieg", "Saber Class servent A-","9/21/89","8753115"));
    }
}

