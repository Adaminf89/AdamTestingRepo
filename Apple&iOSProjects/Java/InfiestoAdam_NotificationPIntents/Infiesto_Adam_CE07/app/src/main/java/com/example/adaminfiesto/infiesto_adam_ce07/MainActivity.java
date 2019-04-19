/*Adam S Infiesto
Java 2
MainActivity
* */
package com.example.adaminfiesto.infiesto_adam_ce07;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.adaminfiesto.infiesto_adam_ce07.ListStuff.RedditListFragment;
import com.example.adaminfiesto.infiesto_adam_ce07.Services.GetRedditService;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final String SAVE_REDDIT_INTENT = "SAVE_REDDIT_INTENT";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //start recivers
        AlarmStarter();
        RedditStarter();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment, RedditListFragment.newInstance()).commit();


    }

    private void RedditStarter()
    {
        //redditstuff start
        IntentFilter iF = new IntentFilter();
        iF.addAction(SAVE_REDDIT_INTENT);

        registerReceiver(new SavingRedditReciever(), iF);
    }

    //initial
    private void AlarmStarter()
    {
        AlarmManager mgr = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);


        if (mgr != null)
        {
            Intent serviceIntent = new Intent(this, GetRedditService.class);


            PendingIntent pi = PendingIntent.getService(this, 0, serviceIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            mgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 60, 60, pi);



        }
    }

    //for alarm restarter
    public class AlarmReciever extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

            if (mgr != null)
            {
                Intent serviceIntent = new Intent(context, GetRedditService.class);

                PendingIntent pi = PendingIntent.getService(context, 0, serviceIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                mgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 60, 60, pi);
            }
        }
    }

    public class SavingRedditReciever extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            //we only want to do this for/after the reddit save
            if(Objects.equals(intent.getAction(),SAVE_REDDIT_INTENT))
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment, RedditListFragment.newInstance()).commit();
            }
        }
    }
}
