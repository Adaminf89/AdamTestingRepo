package com.fullsail.android.ce06_starter.e_tests;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class DetailActEspressoTest
{

    //context for the actual application
    private Context mainContext = InstrumentationRegistry.getTargetContext();

    private Person person = new Person("Lando", "Calrissian", 30 );

    public ArrayList<Person> dummyData;


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

    //start app and add person
    @Before
    public void createApp()
    {
        //need person that was created method that takes the real mainContext
        PersonStorageUtil.savePerson(mainContext, person);
        //user Rule to launch given activity

    }

    @Test
    public void testClickMain()
    {
        mainActivityIntentsTestRule.launchActivity(null);

        onView(withText(person.toString())).perform(click());

        onView(withId(R.id.text_first_name)).check(matches(withText(person.getFirstName())));

        onView(withId(R.id.text_last_name)).check(matches(withText(person.getLastName())));

        onView(withId(R.id.text_age)).check(matches(withText(String.valueOf(person.getAge()))));

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

        onView(withText(person.toString())).perform(click());

        onView(withId(R.id.action_delete)).perform(click());

        assertFalse(dummyData.contains(person));
    }



    @After
    public void onFinish()
    {
        PersonStorageUtil.deletePerson(mainContext, person);
    }

}
