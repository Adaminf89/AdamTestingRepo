/*Adam S Infiesto
Java 2
RedditListFragment
* */
package com.example.adaminfiesto.infiesto_adam_ce07.ListStuff;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.adaminfiesto.infiesto_adam_ce07.Contract.OutboundContract;
import com.example.adaminfiesto.infiesto_adam_ce07.R;
import com.example.adaminfiesto.infiesto_adam_ce07.RedditStuff;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Objects;


public class RedditListFragment extends ListFragment {

    private ArrayList<RedditStuff> passUri = new ArrayList<>();

    public RedditListFragment()
    {

    }
    //ref from https://stackoverflow.com/questions/10798943/image-gridview-inside-fragment?utm_medium=organic&utm
    // _source=google_rich_qa&utm_campaign=google_rich_qa
    //testing context sending to adapter for selectedImage?
    public static RedditListFragment newInstance()
    {
        Bundle args = new Bundle();
        //store the passed Image Uri's
        //args.putParcelableArrayList(IMAGEKEY, u);

        RedditListFragment thisGal = new RedditListFragment();

        thisGal.setArguments(args);

        return thisGal;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.list_fragment, container,false);

        LoadSerializable();
        RedditAdapter adapter = new RedditAdapter(getActivity(), passUri);
        setListAdapter(adapter);

        return view;
    }

    private void LoadSerializable()
    {
        File redditFolder = Objects.requireNonNull(getContext()).getExternalFilesDir(OutboundContract.FOLDER);

        assert redditFolder != null;

        try
        {
            //Note this is still strings...not bytes/object
            for(String redditFile : redditFolder.list())
            {
                FileInputStream fis = new FileInputStream(redditFolder + "/" + redditFile);

                ObjectInputStream oos =  new ObjectInputStream(fis);

                passUri.add((RedditStuff)oos.readObject());

                oos.close();
                fis.close();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }


}
