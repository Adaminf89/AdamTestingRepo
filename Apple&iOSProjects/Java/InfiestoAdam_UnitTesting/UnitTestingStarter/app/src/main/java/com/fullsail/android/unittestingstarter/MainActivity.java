package com.fullsail.android.unittestingstarter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.fullsail.android.unittestingstarter.fragment.PersonListFragment;
import com.fullsail.android.unittestingstarter.util.PersonFormatUtil;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_FORM = 0x01001;
    private static final int REQUEST_PREF = 0x01002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // test();
        if(savedInstanceState == null)
        {
            PersonListFragment fragment = PersonListFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment, PersonListFragment.TAG)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(this, FormActivity.class);
            startActivityForResult(intent, REQUEST_FORM);
        } else if(item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent, REQUEST_PREF);
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        PersonListFragment fragment = (PersonListFragment)
                getFragmentManager().findFragmentByTag(PersonListFragment.TAG);

        if(fragment != null) {
            fragment.refresh();
        }
    }

    public static int DASHES = PersonFormatUtil.FORMAT_ALL_DASHES;
    public static int PARENS = PersonFormatUtil.FORMAT_WITH_PARENS;
    public static int SPACES = PersonFormatUtil.FORMAT_WITH_SPACES;

//    public void test()
//    {
//        String failedNumbers[] = {"1-407-555-0123", "40755501234", "407555012"};
//
//        Log.d("TAG", "test1: "+PersonFormatUtil.isPhoneNumberValid(failedNumbers[0]));
//        Log.d("TAG", "test2: "+PersonFormatUtil.isPhoneNumberValid(failedNumbers[1]));
//        Log.d("TAG", "test3: "+PersonFormatUtil.isPhoneNumberValid(failedNumbers[2]));
//
//    }
}
