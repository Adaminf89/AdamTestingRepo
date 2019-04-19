/*Adam S Infiesto
Java 2
RedditAdapter
* */
package com.example.adaminfiesto.infiesto_adam_ce07.ListStuff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adaminfiesto.infiesto_adam_ce07.R;
import com.example.adaminfiesto.infiesto_adam_ce07.RedditStuff;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class RedditAdapter extends BaseAdapter
{
    private final ArrayList<RedditStuff> passedBooks;
    private final Context mContext;

    public RedditAdapter(Context context, ArrayList<RedditStuff> bookArrayList)
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
        RedditStuff currentBooks = (RedditStuff) getItem(position);

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
