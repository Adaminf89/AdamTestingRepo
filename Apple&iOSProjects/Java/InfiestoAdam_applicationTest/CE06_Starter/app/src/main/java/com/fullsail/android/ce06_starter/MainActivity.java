package com.fullsail.android.ce06_starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fullsail.android.ce06_starter.fragment.PersonListFragment;
import com.fullsail.android.ce06_starter.object.Person;

public class MainActivity extends AppCompatActivity implements PersonListFragment.OnPersonSelectedListener {

    private static final int REQUEST_FORM = 0x01001;
    private static final int REQUEST_DETAILS = 0x01002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
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

    @Override
    public void onPersonSelected(Person _person) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_FIRST_NAME, _person.getFirstName());
        intent.putExtra(DetailsActivity.EXTRA_LAST_NAME, _person.getLastName());
        intent.putExtra(DetailsActivity.EXTRA_AGE, _person.getAge());
        startActivityForResult(intent, REQUEST_DETAILS);
    }
}
