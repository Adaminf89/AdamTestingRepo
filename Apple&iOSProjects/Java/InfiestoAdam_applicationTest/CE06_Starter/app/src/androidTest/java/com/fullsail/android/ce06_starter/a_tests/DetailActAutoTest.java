package com.fullsail.android.ce06_starter.a_tests;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import com.fullsail.android.ce06_starter.DetailsActivity;
import com.fullsail.android.ce06_starter.MainActivity;
import com.fullsail.android.ce06_starter.R;
import com.fullsail.android.ce06_starter.object.Person;
import com.fullsail.android.ce06_starter.util.PersonStorageUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertFalse;

public class DetailActAutoTest
{

    //context for the actual application
    private Context mainContext = InstrumentationRegistry.getTargetContext();
    //data for the test data
    private Person person = new Person("Lando", "Calrissian", 30 );
    //person arraylist for test data
    public ArrayList<Person> dummyData;

    //var RULES for the class we want to test
    @Rule
    public IntentsTestRule<DetailsActivity> detailActivityIntentsTestRule = new IntentsTestRule<>(
            DetailsActivity.class, //class to launch
            true,
            false
    );

    @Rule
    public IntentsTestRule<MainActivity> mainActivityIntentsTestRule = new IntentsTestRule<>(
            MainActivity.class, //class to launch
            true,
            false
    );

    public UiDevice uiDevice;

    //start app and add person
    @Before
    public void createApp()
    {
        //need person that was created method that takes the real mainContext
        PersonStorageUtil.savePerson(mainContext, person);
        //user Rule to launch given activity
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testDetailActivity()
    {
        detailActivityIntentsTestRule.launchActivity(null);

        dummyData = PersonStorageUtil.loadPeople(mainContext);

        onView(withId(R.id.text_first_name)).check(matches(isDisplayed()));

        onView(withId(R.id.text_last_name)).check(matches(isDisplayed()));

        onView(withId(R.id.text_age)).check(matches(isDisplayed()));

        onView(withId(R.id.action_delete)).check(matches(isDisplayed()));

    }

    @Test
    public void testClicks() throws UiObjectNotFoundException
    {
        //actual act needed
        mainActivityIntentsTestRule.launchActivity(null);

        //delete
        UiObject deleteBtn = uiDevice.findObject(new UiSelector().text(person.toString()));
        deleteBtn.clickAndWaitForNewWindow(1000);

        UiObject deletedObj = uiDevice.findObject(new UiSelector().description("Delete"));
        deletedObj.clickAndWaitForNewWindow(1000);

        //check and load the new data while going back
        dummyData = PersonStorageUtil.loadPeople(mainContext);
        assertFalse(dummyData.contains(person));
    }


    @After
    public void onFinish()
    {
        PersonStorageUtil.deletePerson(mainContext, person);
    }



}
