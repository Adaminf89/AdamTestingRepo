package com.fullsail.android.unittestingstarter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.fullsail.android.unittestingstarter.object.Person;
import com.fullsail.android.unittestingstarter.util.PersonStorageUtil;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Matcher;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class StorageUtilTest
{

    private static final String FILE_NAME = "people.json";
    private static final String FILE_FOLDER = "json";

    public static Context mainContext = InstrumentationRegistry.getTargetContext();
    public static Context mockitoCon = Mockito.mock(Context.class);

    public static Person person = new Person("Malphat", "Cal","2013336189", 30 );

    public ArrayList<Person> dummyData;

    @Before
    public void createApp()
    {
        //need person that was created method that takes the real mainContext
        //PersonStorageUtil.savePerson(mainContext, person);
        //mock the "external" file
        Mockito.when(mockitoCon.getExternalFilesDir(Mockito.anyString())).thenReturn(mainContext.getExternalFilesDir(FILE_NAME));

    }

    @Test
    public void save() throws FileNotFoundException
    {

        PersonStorageUtil.savePerson(mockitoCon, person);

        dummyData = PersonStorageUtil.loadPeople(mockitoCon);

        //assertThat(dummyData, Matchers.hasItem(person));
        assertTrue(dummyData.contains(person));
    }

    @Test
    public void delete() throws FileNotFoundException
    {

        PersonStorageUtil.savePerson(mockitoCon, person);

        PersonStorageUtil.deletePerson(mockitoCon, person);

        dummyData = PersonStorageUtil.loadPeople(mockitoCon);
        //check deleted
        assertFalse(dummyData.contains(person));

    }

    @After
    public void onFinish()
    {
        Mockito.when(mockitoCon.deleteFile(anyString())).thenReturn(mainContext.deleteFile(FILE_NAME));

        mainContext.deleteFile(FILE_NAME);
    }

}
