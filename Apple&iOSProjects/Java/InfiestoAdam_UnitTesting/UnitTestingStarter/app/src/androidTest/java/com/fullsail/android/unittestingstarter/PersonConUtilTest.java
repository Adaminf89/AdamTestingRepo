package com.fullsail.android.unittestingstarter;


import android.support.test.runner.AndroidJUnit4;

import com.fullsail.android.unittestingstarter.object.Person;
import com.fullsail.android.unittestingstarter.util.PersonConversionUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

//ref : https://www.baeldung.com/jsonassert
@RunWith(AndroidJUnit4.class)
public class PersonConUtilTest
{

    public ArrayList<Person> dummyData = new ArrayList<>();
    public ArrayList<Person> dummyOnePerson = new ArrayList<>();
    public JSONArray jsonData = new JSONArray();
    public JSONArray jsonData2 = new JSONArray();


    @Before
    public void createApp()
    {
        //need data to for check with people
        // Three Person objects in an ArrayList converted to the appropriate JSON.
        dummyData.add(new Person("Malphat", "Cal","2013336189", 30 ));
        dummyData.add(new Person("Malphat2", "Cal2","2013336189", 31 ));
        dummyData.add(new Person("Malphat3", "Cal3","2013336189", 32 ));
        //One Person object in an ArrayList converted to the appropriate JSON.
        dummyOnePerson.add(new Person("Malphat", "Cal","2013336189", 30 ));

    }

    //JSON of three people converted to an ArrayList of three Person objects.
    @Test
    public void threePeopleTest() throws JSONException
    {
        //get the actual json from class method
        jsonData.put(dummyData.get(0).getPersonAsJSON());
        jsonData.put(dummyData.get(1).getPersonAsJSON());
        jsonData.put(dummyData.get(2).getPersonAsJSON());

        JSONAssert.assertEquals(PersonConversionUtil.getPeopleJSONFromList(dummyData), jsonData,true);
        //test opposite
        assertEquals(PersonConversionUtil.getPeopleListFromJSON(jsonData), dummyData);

    }

    @Test
    public void onePersontest() throws JSONException
    {
        jsonData2.put(dummyOnePerson.get(0).getPersonAsJSON());

        JSONAssert.assertEquals(PersonConversionUtil.getPeopleJSONFromList(dummyOnePerson), jsonData2,true);
        //test opposite
        assertEquals(PersonConversionUtil.getPeopleListFromJSON(jsonData2), dummyOnePerson);

    }

    @After
    public void finish()
    {
        dummyData.clear();
        dummyOnePerson.clear();
    }

}
