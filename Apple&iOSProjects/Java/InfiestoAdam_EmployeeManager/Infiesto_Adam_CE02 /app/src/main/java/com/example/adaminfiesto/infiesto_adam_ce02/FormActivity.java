//Adam S Infiesto
//JAVA 2
//FormActivity

package com.example.adaminfiesto.infiesto_adam_ce02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.adaminfiesto.infiesto_adam_ce02.Fragmen.FragForm;

public class FormActivity extends AppCompatActivity  implements FragForm.FormData{

    public static final String EXTRA_M_NAME = "EXTRA_M_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.form_layout);
        //opens up the create form
        getSupportFragmentManager().beginTransaction().replace(R.id.frameForm2, FragForm.newInstance()).commit();
    }

    @Override
    public void passEmployee(Employees employ)
    {
        DatabaseHelper dbh = DatabaseHelper.getInstance(this);
        dbh.insertArticle(employ);

        Intent daForm = new Intent();

        daForm.putExtra(EXTRA_M_NAME, employ);

        setResult(RESULT_OK, daForm);

        finish();

    }
}
