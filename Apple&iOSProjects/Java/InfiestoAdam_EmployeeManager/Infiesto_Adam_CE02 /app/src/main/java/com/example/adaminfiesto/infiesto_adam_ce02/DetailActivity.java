//Adam S Infiesto
//JAVA 2
//DetailActivity

package com.example.adaminfiesto.infiesto_adam_ce02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.adaminfiesto.infiesto_adam_ce02.Fragmen.FragDetail;


public class DetailActivity extends AppCompatActivity implements FragDetail.BackData
{
    private static final String DELETEF = "DELETEF";
    public static final String DELETEB = "DELETEB";
    //holder for getting the right monster class data
    private Employees mData;
    private int forwardPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_layout);

        Intent daIntend = getIntent();

        mData = (Employees) daIntend.getSerializableExtra(Intent.EXTRA_TEXT);

        forwardPos = daIntend.getIntExtra(DELETEF, 0);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameDetail2, FragDetail.newInstance()).commit();

    }

    @Override
    public Employees getSelected() {
        return mData;
    }

    //delete from the actual phone listview.
    @Override
    public void Delete()
    {
        Intent backIntent = new Intent();

        backIntent.getIntExtra(DELETEB, forwardPos);

        setResult(RESULT_OK, backIntent);

        DeleteData(mData.getmEnumber());
        finish();
    }

    //database deleter
    private void DeleteData(final int i)
    {
        DatabaseHelper dataSource = new DatabaseHelper(DetailActivity.this);

        dataSource.deleteName(i);
        dataSource.close();

        finish();

    }

}
