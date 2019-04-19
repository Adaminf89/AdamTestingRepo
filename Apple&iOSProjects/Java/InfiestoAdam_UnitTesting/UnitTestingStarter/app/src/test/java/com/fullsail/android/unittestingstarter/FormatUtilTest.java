package com.fullsail.android.unittestingstarter;

import android.content.Context;
import android.util.Log;

import com.fullsail.android.unittestingstarter.util.PersonFormatUtil;
import com.fullsail.android.unittestingstarter.util.PersonStorageUtil;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FormatUtilTest
{
    public static int DASHES = PersonFormatUtil.FORMAT_ALL_DASHES;
    public static int PARENS = PersonFormatUtil.FORMAT_WITH_PARENS;
    public static int SPACES = PersonFormatUtil.FORMAT_WITH_SPACES;

    @Test
    public void phoneTest()
    {
        String userNumbers[] = {"4075550123", "407 555 0123", "(407)555-0123", "407-555-0123"};

        String[] UserData = new String[4];

        //unformated numbers
        UserData[0] = PersonFormatUtil.unformatPhoneNumber(userNumbers[0]);
        UserData[1] = PersonFormatUtil.unformatPhoneNumber(userNumbers[1]);
        UserData[2] = PersonFormatUtil.unformatPhoneNumber(userNumbers[2]);
        UserData[3] = PersonFormatUtil.unformatPhoneNumber(userNumbers[3]);

        assertTrue(UserData[0].equals("4075550123"));
        assertTrue(UserData[1].equals("4075550123"));
        assertTrue(UserData[2].equals("4075550123"));
        assertTrue(UserData[3].equals("4075550123"));

        //check array items with spaces
        UserData[0] = PersonFormatUtil.formatPhoneNumber(SPACES, userNumbers[0]);
        UserData[1] = PersonFormatUtil.formatPhoneNumber(SPACES, userNumbers[1]);
        UserData[2] = PersonFormatUtil.formatPhoneNumber(SPACES, userNumbers[2]);
        UserData[3] = PersonFormatUtil.formatPhoneNumber(SPACES, userNumbers[3]);

        assertTrue(UserData[0].equals("407 555 0123"));
        assertTrue(UserData[1].equals("407 555 0123"));
        assertTrue(UserData[2].equals("407 555 0123"));
        assertTrue(UserData[3].equals("407 555 0123"));

        //check array with parens
        UserData[0] = PersonFormatUtil.formatPhoneNumber(PARENS, userNumbers[0]);
        UserData[1] = PersonFormatUtil.formatPhoneNumber(PARENS, userNumbers[1]);
        UserData[2] = PersonFormatUtil.formatPhoneNumber(PARENS, userNumbers[2]);
        UserData[3] = PersonFormatUtil.formatPhoneNumber(PARENS, userNumbers[3]);

        assertTrue(UserData[0].equals("(407)555-0123"));
        assertTrue(UserData[1].equals("(407)555-0123"));
        assertTrue(UserData[2].equals("(407)555-0123"));
        assertTrue(UserData[3].equals("(407)555-0123"));

        //check array with dashes
        UserData[0] = PersonFormatUtil.formatPhoneNumber(DASHES, userNumbers[0]);
        UserData[1] = PersonFormatUtil.formatPhoneNumber(DASHES, userNumbers[1]);
        UserData[2] = PersonFormatUtil.formatPhoneNumber(DASHES, userNumbers[2]);
        UserData[3] = PersonFormatUtil.formatPhoneNumber(DASHES, userNumbers[3]);

        assertTrue(UserData[0].equals("407-555-0123"));
        assertTrue(UserData[1].equals("407-555-0123"));
        assertTrue(UserData[2].equals("407-555-0123"));
        assertTrue(UserData[3].equals("407-555-0123"));

    }

    @Test
    public void failedNumber()
    {
        String failedNumbers[] = {"1-407-555-0123", "40755501234", "407555012"};

        assertFalse(PersonFormatUtil.isPhoneNumberValid(failedNumbers[0]));
        assertFalse(PersonFormatUtil.isPhoneNumberValid(failedNumbers[1]));
        assertFalse(PersonFormatUtil.isPhoneNumberValid(failedNumbers[2]));

    }

}