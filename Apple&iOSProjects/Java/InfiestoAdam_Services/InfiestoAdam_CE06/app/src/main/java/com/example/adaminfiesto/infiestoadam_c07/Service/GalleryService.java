/*Adam Infiesto
Java 2
GalleryService
* */
package com.example.adaminfiesto.infiestoadam_c07.Service;
import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class GalleryService extends IntentService {
// --Commented out by Inspection START (5/17/18, 3:51 PM):
//    // TODO: Rename actions, choose action names that describe tasks that this
//    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
//    private static final String ACTION_FOO = "com.example.adaminfiesto.infiestoadam_c07.Service.action.FOO";
// --Commented out by Inspection STOP (5/17/18, 3:51 PM)
    // --Commented out by Inspection (5/17/18, 3:51 PM):private static final String ACTION_BAZ = "com.example.adaminfiesto.infiestoadam_c07.Service.action.BAZ";
    private static final String IMAGE_FOLDER = "images";
// --Commented out by Inspection START (5/17/18, 3:51 PM):
//    // TODO: Rename parameters
//    private static final String EXTRA_PARAM1 = "com.example.adaminfiesto.infiestoadam_c07.Service.extra.PARAM1";
// --Commented out by Inspection STOP (5/17/18, 3:51 PM)
    // --Commented out by Inspection (5/17/18, 3:51 PM):private static final String EXTRA_PARAM2 = "com.example.adaminfiesto.infiestoadam_c07.Service.extra.PARAM2";

    public static final String EXTRA_RESULT_RECEIVER =
            "com.fullsail.android.intentservicedemo.EXTRA_RESULT_RECEIVER";

    private static final String URL_BASE = "http://i.imgur.com/";
    // --Commented out by Inspection (5/17/18, 3:51 PM):String name;
    private final String[] IMAGES = {
            "Df9sV7x.jpg", "nqnegVs.jpg", "JDCG1tP.jpg",
            "tUvlwvB.jpg", "2bTEbC5.jpg", "Jnqn9NJ.jpg",
            "xd2M3FF.jpg", "atWe0me.jpg", "UJROzhm.jpg",
            "4lEPonM.jpg", "vxvaFmR.jpg", "NDPbJfV.jpg",
            "ZPdoCbQ.jpg", "SX6hzar.jpg", "YDNldPb.jpg",
            "iy1FvVh.jpg", "2bTEbC5.jpg", "P0RMPwI.jpg",
            "lKrCKtM.jpg", "eXvZwlw.jpg", "zFQ6TwY.jpg",
            "mTY6vrd.jpg", "QocIraL.jpg", "VYZGZnk.jpg",
            "RVzjXTW.jpg", "1CUQgRO.jpg", "GSZbb2d.jpg",
            "IvMKTro.jpg", "oGzBLC0.jpg", "55VipC6.jpg"
    };


    public GalleryService() {
        super("GalleryService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if (intent != null)
        {
           makeImages(intent);
        }
    }


    private void makeImages(Intent i)
    {

        ResultReceiver receiver = i.getParcelableExtra(EXTRA_RESULT_RECEIVER);

        Bundle dataBundle = new Bundle();

        //the path to files folder inside
        File protectedStorage =  getExternalFilesDir(IMAGE_FOLDER);

        protectedStorage.mkdir();

        for(String jpgString : IMAGES)
        {
            File imageFile = new File(protectedStorage, jpgString);

            //if the Image folder is here dont download
            if (imageFile.exists())
            {
                receiver.send(Activity.RESULT_OK, dataBundle);
            }
            else
                {

                try {


                    URL url = new URL(URL_BASE + jpgString);

                    InputStream is = url.openConnection().getInputStream();

                    byte[] byteArray = new byte[2048];

                    FileOutputStream fos = new FileOutputStream(imageFile);

                    int length;

                    while ((length = is.read(byteArray)) != -1) {
                        fos.write(byteArray, 0, length);
                    }

                    is.close();
                    fos.close();


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                //send somthing to set the images to load

            }
            receiver.send(Activity.RESULT_OK, dataBundle);
        }

    }



}
