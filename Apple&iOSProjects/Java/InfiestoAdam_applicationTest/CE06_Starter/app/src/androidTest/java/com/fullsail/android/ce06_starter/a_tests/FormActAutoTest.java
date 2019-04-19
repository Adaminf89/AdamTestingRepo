package com.fullsail.android.ce06_starter.a_tests;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;

import com.fullsail.android.ce06_starter.FormActivity;
import com.fullsail.android.ce06_starter.object.Person;
import com.fullsail.android.ce06_starter.util.PersonStorageUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class FormActAutoTest
{
    //context for the actual application
    private Context mainContext = InstrumentationRegistry.getTargetContext();
    //data for the test data
    private Person person = new Person("Lando", "Calrissian", 30 );
    //person arraylist for test data
    public ArrayList<Person> dummyData;

    //var RULES for the class we want to test
    @Rule
    public IntentsTestRule<FormActivity> formActivityIntentsTestRule = new IntentsTestRule<>(
            FormActivity.class, //class to launch
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
        formActivityIntentsTestRule.launchActivity(null);
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testFormActivity()
    {
        //load dummy data
        dummyData = PersonStorageUtil.loadPeople(mainContext);

        //find resource and set the given
        uiDevice.findObject(By.res(mainContext.getPackageName(), "edit_first_name")).setText(person.getFirstName());
        uiDevice.findObject(By.res(mainContext.getPackageName(), "edit_last_name")).setText(person.getLastName());
        uiDevice.findObject(By.res(mainContext.getPackageName(), "edit_age")).setText((String.valueOf(person.getAge())));

        uiDevice.findObject(By.res(mainContext.getPackageName(), "action_save")).click();
        //check
        assertThat(dummyData, hasItems(person));
    }

    public void onFinish()
    {
        PersonStorageUtil.deletePerson(mainContext, person);
    }

}
