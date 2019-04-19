/*Adam S Infiesto
* Java 2
* GetRedditData */

package com.example.adaminfiesto.infiestoadamce01;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

class GetRedditData extends AsyncTask<Void, Void, ArrayList<RedditData>> {


    private final MainActivity mActivity;
    private final String WebAddress;
    private static final String TAG = "member is hitting";

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
        Log.i(TAG, "doInBackground: dont work");

        if(data!= null)
        {

            try {

                Log.i(TAG, "doInBackground: url works");
                JSONObject response = new JSONObject(data);

                JSONObject membersJson = response.getJSONObject("data");
                JSONArray  redArray = membersJson.getJSONArray("children");

                ArrayList<RedditData> subReddit = new ArrayList<>();

                for(int i = 0; i < redArray.length(); i++)
                {
                    JSONObject obj = redArray.getJSONObject(i);
                    JSONObject nData = obj.getJSONObject("data");

                        String name = nData.getString("title");
                        String ups = nData.getString("ups");
                        subReddit.add(new RedditData(name,ups));

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
        //todo: put radio button for
        mActivity.findListView(redditData);
        mActivity.SaveSerializable(redditData);

    }

    @Override
    protected void onCancelled()
    {
        super.onCancelled();
        mActivity.LoadSerializable();
    }

}
