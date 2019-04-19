package com.fullsail.android.ce06_starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.fullsail.android.ce06_starter.fragment.DetailsFragment;
import com.fullsail.android.ce06_starter.object.Person;
import com.fullsail.android.ce06_starter.util.PersonStorageUtil;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_FIRST_NAME = "com.fullsail.android.EXTRA_FIRST_NAME";
    public static final String EXTRA_LAST_NAME = "com.fullsail.android.EXTRA_LAST_NAME";
    public static final String EXTRA_AGE = "com.fullsail.android.EXTRA_AGE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {

            Intent intent = getIntent();

            String first = intent.getStringExtra(EXTRA_FIRST_NAME);
            String last = intent.getStringExtra(EXTRA_LAST_NAME);
            int age = intent.getIntExtra(EXTRA_AGE, 0);

            Person person = new Person(first, last, age);

            DetailsFragment fragment = DetailsFragment.newInstance(person);
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment, DetailsFragment.TAG)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_delete) {
            DetailsFragment fragment = (DetailsFragment)getFragmentManager()
                    .findFragmentByTag(DetailsFragment.TAG);
            if(fragment != null) {
                Person person = fragment.getPerson();
                PersonStorageUtil.deletePerson(this, person);
                setResult(RESULT_OK);
                finish();
            }
        }

        return true;
    }
}
