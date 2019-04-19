/*Adam S Infiesto
* Java 2
* MainActivity */

package com.example.adaminfiesto.infiestoadamce01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mlstView;
    private View daListView;
    private TextView daEmptyView;
    private String web;
    private final String webAddress1 = "https://www.reddit.com/r/Kings_Raid/.json";
    private final String webAddress2 = "https://www.reddit.com/r/gameDevClassifieds/hot.json";
    private final String webAddress3 = "https://www.reddit.com/r/MonsterHunter/.json";
    private static final String TAG = "TAG";
    private String KEY = "";


    //the adapter is an arraylist of redditData
    private ArrayList<RedditData> reddit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daListView = findViewById(R.id.listofReddit);

        daEmptyView =findViewById(R.id.isHidden);

        Spinner spinnerOne = findViewById(R.id.spinner);
        //set spinner with array data from R string
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.picker_adapterarray,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //add the spinner
        spinnerOne.setAdapter(adapter);

        spinnerOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0)
                {
                    KEY = "KEY1";
                    web = webAddress1;

                    GetRedditData task = new GetRedditData(MainActivity.this, web);
                    task.execute();

                }
                else if (position == 1)
                {
                    KEY = "KEY2";
                    web = webAddress2;
                    GetRedditData task = new GetRedditData(MainActivity.this, web);
                    task.execute();
                }
                else
                {

                    KEY = "KEY3";
                    web = webAddress3;
                    GetRedditData task = new GetRedditData(MainActivity.this, web);
                    task.execute();

                }
                //listview frag with reddit arraylist
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,listFragment.newInstance(reddit)).commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void SaveSerializable(ArrayList<RedditData> redditData)
    {

        try
        {

            FileOutputStream fso = openFileOutput(KEY, MODE_PRIVATE);

            ObjectOutputStream oos = new ObjectOutputStream(fso);

            oos.writeObject(redditData);

            oos.close();
            fso.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void LoadSerializable()
    {

        try
        {

            FileInputStream fis = openFileInput(KEY);

            ObjectInputStream oos =  new ObjectInputStream(fis);

            reddit =  (ArrayList<RedditData>) oos.readObject();

            oos.close();
            fis.close();

        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        //check to see if the array listhas data already
        if(reddit != null)
        {

            mlstView.setAdapter(new RedditAdapter(this, reddit));
            daEmptyView.setVisibility(View.INVISIBLE);
            mlstView.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Data", Toast.LENGTH_SHORT).show();
        }
        else
            {
                daEmptyView.setVisibility(View.VISIBLE);
                mlstView.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            }
    }


    public void findListView(ArrayList<RedditData> _reddit)
    {
        Log.i(TAG, "findListView()");
        try
        {

            mlstView = daListView.findViewById(android.R.id.list);
            //set Adapter with current context and serial reddit arraylist
            mlstView.setAdapter(new RedditAdapter(this, _reddit));
            //make frag listview show
            mlstView.setVisibility(View.VISIBLE);
            daEmptyView.setVisibility(View.INVISIBLE);

        }
        catch (Exception e)
        {
            Log.e(TAG, "Error finding view for list demo.\n");
            e.printStackTrace();
        }
    }


}
