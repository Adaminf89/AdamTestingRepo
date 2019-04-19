/*Adam S Infiesto
Java 2
SaveRedditIntent
* */
package com.example.adaminfiesto.infiesto_adam_ce07.Services;

import android.app.IntentService;
import android.content.Intent;


import com.example.adaminfiesto.infiesto_adam_ce07.Contract.OutboundContract;
import com.example.adaminfiesto.infiesto_adam_ce07.MainActivity;
import com.example.adaminfiesto.infiesto_adam_ce07.RedditStuff;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SaveRedditIntent extends IntentService {
// --Commented out by Inspection START (5/20/18, 10:53 PM):
//    // TODO: Rename actions, choose action names that describe tasks that this
//    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
//    private static final String ACTION_FOO = "com.example.adaminfiesto.infiesto_adam_ce07.Services.action.FOO";
// --Commented out by Inspection STOP (5/20/18, 10:53 PM)
    // --Commented out by Inspection (5/20/18, 10:53 PM):private static final String ACTION_BAZ = "com.example.adaminfiesto.infiesto_adam_ce07.Services.action.BAZ";

// --Commented out by Inspection START (5/20/18, 10:53 PM):
//    // TODO: Rename parameters
//    private static final String EXTRA_PARAM1 = "com.example.adaminfiesto.infiesto_adam_ce07.Services.extra.PARAM1";
// --Commented out by Inspection STOP (5/20/18, 10:53 PM)
    // --Commented out by Inspection (5/20/18, 10:53 PM):private static final String EXTRA_PARAM2 = "com.example.adaminfiesto.infiesto_adam_ce07.Services.extra.PARAM2";


    public SaveRedditIntent() {
        super("SaveRedditIntent");
    }


    @Override
    protected void onHandleIntent(Intent intent)
    {
        if (intent != null)
        {
            //ResultReceiver resultReceiver = intent.getParcelableExtra(OutboundContract.EXTRA_REDDIT_RECEIVER);
            RedditStuff reddit = (RedditStuff) intent.getSerializableExtra(OutboundContract.EXTRA_REDDIT_RECEIVER);

            //folder to save reddit data
            File redditFolder = getExternalFilesDir(OutboundContract.FOLDER);

            assert redditFolder != null;

            try {

                //can use id to make different files for folder instead of random number
                File redditFile = new File(redditFolder + "/" + reddit.getId());

                //save this file
                FileOutputStream fos = new FileOutputStream(redditFile);

                //like first project we can save all this as objects
                ObjectOutputStream oss = new ObjectOutputStream(fos);

                //save the gotten reddit item
                oss.writeObject(reddit);

                //close the streams
                fos.close();
                oss.close();

                //dont need result reciver to send stuff
                //need name of reddit intent
                Intent saveInt = new Intent(MainActivity.SAVE_REDDIT_INTENT);

                //Broadcast this saveIntent
                sendBroadcast(saveInt);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }


}
