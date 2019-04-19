//Adam S Infiesto
//JAVA 2
//EditActivity

package com.example.adaminfiesto.infiesto_adam_ce02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity
{

    // --Commented out by Inspection (5/3/18, 11:03 PM):private static final String FNAME = "FNAME";
    // --Commented out by Inspection (5/3/18, 11:03 PM):private static final String LNAME = "LNAME";
    // --Commented out by Inspection (5/3/18, 11:03 PM):private static final String NUMBER = "NUMBER";
    // --Commented out by Inspection (5/3/18, 11:03 PM):private static final String STATUS = "STATUS";
    private static final String CLASS = "CLASS";
    // --Commented out by Inspection (5/3/18, 11:03 PM):private static final String DATE = "DATE";
    // --Commented out by Inspection (5/3/18, 11:03 PM):Intent passedIntent = getIntent();
    private Employees em;
    private EditText etName = null;
    private EditText etLname = null;
    // --Commented out by Inspection (5/3/18, 11:03 PM):EditText etNUMBER = null;
    private EditText etSTATUS = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_form);

        //getSupportFragmentManager().beginTransaction().replace(R.id.frameForm2, FragForm.newInstance()).commit();
    }


    @Override
    protected void onStart() {
        super.onStart();

        etName = findViewById(R.id.EdittxtFname);
        etLname = findViewById(R.id.EdittxtLname);
        etSTATUS = findViewById(R.id.EdittxtStatus);

        //CAST the passed intent as the serializable class of employees
        em = (Employees) getIntent().getSerializableExtra(CLASS);
        //show text in the Intent object in the View
        etName.setText(this.em.getmName());
        etLname.setText(this.em.getmLast());
        etSTATUS.setText(this.em.getmStatus());

        Button save = findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent daIntent;

                DatabaseHelper db = new DatabaseHelper(EditActivity.this);
                //gram the text from fields and set to class
                em.setFname(etName.getText().toString());
                em.setLname(etLname.getText().toString());
                em.setmStatus(etSTATUS.getText().toString());
                db.updateEmployee(em);

                //after update dont go back to mainact
                daIntent = new Intent(EditActivity.this, MainActivity.class);

                startActivity(daIntent);
                finish();

            }
        });
    }

}
