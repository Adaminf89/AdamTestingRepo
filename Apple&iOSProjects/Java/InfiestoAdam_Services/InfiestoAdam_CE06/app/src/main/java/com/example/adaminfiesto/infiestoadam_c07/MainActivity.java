/*Adam Infiesto
Java 2
MainActivity
* */
package com.example.adaminfiesto.infiestoadam_c07;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.adaminfiesto.infiestoadam_c07.GridStuff.GalleryFragment;
import com.example.adaminfiesto.infiestoadam_c07.Service.GalleryService;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String AUTHORITIES = "com.example.adaminfiesto.infiestoadam_c07";
    private static final String IMAGE_FOLDER = "images";
    // --Commented out by Inspection (5/17/18, 3:51 PM):private final String IMAGE_FILE = "test_image";
    private ArrayList<Uri> daImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragFrame, GalleryFragment.newInstance(this,daImages)).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pic_btn, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.imagebtn)
        {
            Intent intent = new Intent(this, GalleryService.class);
            intent.putExtra(GalleryService.EXTRA_RESULT_RECEIVER, new GalleryResultReciever(mHandler));
            startService(intent);
        }
        return true;
    }

    private void getFiles()
    {
        //table of bits aka images
        //BitmapFactory.Options o = new BitmapFactory.Options();
        try
        {
            daImages = new ArrayList<>();

            //the path to files folder inside
            //needs global context in class
            File files = getExternalFilesDir(IMAGE_FOLDER);

            File[] storedFile = files != null ? files.listFiles(): new File[0];

            //file URIs to add to arraylist
            for (File f : storedFile)
            {
                Uri cUri = Uri.parse(f.getAbsolutePath());
                daImages.add(cUri);
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragFrame, GalleryFragment.newInstance(this, daImages)).commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private final Handler mHandler = new Handler();

    public class GalleryResultReciever extends ResultReceiver
    {

        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        GalleryResultReciever(Handler handler) {
            super(mHandler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData)
        {
            if (resultCode != Activity.RESULT_OK || resultData == null)
            {
                return;
            }

          getFiles();

        }

    }

}
