/*Adam S Infiesto
 * Java 2
 * listFragment */
package com.example.adaminfiesto.infiestoadamce01;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.Serializable;
import java.util.ArrayList;

//Note: dont name list fragment ListFragment
// TODO: 4/30/18 refactor. 
public class listFragment extends ListFragment
{
    private static final String ARG_People = "ARGP";

    public Serializable daData;
    
    public listFragment() {
    }


    public static listFragment newInstance(ArrayList<RedditData> daData) {

        Bundle args = new Bundle();

        args.putSerializable(ARG_People, daData);
        listFragment fragment = new listFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {
            daData = getArguments().getSerializable(ARG_People);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState)
//    {
//        super.onActivityCreated(savedInstanceState);
//
//    }
}
