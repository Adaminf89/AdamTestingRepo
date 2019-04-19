/**
 * Created by adaminfiesto on 2/7/18.
 * Java 1
 * User Adapter
 */

package com.example.adaminfiesto.infiestoadam_ce05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class UserAdapter extends BaseAdapter
{
    private static final int BASE_ID = 0x0A0B0CD;

    private final Context mContext;

    private final ArrayList<UserItem> mCollection;

    UserAdapter(Context _context, ArrayList<UserItem> _collection)
    {
        mCollection= _collection;
        mContext = _context;
    }


    public int getCount()
    {
        if(mCollection!=null)
        {
            return mCollection.size();
        }
        return 0;
    }

    public Object getItem(int _position) {
        if( mCollection!= null && _position < mCollection.size() && _position > -1)
        {
            return mCollection.get(_position);
        }
        return null;
    }


    public long getItemId(int _position) {
        return BASE_ID + _position;
    }

    public View getView(int _position, View _recycleView, ViewGroup _parentView) {

        ViewHolder vh;

        if(_recycleView == null)
        {
            _recycleView = LayoutInflater.from(mContext).inflate(R.layout.listview_layout, _parentView,false);
            vh = new ViewHolder(_recycleView);
            _recycleView.setTag(new ViewHolder(_recycleView));
        }
        else
        {
            vh =(ViewHolder)_recycleView.getTag();
        }

        UserItem i = (UserItem)getItem(_position);

        if(i != null)
        {
            vh.userPic.setImageDrawable(i.getPicId());
            vh.userName.setText(i.getUserName());
            vh.userDesc.setText(i.getUserDesc());
            vh.userDate.setText(i.getUserDate());

        }

        return _recycleView;
    }

    static public class ViewHolder
    {
        final TextView userName;
        final TextView userDesc;
        final TextView userDate;
        final ImageView userPic;
        // --Commented out by Inspection (2/8/18, 9:47 PM):final TextView userID;

        ViewHolder(View _layout)
        {
            //userID = _layout.findViewById(R.id.txtID);
            userName = _layout.findViewById(R.id.txtLTitle);
            userDesc = _layout.findViewById(R.id.txtLDesc);
            userDate = _layout.findViewById(R.id.txtLDate);
            userPic = _layout.findViewById(R.id.picLView);
        }
    }

}

