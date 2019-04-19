package com.fullsail.android.unittestingstarter;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.fullsail.android.unittestingstarter.util.PersonFormatUtil;
import com.fullsail.android.unittestingstarter.util.PreferenceUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(AndroidJUnit4.class)
public class PreferenceTest
{
    public static SharedPreferences userPref = Mockito.mock(SharedPreferences.class);
    public static Context mainContext = InstrumentationRegistry.getTargetContext();
    public static Context mockitoCon = Mockito.mock(Context.class);
    public static int FORMAT_FIRST = PersonFormatUtil.FORMAT_FIRST_LAST;
    public static int FORMAT_LAST = PersonFormatUtil.FORMAT_LAST_FIRST;


    @Test
    public void prefNameTest()
    {
        String[] UserData = new String[4];
        //name checks
        UserData[0] = PersonFormatUtil.formatName(FORMAT_FIRST, "John", "Doe");
        assertTrue(UserData[0].equals("John Doe"));

        UserData[1] = PersonFormatUtil.formatName(FORMAT_FIRST, "David", "Smith");
        assertTrue(UserData[1].equals("David Smith"));

        UserData[2] = PersonFormatUtil.formatName(FORMAT_LAST, "John", "Doe");
        assertTrue(UserData[2].equals("Doe John"));

        UserData[3] = PersonFormatUtil.formatName(FORMAT_LAST, "David", "Smith");
        assertTrue(UserData[3].equals("Smith David"));

        //get the fake
        Mockito.when(mockitoCon.getSharedPreferences(anyString(), anyInt())).thenReturn(userPref);
        //this is the 2nd option
        Mockito.when(userPref.getInt(anyString(), anyInt())).thenReturn(1);
        //che the last name.
        assertEquals(PersonFormatUtil.FORMAT_LAST_FIRST, PreferenceUtil.getNameFormat(mockitoCon));

        Mockito.when(userPref.getInt(anyString(), anyInt())).thenReturn(0);
        //che the last name.
        assertEquals(PersonFormatUtil.FORMAT_FIRST_LAST, PreferenceUtil.getNameFormat(mockitoCon));

    }



    @Test
    public void prefNumberTest()
    {
        //get the fake
        Mockito.when(mockitoCon.getSharedPreferences(anyString(), anyInt())).thenReturn(userPref);
        //this is the 2nd option
        Mockito.when(userPref.getInt(anyString(), anyInt())).thenReturn(0);
        //che the 1st
        assertEquals(PersonFormatUtil.FORMAT_ALL_DASHES, PreferenceUtil.getPhoneFormat(mockitoCon));

        Mockito.when(userPref.getInt(anyString(), anyInt())).thenReturn(1);
        //check 2nd
        assertEquals(PersonFormatUtil.FORMAT_WITH_PARENS, PreferenceUtil.getPhoneFormat(mockitoCon));

        Mockito.when(userPref.getInt(anyString(), anyInt())).thenReturn(2);
        //che 3rd
        assertEquals(PersonFormatUtil.FORMAT_WITH_SPACES, PreferenceUtil.getPhoneFormat(mockitoCon));


    }




}
