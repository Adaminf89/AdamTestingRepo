/*Adam Infiesto
Java 2
GalleryFragment
* */
package com.example.adaminfiesto.infiestoadam_c07.GridStuff;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.adaminfiesto.infiestoadam_c07.R;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * interface.
 */


public class GalleryFragment extends Fragment
{
    private ArrayList<Uri> passUri;
    private static final String IMAGEKEY = "IMAGEKEY";
    private GridView gridView;

    public GalleryFragment()
    {

    }
    //ref from https://stackoverflow.com/questions/10798943/image-gridview-inside-fragment?utm_medium=organic&utm
    // _source=google_rich_qa&utm_campaign=google_rich_qa
    //testing context sending to adapter for selectedImage?
    public static GalleryFragment newInstance(Context c, ArrayList<Uri> u)
    {
        Bundle args = new Bundle();
        //store the passed Image Uri's
        args.putParcelableArrayList(IMAGEKEY, u);

        GalleryFragment thisGal = new GalleryFragment();

        thisGal.setArguments(args);

        return thisGal;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.gridview_frag, container,false);

        if (!(this.getArguments() == null)) {
            passUri = this.getArguments().getParcelableArrayList(IMAGEKEY);
        }

        gridView = (GridView) view.findViewById(R.id.gridView);

        GalleryAdapter adapter = new GalleryAdapter(getActivity(), passUri);

        gridView.setAdapter(adapter);

        return view;
    }

//    @Override
//    public void onResume()
//    {
//        super.onResume();
//
//        GalleryAdapter adapter = new GalleryAdapter(getContext());
//
//        gridView.setAdapter(adapter);
//    }



}
