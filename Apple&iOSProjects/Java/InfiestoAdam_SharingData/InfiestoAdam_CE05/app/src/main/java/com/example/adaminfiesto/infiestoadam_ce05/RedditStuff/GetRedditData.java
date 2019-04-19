/*
Adam S Infiesto
Java 2
GetRedditData
* */
package com.example.adaminfiesto.infiestoadam_ce05.RedditStuff;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import com.example.adaminfiesto.infiestoadam_ce05.DataBase.DatabaseHelper;
import com.example.adaminfiesto.infiestoadam_ce05.DataBase.OutboundContract;
import com.example.adaminfiesto.infiestoadam_ce05.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class GetRedditData extends AsyncTask<Void, Void, ArrayList<RedditData>> {


    private final MainActivity mActivity;
    private final String WebAddress;


    public GetRedditData(MainActivity mActivity, String WebAddress) {
        this.mActivity = mActivity;
        this.WebAddress = WebAddress;

    }

    @Override
    protected void onPreExecute() {

        //todo
        //put Toast to alert if the app is online or off line
        if(!Net.isConnected(mActivity)){
            onCancelled();
        }
    }

    @Override
    protected ArrayList<RedditData> doInBackground(Void... voids)
    {
        //todo: parse data from reddit
        String webAddress1 = WebAddress;
        String data = Net.getNetworkData(webAddress1);
        DatabaseHelper db = new DatabaseHelper(mActivity);

        if(data!= null)
        {

            try {

                Log.i(TAG, "hit");

                JSONObject response = new JSONObject(data);

                JSONObject membersJson = response.getJSONObject("data");
                JSONArray redArray = membersJson.getJSONArray("children");

                ArrayList<RedditData> subReddit = new ArrayList<>();

                for(int i = 0; i < redArray.length(); i++)
                {
                    JSONObject obj = redArray.getJSONObject(i);
                    JSONObject nData = obj.getJSONObject("data");

                    //use contract names to pull from
                    String name = nData.getString(OutboundContract.REDDIT_TITLE);
                    String ups = nData.getString(OutboundContract.REDDIT_UPS);
                    String pic = nData.getString(OutboundContract.REDDIT_THUMBNAIL);
                    //add to array of class
                    subReddit.add(new RedditData(name, ups, pic));

                    //set pull json data to content values for Database
                    ContentValues contentValues = new ContentValues();

                    contentValues.put(OutboundContract.REDDIT_TITLE, name);
                    //todo: not working?
                    //Made a logic error due to using past code.. the other app looking for description
                    //however i pull and used "up" as such i just posted same in the description catagory
                    contentValues.put(OutboundContract.REDDIT_DESC, ups);
                    contentValues.put(OutboundContract.REDDIT_THUMBNAIL, pic);

                    //use databasehelper to make database to send to other app
                    db.insertContent(contentValues);

                }

                // Update the UI
                return subReddit;

            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
        webAddress1 = "";
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<RedditData> redditData) {
        //todo: put radio button for//
        //mActivity.findListView(redditData);
       //mActivity.SaveSerializable(redditData);

    }

    @Override
    protected void onCancelled()
    {
        super.onCancelled();
        //mActivity.LoadSerializable();
    }

}
