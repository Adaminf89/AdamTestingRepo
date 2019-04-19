package com.fullsail.android.ce06_starter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.fullsail.android.ce06_starter.object.Person;
import com.fullsail.android.ce06_starter.util.PersonStorageUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UtilTest {


    public static String FILE_NAME = "swordsofrevealinglight.dat";

    public static Context mainContext = InstrumentationRegistry.getTargetContext();
    public static Context mockitoCon = Mockito.mock(Context.class);

    public static Person person = new Person("Lando", "Calrissian", 30 );

    public static ArrayList<Person> dummyData;

    @Before
    public void createApp()
    {
        //need person that was created method that takes the real mainContext
        //PersonStorageUtil.savePerson(mainContext, person);
        //create the file&location to save
        File userData = new File(mainContext.getFilesDir(), FILE_NAME);
        try
        {
//            FileOutputStream fos = mainContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(people);
//            oos.close();
            userData.createNewFile();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @Test
    public void save() throws FileNotFoundException
    {
//        ArrayList<Person> people = loadPeople(mockitoCon);
//        people.add(person);

        Mockito.when(mockitoCon.openFileInput(anyString())).thenReturn(mainContext.openFileInput(FILE_NAME));

        Mockito.when(mockitoCon.openFileOutput(anyString(), anyInt())).thenReturn(mainContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE));

        PersonStorageUtil.savePerson(mockitoCon, person);

        dummyData = PersonStorageUtil.loadPeople(mockitoCon);
        //check
        assertTrue(dummyData.contains(person));
    }

    @Test
    public void delete() throws FileNotFoundException
    {
        Mockito.when(mockitoCon.openFileInput(anyString())).thenReturn(mainContext.openFileInput(FILE_NAME));

        Mockito.when(mockitoCon.openFileOutput(anyString(), anyInt())).thenReturn(mainContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE));

        PersonStorageUtil.savePerson(mockitoCon, person);

        PersonStorageUtil.deletePerson(mockitoCon, person);

        dummyData = PersonStorageUtil.loadPeople(mockitoCon);
        //check deleted
        assertFalse(dummyData.contains(person));

    }

    @Test
    public void load() throws FileNotFoundException
    {

        try
        {
            Mockito.when(mockitoCon.openFileInput(anyString())).thenReturn(mainContext.openFileInput(FILE_NAME));

            Mockito.when(mockitoCon.openFileOutput(anyString(), anyInt())).thenReturn(mainContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE));

            PersonStorageUtil.savePerson(mockitoCon, person);

            dummyData = PersonStorageUtil.loadPeople(mockitoCon);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @After
    public void onFinish()
    {
        Mockito.when(mockitoCon.deleteFile(anyString())).thenReturn(mainContext.deleteFile(FILE_NAME));

        mainContext.deleteFile(FILE_NAME);
    }


}
