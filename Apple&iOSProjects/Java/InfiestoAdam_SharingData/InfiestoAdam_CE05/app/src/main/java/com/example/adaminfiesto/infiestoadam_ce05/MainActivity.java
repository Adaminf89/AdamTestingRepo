/*
Adam S Infiesto
Java 2
MainActivity
* */
package com.example.adaminfiesto.infiestoadam_ce05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.adaminfiesto.infiestoadam_ce05.Fragments.DataListFragment;
import com.example.adaminfiesto.infiestoadam_ce05.RedditStuff.GetRedditData;

public class MainActivity extends AppCompatActivity {


    private final String webAddress1 = "https://www.reddit.com/r/Kings_Raid/.json";
    //ArrayList<RedditData> redditArray;
    //DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, DataListFragment.newInstance()).commit();
        //start reddit task.
        GetRedditData task = new GetRedditData(MainActivity.this, webAddress1);
        task.execute();
    }

}

