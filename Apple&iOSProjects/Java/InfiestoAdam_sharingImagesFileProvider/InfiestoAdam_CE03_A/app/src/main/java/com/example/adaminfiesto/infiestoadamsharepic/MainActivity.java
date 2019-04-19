/*
Adam S Infiesto
JAVA2
MainActivity
* */

package com.example.adaminfiesto.infiestoadamsharepic;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_TAKE_PICTURE = 0x01001;
    public static final String AUTHORITIES = "com.example.adaminfiesto.infiestoadamsharepic";
    private static final String IMAGE_FOLDER = "images";
    private final String IMAGE_FILE = "test_image";
    private ArrayList<Uri> daImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFiles();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_camara, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.camBtn)
        {
            //intent for passing the image
            //mediastore used to grab the action
            //looks in manifest to do this
            //NOTE: can have more than one attached to action
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            //telling where ur going to save the file
            //this is expecting a uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, getOutputUri());

            //give temp premissions
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            //starts the activity
            startActivityForResult(intent, REQUEST_TAKE_PICTURE);

        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        getFiles();

    }

    private Uri getOutputUri()
    {
        //actual file handler
        File f = getImageFile();

        //"magical" method to build uri
        return (f == null ? null : FileProvider.getUriForFile(this, AUTHORITIES, f));
    }


//    private String getOutputFilePath()
//    {
//        File f = getImageFile();
//        //the absolutepath
//        return (f == null ? null : f.getAbsolutePath());
//    }


    //sets up getting Image files to call whenever
    private File getImageFile()
    {
        //number to tag each taken pic
        Random r = new Random();
        int i1 = (r.nextInt(1000000000) + 1);

        //the path to files folder inside
        File protectedStorage =  getExternalFilesDir(IMAGE_FOLDER);

        File imageFile = new File(protectedStorage, IMAGE_FILE+""+i1);

        try {
            //warning should just return null in catch
            //ask image file
            imageFile.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            //allow involker to handle stacktrace
            return null;
        }

        return imageFile;
    }

    private void getFiles()
    {
        //table of bits aka images
        //BitmapFactory.Options o = new BitmapFactory.Options();
        try
        {
            daImages = new ArrayList<>();

            //the path to files folder inside
            File files = getExternalFilesDir(IMAGE_FOLDER);

            File[] storedFile = files != null ? files.listFiles(): new File[0];

            //file URIs to add to arraylist
            for (File f : storedFile)
            {
                Uri cUri = Uri.parse(f.getAbsolutePath());
                daImages.add(cUri);
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frameForm, GalleryFragment.newInstance(this, daImages)).commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
