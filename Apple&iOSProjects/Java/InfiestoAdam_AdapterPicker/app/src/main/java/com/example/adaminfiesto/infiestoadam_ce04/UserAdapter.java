
//ADam S Infiesto
//Java 1
//MainActivity


package com.example.adaminfiesto.infiestoadam_ce04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by adaminfiesto on 2/6/18.
 */

@SuppressWarnings("ALL")
public class UserAdapter extends BaseAdapter
{
    static final int BASE_ID = 0x0A0B0CD;

    Context mContext;

    ArrayList<UserItem> mCollection;

    UserAdapter(Context _context, ArrayList<UserItem> _collection)
    {
        mCollection= _collection;
        mContext = _context;
    }


    public int getCount()
    {
        if(mCollection!=null) {
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

//        if(_recycleView == null) {
//            _recycleView = LayoutInflater.from(mContext)
//                    .inflate(R.layout.array_temp,_parentView,false);
//
//        }
//
//        TextView tvName =(TextView)_recycleView.findViewById(R.id.txtLDesc);
//        TextView tvdes =(TextView)_recycleView.findViewById(R.id.txtLTitle);
//        //ImageView tvP = (ImageView) _recycleView.findViewById(R.id.picLView);
//
//        UserItem account = (UserItem) getItem(_position);
//
//        tvName.setText(account.getUserName());
//
//        String age = account.getUserDesc();
//        tvdes.setText(age.toString());

       // Drawable d = account.getPicId();
       // tvP.setImageDrawable(account.getPicId());

        if(_recycleView == null)
        {
            _recycleView = LayoutInflater.from(mContext).inflate(R.layout.array_temp, _parentView,false);
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
        }

        return _recycleView;
    }

    static public class ViewHolder
    {
        final TextView userName;
        final TextView userDesc;
        final ImageView userPic;

        ViewHolder(View _layout) {
            userName = _layout.findViewById(R.id.txtLTitle);
            userDesc = _layout.findViewById(R.id.txtLDesc);
            userPic = _layout.findViewById(R.id.picLView);
        }
    }

}
