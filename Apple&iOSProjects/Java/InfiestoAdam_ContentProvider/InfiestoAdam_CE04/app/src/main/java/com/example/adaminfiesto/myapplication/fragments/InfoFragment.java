/*
Adam S Infiesto
JAVA 2
InfoFragment
* */
package com.example.adaminfiesto.myapplication.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.adaminfiesto.myapplication.R;

import java.util.ArrayList;

public class InfoFragment extends Fragment
{

    private static final String CONTACT_NAME = "CONTACT_NAME";
    private static final String CONTACT_NUMBER = "CONTACT_NUMBER";
    private static final String CONTACT_URI = "CONTACT_URI";
    private String Name;
    private ArrayList<String> Numbers;
    private String imageUri;

    public InfoFragment() {
    }

    public static InfoFragment newInstance(String cName, ArrayList<String> cNumber, String cimageUri) {

        Bundle args = new Bundle();

        InfoFragment fragment = new InfoFragment();

        args.putString(CONTACT_NAME, cName);

        args.putStringArrayList(CONTACT_NUMBER, cNumber);

        args.putString(CONTACT_URI, cimageUri);

        fragment.setArguments(args);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.info_frag, container, false);

        Bundle thisBundle = getArguments();

        //set assets
        EditText nameCon = (EditText) rootView.findViewById(R.id.fullNameText);
        EditText phone1 = (EditText) rootView.findViewById(R.id.editTextPhone1);
        EditText phone2 = (EditText) rootView.findViewById(R.id.editTextPhone2);
        ImageView iv = (ImageView) rootView.findViewById(R.id.imageView2);

        //get passed values
        Name = thisBundle.getString(CONTACT_NAME);
        Numbers = thisBundle.getStringArrayList(CONTACT_NUMBER);
        imageUri = thisBundle.getString(CONTACT_URI);

        if(imageUri == null)
        {
            iv.setImageResource(R.drawable.ic_launcher_background);
            nameCon.setText(Name);
            phone1.setText(Numbers.get(0));
            phone2.setText(Numbers.get(1));
        }
        else
        {
            //parse Uri string from selected
            Uri imageString = Uri.parse(imageUri);
            iv.setImageURI(imageString);
            nameCon.setText(Name);
            phone1.setText(Numbers.get(0));
            phone2.setText(Numbers.get(1));

        }

        return rootView;

    }
}
