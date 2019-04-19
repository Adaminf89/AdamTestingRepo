/*
Adam S Infiesto
JAVA 2
ContactAdapter
* */
package com.example.adaminfiesto.myapplication.fragments;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adaminfiesto.myapplication.Class.Contact;
import com.example.adaminfiesto.myapplication.R;

import java.util.ArrayList;

class ContactAdapter extends BaseAdapter
{
    private final Context mContext;
    private final ArrayList<Contact> passArray;

    public ContactAdapter(Context c, ArrayList<Contact> passArray)
    {
        this.mContext = c;
        this.passArray = passArray;
    }

    @Override
    public int getCount() {
        if(passArray != null )
        {
            return passArray.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position)
    {
        if (passArray != null && position >= 0 && position < passArray.size())
        {
            return passArray.get(position);
        }
        else
        {
            return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView iv;
        TextView nameCon;
        TextView phoneCon;
       //String temp = this.selectedCon.getPic();


        if(convertView != null)
        {
            convertView.getTag();
        }
        else
            {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_cell, parent, false);
        }

        //get selected
        Contact selectedCon = this.passArray.get(position);

        //get cell assets
        nameCon = (TextView) convertView.findViewById(R.id.textViewName);
        phoneCon = (TextView) convertView.findViewById(R.id.textViewNumber);
        iv = (ImageView)convertView.findViewById(R.id.imageView);
        Log.i("Tage", "getView: "+ selectedCon.getPic());

        //check the profile image to set defualt
        if(selectedCon.getPic() == null)
        {
            iv.setImageResource(R.drawable.ic_launcher_background);
            nameCon.setText(selectedCon.getmName());
            phoneCon.setText(selectedCon.cellNumber);
        }
        else
            {
                //parse Uri string from selected
                Uri imageString = Uri.parse(selectedCon.getPic());
                iv.setImageURI(imageString);
                nameCon.setText(selectedCon.getmName());
                phoneCon.setText(selectedCon.getCellNumber());
            }


        return  convertView;

    }
}
