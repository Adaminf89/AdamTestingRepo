package com.fullsail.android.ce06_starter.e_tests;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.fullsail.android.ce06_starter.DetailsActivity;
import com.fullsail.android.ce06_starter.FormActivity;
import com.fullsail.android.ce06_starter.MainActivity;
import com.fullsail.android.ce06_starter.R;
import com.fullsail.android.ce06_starter.object.Person;
import com.fullsail.android.ce06_starter.util.PersonStorageUtil;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)
public class AppEspressoTest
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

    @Rule
    public IntentsTestRule<DetailsActivity> detailActivityIntentsTestRule = new IntentsTestRule<>(
            DetailsActivity.class, //class to launch
            true,
            false
    );

    @Rule
    public IntentsTestRule<FormActivity> formActivityIntentsTestRule = new IntentsTestRule<>(
            FormActivity.class, //class to launch
            true,
            false
    );

    @Test
    public void EspressoTest()
    {   //look to open activity
        mainActivityIntentsTestRule.launchActivity(null);
        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));

        //add checks for btn
        onView(withId(R.id.action_add)).check(matches(isDisplayed()));
        onView(withId(R.id.action_add)).perform(click());

        //fill
        onView(withId(R.id.edit_first_name)).perform(typeText(person.getFirstName()));
        onView(withId(R.id.edit_last_name)).perform(typeText(person.getLastName()));
        onView(withId(R.id.edit_age)).perform(typeText(String.valueOf(person.getAge())));

        //prepare data for switch
        dummyData = PersonStorageUtil.loadPeople(mainContext);

        //save actions
        onView(withId(R.id.action_save)).perform(click());
        onView(withText(person.toString())).check(matches(isDisplayed()));
        onView(withText(person.toString())).perform(click());

        //saved intents
        intended(allOf
                (hasComponent(DetailsActivity.class.getName()),
                hasExtra(equalTo(DetailsActivity.EXTRA_FIRST_NAME), equalTo(person.getFirstName())),
                hasExtra(equalTo(DetailsActivity.EXTRA_LAST_NAME), equalTo(person.getLastName())),
                hasExtra(equalTo(DetailsActivity.EXTRA_AGE), equalTo(person.getAge()))
        ));

        //detail
        onView(withId(R.id.text_first_name)).check(matches(withText(person.getFirstName())));
        onView(withId(R.id.text_last_name)).check(matches(withText(person.getLastName())));
        onView(withId(R.id.text_age)).check(matches(withText(String.valueOf(person.getAge()))));
        onView(withId(R.id.text_first_name)).check(matches(isDisplayed()));
        onView(withId(R.id.text_last_name)).check(matches(isDisplayed()));
        onView(withId(R.id.text_age)).check(matches(isDisplayed()));

        //delete check
        onView(withId(R.id.action_delete)).check(matches(isDisplayed()));
        onView(withId(R.id.action_delete)).perform(click());
        onView(withText(person.toString())).check(doesNotExist());

    }

    @After
    public void onFinish()
    {
        //delete that user
        PersonStorageUtil.deletePerson(mainContext, person);
    }

}
