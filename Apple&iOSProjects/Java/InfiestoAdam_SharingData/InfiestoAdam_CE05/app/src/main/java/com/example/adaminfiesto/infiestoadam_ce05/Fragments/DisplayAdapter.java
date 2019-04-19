/*
Adam S Infiesto
Java 2
DisplayAdapter
* */
package com.example.adaminfiesto.infiestoadam_ce05.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adaminfiesto.infiestoadam_ce05.GoogleBooks.Books;
import com.example.adaminfiesto.infiestoadam_ce05.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class DisplayAdapter extends BaseAdapter
{
    private final ArrayList<Books> passedBooks;
    private final Context mContext;

    public DisplayAdapter(Context context, ArrayList<Books> bookArrayList)
    {
        this.mContext = context;
        this.passedBooks = bookArrayList;
    }

    @Override
    public int getCount()
    {
        if(passedBooks != null)
        {
            return passedBooks.size();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public Object getItem(int position)
    {
        if(passedBooks != null && position <= passedBooks.size())
        {
            return passedBooks.get(position);
        }
        else
        {
            return null;
        }
    }

    @Override
    public long getItemId(int position)
    {
        if(passedBooks != null && position <= passedBooks.size())
        {
            return  position;
        }
        else
        {
            return 0;
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Books currentBooks = (Books) getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_cell, parent, false);
        }

        ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
        TextView tv1 = convertView.findViewById(R.id.textViewTitle);
        TextView tv2 = convertView.findViewById(R.id.textViewDesc);

        Picasso.get().load(currentBooks.getImageUri()).into(iv);
        tv1.setText(currentBooks.getTitle());
        tv2.setText(currentBooks.getDescription());

        return convertView;

    }


}
