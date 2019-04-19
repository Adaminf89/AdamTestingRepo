package com.fullsail.android.ce06_starter.a_tests;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
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
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class AppActAutoTest
{
    //context for the actual application
    private Context mainContext = InstrumentationRegistry.getTargetContext();
    //data for the test data
    private Person person = new Person("Lando", "Calrissian", 30 );
    //person arraylist for test data
    public ArrayList<Person> dummyData;

    //var RULES for the class we want to test
    @Rule
    public IntentsTestRule<MainActivity> mainActivityIntentsTestRule = new IntentsTestRule<>(
            MainActivity.class, //class to launch
            true,
            false
    );

    public UiDevice uiDevice;

    @Before
    public void createApp()
    {
        //need person that was created method that takes the real mainContext
        PersonStorageUtil.savePerson(mainContext, person);
        //user Rule to launch given activity
        mainActivityIntentsTestRule.launchActivity(null);
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }


    @Test
    public void testFormActivity() throws UiObjectNotFoundException
    {
        UiObject addBtn = uiDevice.findObject(new UiSelector().description("Add New"));
        addBtn.clearTextField();
        //time to proceed after click
        addBtn.clickAndWaitForNewWindow(1000);
        //find resource
        uiDevice.findObject(By.res(mainContext.getPackageName(), "action_save"));
        //find resource and set the given
        uiDevice.findObject(By.res(mainContext.getPackageName(), "edit_first_name")).setText(person.getFirstName());
        uiDevice.findObject(By.res(mainContext.getPackageName(), "edit_last_name")).setText(person.getLastName());
        uiDevice.findObject(By.res(mainContext.getPackageName(), "edit_age")).setText((String.valueOf(person.getAge())));

        UiObject saveBtn = uiDevice.findObject(new UiSelector().description("Save"));

        saveBtn.clickAndWaitForNewWindow(1000);

        //load dummy data
        dummyData = PersonStorageUtil.loadPeople(mainContext);
        assertThat(dummyData, hasItems(person));

        UiObject object = uiDevice.findObject(new UiSelector().text(person.toString()));

        object.clickAndWaitForNewWindow(1000);

        intended(allOf
                (
                        hasComponent(DetailsActivity.class.getName()),
                        hasExtra(equalTo(DetailsActivity.EXTRA_FIRST_NAME), equalTo(person.getFirstName())),
                        hasExtra(equalTo(DetailsActivity.EXTRA_LAST_NAME), equalTo(person.getLastName())),
                        hasExtra(equalTo(DetailsActivity.EXTRA_AGE), equalTo(person.getAge()))
                )
        );


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
