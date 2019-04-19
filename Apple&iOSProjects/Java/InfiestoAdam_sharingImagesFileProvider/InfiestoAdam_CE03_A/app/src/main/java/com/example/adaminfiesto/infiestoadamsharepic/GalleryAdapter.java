/*
Adam S Infiesto
JAVA2
GalleryAdapter
* */
package com.example.adaminfiesto.infiestoadamsharepic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class GalleryAdapter extends BaseAdapter
{

    private final Context mContext;
    private final ArrayList<Uri> passArray;
    private Uri selectedUri;

    public GalleryAdapter(Context c, ArrayList<Uri> passArray)
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
        return 0;
    }

    //Ref from:https://stackoverflow.com/questions/33738061/setting-image-uri-in-gridview?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
    //https://stackoverflow.com/questions/42288227/how-to-display-image-with-intent-action-view?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        selectedUri = passArray.get(position);

        ImageView imageView;

        if(convertView == null)
        {
            //convertView = LayoutInflater.from(mContext).inflate(R.layout.)
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);

        }
        else
            {
                imageView = (ImageView) convertView;
            }
            //uri strings are passing but no display?
            imageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    MainActivity mainActivity = (MainActivity) mContext;
                    Intent i;

                    if(mainActivity.getIntent() != null)
                    {
                        Uri mainUris = FileProvider.getUriForFile(mContext, MainActivity.AUTHORITIES, new File(selectedUri.getPath()));

                        if(Intent.ACTION_PICK.equals(mainActivity.getIntent().getAction()))
                        {

                            i = new Intent(Intent.ACTION_PICK);
                            //i = new Intent();
                            //set the selected URI we need
                            i.setData(mainUris);

                            //replace old intent premission
                            i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                            Log.i(TAG, "onClick: "+selectedUri.toString());
                            //data the send back from A to B if its OK via pick
                            mainActivity.setResult(Activity.RESULT_OK, i);
                            mainActivity.finish();

                        }
                        else
                            {

                            //set what we want it todo
                            i = new Intent(Intent.ACTION_VIEW);

                            //how and what we want to view
                            //i.setData(selectedUri.toString());
                            i.setDataAndType(mainUris, "image/*");

                            //add it intent premission to do
                            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                            Log.i(TAG, "onClick: "+selectedUri.toString());
                            //do
                            mainActivity.startActivity(i);
                            //mainActivity.startActivity(Intent.createChooser(i, "View testing"));
                            //image still not showing?
                        }
                    }
                }
            });

            //imageView.setImageURI(null);
            imageView.setImageURI(selectedUri);
            Log.i(TAG, "onClick: "+selectedUri.toString());

            return imageView;
    }




}
