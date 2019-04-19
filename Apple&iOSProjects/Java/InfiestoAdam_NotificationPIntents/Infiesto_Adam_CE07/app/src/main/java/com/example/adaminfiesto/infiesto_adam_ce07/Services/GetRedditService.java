/*Adam S Infiesto
Java 2
GetRedditService
* */
package com.example.adaminfiesto.infiesto_adam_ce07.Services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.Intent;
import android.content.Context;

import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.adaminfiesto.infiesto_adam_ce07.Contract.OutboundContract;
import com.example.adaminfiesto.infiesto_adam_ce07.R;
import com.example.adaminfiesto.infiesto_adam_ce07.RedditStuff;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class GetRedditService extends IntentService
{
    // TODO: Rename actions, choose action names that describe tasks that this

    private ArrayList<RedditStuff> redditData;
    private static final int STANDARD_NOTIFICATION = 0x01001;


    public GetRedditService() {
        super("GetRedditService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null)
        {

            HttpURLConnection connection;
            InputStream is;
            String data = null;

            try {
                String webAddress1 = "https://www.reddit.com/r/Kings_Raid/.json";
                URL url = new URL(webAddress1);

                connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                is = connection.getInputStream();

                data = IOUtils.toString(is, "UTF-8");

                is.close();

                connection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (data != null) {

                try {

                    Log.i(TAG, "hit");

                    JSONObject response = new JSONObject(data);

                    JSONObject membersJson = response.getJSONObject("data");
                    JSONArray redArray = membersJson.getJSONArray("children");

                    redditData = new ArrayList<>();

                    for (int i = 0; i < redArray.length(); i++) {
                        JSONObject obj = redArray.getJSONObject(i);
                        JSONObject nData = obj.getJSONObject("data");

                        //use contract names to pull from
                        String name = nData.getString(OutboundContract.REDDIT_TITLE);
                        String ups = nData.getString(OutboundContract.REDDIT_UPS);
                        String pic = nData.getString(OutboundContract.REDDIT_THUMBNAIL);
                        String url = nData.getString(OutboundContract.REDDIT_URL);
                        String id = nData.getString(OutboundContract.REDDIT_ID);
                        //add to array of class
                        redditData.add(new RedditStuff(name, ups, pic, url, id));

                    }
                    if (redditData != null)
                    {
                        Random randomGenerator = new Random();

                        int index = randomGenerator.nextInt(redditData.size());

                        onStandardNotification(redditData.get(index));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    //Ref:https://stackoverflow.com/questions/46990995/on-android-8-1-api-27-notification-does-not-display?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
    //Ref:https://stackoverflow.com/questions/3168484/pendingintent-works-correctly-for-the-first-notification-but-incorrectly-for-the/9121565?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
    private void onStandardNotification(RedditStuff redditStuff)
    {

        NotificationManager mgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (mgr != null)
        {

            //NOTE: make all RequestCodes different
            Intent webViewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.reddit.com"+redditStuff.getLink()));
            PendingIntent openBrowserIntent = PendingIntent.getActivity(this, 1, webViewIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            //Save action
            Intent saveIntent = new Intent(this, SaveRedditIntent.class);
            saveIntent.putExtra(OutboundContract.EXTRA_REDDIT_RECEIVER, redditStuff);
            PendingIntent savePendingIntent = PendingIntent.getService(this, 2, saveIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            //Required sets
            NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
            style.setBigContentTitle(redditStuff.getTitle());
            style.setSummaryText(redditStuff.getDescription());

            //Use compat for older verisons
            //set notification content for user to know
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, OutboundContract.CHANNAL);
            builder.setSmallIcon(R.drawable.ic_announcement_black_24dp);

            //click for buttons
            builder.setContentIntent(openBrowserIntent);
            builder.setStyle(style);
            builder.addAction(R.drawable.ic_save_black_24dp, "SAVE", savePendingIntent);

            Notification notification = builder.build();

            //extra: to check versions thats not 25
            if (Build.VERSION.SDK_INT >= 26)
            {
                int importance = NotificationManager.IMPORTANCE_HIGH;

                NotificationChannel mChannel = new NotificationChannel(OutboundContract.CHANNAL, OutboundContract.EXTRA_REDDIT_RECEIVER, importance);

                mChannel.enableLights(true);

                builder.setChannelId(OutboundContract.CHANNAL);

                mgr.createNotificationChannel(mChannel);
            }

            mgr.notify(STANDARD_NOTIFICATION, notification);
        }
    }

}