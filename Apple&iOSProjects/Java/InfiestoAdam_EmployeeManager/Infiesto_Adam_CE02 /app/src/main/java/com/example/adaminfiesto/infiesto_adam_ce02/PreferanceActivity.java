//Adam S Infiesto
//JAVA 2
//PreferanceActivity

package com.example.adaminfiesto.infiesto_adam_ce02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.adaminfiesto.infiesto_adam_ce02.Fragmen.PrefsFragment;

public class PreferanceActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferance_layout);

        getFragmentManager().beginTransaction().replace(R.id.prefFrame, new PrefsFragment()).commit();
    }
}
