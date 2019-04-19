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
import com.fullsail.android.ce06_starter.object.Person;
import com.fullsail.android.ce06_starter.util.PersonStorageUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActAutoTest
{

    //context for the actual application
    private Context mainContext = InstrumentationRegistry.getTargetContext();

    private Person person = new Person("Lando", "Calrissian", 30 );

//    public ArrayList<Person> dummyData;

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
        mainActivityIntentsTestRule.launchActivity(null);
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    }

    @Test
    public void addTest() throws UiObjectNotFoundException
    {
        UiObject addBtn = uiDevice.findObject(new UiSelector().description("Add New"));
        addBtn.clearTextField();
        //time to proceed after click
        addBtn.clickAndWaitForNewWindow(1000);
        //find resource
        uiDevice.findObject(By.res(mainContext.getPackageName(), "action_save"));

    }

    @Test
    public void pressTest() throws UiObjectNotFoundException
    {
        UiObject personObj = uiDevice.findObject(new UiSelector().text(person.toString()));
        personObj.clickAndWaitForNewWindow(1000);

        intended(allOf
                (
                        hasComponent(DetailsActivity.class.getName()),
                        hasExtra(equalTo(DetailsActivity.EXTRA_FIRST_NAME), equalTo(person.getFirstName())),
                        hasExtra(equalTo(DetailsActivity.EXTRA_LAST_NAME), equalTo(person.getLastName())),
                        hasExtra(equalTo(DetailsActivity.EXTRA_AGE), equalTo(person.getAge()))
                )
        );


    }

    @After
    public void onFinish()
    {
        PersonStorageUtil.deletePerson(mainContext, person);
    }

}
