/*
Adam S Infiesto
JAVA2
ShareFragment
* */
package com.example.adaminfiesto.infiestoadamsendback.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.adaminfiesto.infiestoadamsendback.MainActivity;
import com.example.adaminfiesto.infiestoadamsendback.R;

import java.util.Objects;

public class ShareFragment extends Fragment {

    private static final String ARG_URI = "ARG_URI";
    private Uri uri2;
    private String uri;

    public ShareFragment() {
    }


    public static ShareFragment newInstance(String daData)
    {
        Bundle args = new Bundle();

        args.putString(ARG_URI, daData);

        ShareFragment fragment = new ShareFragment();

        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //tell the view that a menu is here
        setHasOptionsMenu(true);

        if(getArguments() != null)
        {
            uri = getArguments().getString(ARG_URI);
            //convert the passed Uri string to actual uri
            uri2 = Uri.parse(uri);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.image_frag, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        //get the imageview we are on.
        ImageView daView = (ImageView) Objects.requireNonNull(getActivity()).findViewById(R.id.imageView);
        //set URI
        //shows correct uriString and first string after fragment is reloaded?
        uri2 = Uri.parse(uri);
        daView.setImageURI(uri2);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_share,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if(item.getItemId() == R.id.shareBtn)
        {
            Intent iRQT = new Intent(Intent.ACTION_PICK);
            startActivityForResult(iRQT, MainActivity.REQUEST_CODE);
        }
        return true;
    }
}
