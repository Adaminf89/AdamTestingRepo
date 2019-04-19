/*Adam S Infiesto
* Java 2
* RedditAdapter */

package com.example.adaminfiesto.infiestoadamce01;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


class RedditAdapter extends BaseAdapter {

    private static final long ID_CONSTANT = 0x010101010L;

    private final Context mContext;
    private final ArrayList<RedditData> mReddit;


    public RedditAdapter(Context mContext, ArrayList<RedditData> mReddit) {
        this.mContext = mContext;
        this.mReddit = mReddit;
    }

    @Override
    public int getCount() {
        if(mReddit != null)
        {
            return mReddit.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(mReddit != null && position >= 0 && position < mReddit.size())
        {
            return mReddit.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if(mReddit != null && position >= 0 && position < mReddit.size())
        {
            return ID_CONSTANT + position;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        RedditData subReddit = (RedditData)getItem(position);

        TextView text1 = convertView.findViewById(android.R.id.text1);
        TextView text2 = convertView.findViewById(android.R.id.text2);
        text1.setText(subReddit.getmTitle());
        text2.setText(subReddit.getmUps());


        return convertView;
    }


}
