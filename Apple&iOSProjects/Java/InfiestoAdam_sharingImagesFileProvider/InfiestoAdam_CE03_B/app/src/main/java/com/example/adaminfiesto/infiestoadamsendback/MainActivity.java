/*
Adam S Infiesto
JAVA2
MainActivity
* */
package com.example.adaminfiesto.infiestoadamsendback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.adaminfiesto.infiestoadamsendback.Fragments.ShareFragment;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 0x01001;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //show blank screen first
        getSupportFragmentManager().beginTransaction().replace(R.id.frameFrag, ShareFragment.newInstance("")).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //update with image
        getSupportFragmentManager().beginTransaction().replace(R.id.frameFrag, ShareFragment.newInstance(data.getDataString())).commit();

    }
}
