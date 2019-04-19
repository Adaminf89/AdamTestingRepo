package com.fullsail.android.ce06_starter.e_tests;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import com.fullsail.android.ce06_starter.DetailsActivity;
import com.fullsail.android.ce06_starter.FormActivity;
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
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class MainActEspressoTest
{
    //context for the actual application
    private Context mainContext = InstrumentationRegistry.getTargetContext();

    private Person person = new Person("Lando", "Calrissian", 30 );

    public ArrayList<Person> dummyData;


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
        mainActivityIntentsTestRule.launchActivity(null);

    }


    @Test
    public void testFormActivity()
    {
        //load dummy data
        dummyData = PersonStorageUtil.loadPeople(mainContext);

        //check click action to add
        onView(ViewMatchers.withId(R.id.action_add)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.action_add)).perform(click());

        //checking form intents
        intended(
                allOf(
                        hasComponent(FormActivity.class.getName())
                )
        );

        //check
        assertThat(dummyData, hasItems(person));
        onView(withId(R.id.action_add));

    }

    @Test
    public void testDetailActivity()
    {
        //click
        onView(withText(person.toString())).perform(click());
        //intents to send
        intended(allOf(hasComponent(DetailsActivity.class.getName()),
                hasExtra(equalTo(DetailsActivity.EXTRA_FIRST_NAME), equalTo(person.getFirstName())),
                hasExtra(equalTo(DetailsActivity.EXTRA_LAST_NAME), equalTo(person.getLastName())),
                hasExtra(equalTo(DetailsActivity.EXTRA_AGE), equalTo(person.getAge()))
        ));

       // intending(hasComponent(FormActivity.class.getName())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK,null));

    }


    @After
    public void onFinish()
    {
        //delete that user
        PersonStorageUtil.deletePerson(mainContext, person);
    }

}
