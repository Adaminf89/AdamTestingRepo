package com.fullsail.android.ce06_starter.e_tests;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;

import com.fullsail.android.ce06_starter.FormActivity;
import com.fullsail.android.ce06_starter.R;
import com.fullsail.android.ce06_starter.object.Person;
import com.fullsail.android.ce06_starter.util.PersonStorageUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

public class FormActEspressoTest
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

    //start app and add person
    @Before
    public void createApp()
    {
        //need person that was created method that takes the real mainContext
        PersonStorageUtil.savePerson(mainContext, person);
        //user Rule to launch given activity
        formActivityIntentsTestRule.launchActivity(null);

    }

    @Test
    public void testFormActivity()
    {
        //load dummy data
        dummyData = PersonStorageUtil.loadPeople(mainContext);
        //set values
        onView(ViewMatchers.withId(R.id.edit_first_name)).perform(typeText(person.getFirstName()));

        onView(ViewMatchers.withId(R.id.edit_last_name)).perform(typeText(person.getLastName()));

        onView(ViewMatchers.withId(R.id.edit_age)).perform(typeText((String.valueOf(person.getAge()))));

        onView(withId(R.id.edit_first_name)).check(matches(withText(person.getFirstName())));

        onView(withId(R.id.edit_last_name)).check(matches(withText(person.getLastName())));

        onView(withId(R.id.edit_age)).check(matches(withText(String.valueOf(person.getAge()))));

        onView(withId(R.id.action_save)).check(matches(isDisplayed()));

        //save
        onView(withId(R.id.action_save)).perform(click());

        assertThat(dummyData, hasItems(person));

    }


    @After
    public void onFinish()
    {
        PersonStorageUtil.deletePerson(mainContext, person);
    }
}
