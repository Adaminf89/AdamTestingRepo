/*Adam S Infiesto
* JAVA2
* Prefragment*/

package com.example.adaminfiesto.infiesto_adam_ce02.Fragmen;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.adaminfiesto.infiesto_adam_ce02.DatabaseHelper;
import com.example.adaminfiesto.infiesto_adam_ce02.MainActivity;
import com.example.adaminfiesto.infiesto_adam_ce02.R;

import static android.content.ContentValues.TAG;

public class PrefsFragment extends PreferenceFragment {

    // --Commented out by Inspection (5/3/18, 11:03 PM):private static final String TAG = "MainActivity";
    // --Commented out by Inspection (5/3/18, 11:03 PM):private static final String PREFS = "SharedPreferences.PREFS";
    private ListPreference listPreference;
    private Preference genPref;
    private DatabaseHelper dataHelper;
    // --Commented out by Inspection (5/3/18, 11:03 PM):private Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
        //never used due to couldnt figure out the date in Database
        SharedPreferences customPrefs = getPreferenceManager().getSharedPreferences();
        //SharedPreferences.Editor editor = customPrefs.edit();
        String date = customPrefs.getString("DATEKEY", "MM-dd-yyyy");
        listPreference = (ListPreference) findPreference("DATEKEY");
        genPref = findPreference("DELETEBTN");
        Log.d(TAG, "onCreate: "+date);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue)
            {
                preference.setSummary(newValue.toString());
                return true;
            }
        });

        genPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Delete All");
                builder.setMessage("ALL CONTENT WILL BE LOST");
                builder.setNegativeButton("Cancel",null);
                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dataHelper = DatabaseHelper.getInstance(getActivity());
                        dataHelper.deleteAll();
                        //dataHelper.close();
                        goBack();
                    }
                });

                builder.show();
                return false;
            }
            //goBack();
        });

    }

    private void goBack()
    {
        Intent daIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(daIntent);
    }

}
