package com.fullsail.android.unittestingstarter.util;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.util.regex.Pattern;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class PersonFormatUtil
{

    @Retention(SOURCE)
    @IntDef({FORMAT_FIRST_LAST, FORMAT_LAST_FIRST})
    public @interface NameFormat {}

    public static final int FORMAT_FIRST_LAST = 0x01010;
    public static final int FORMAT_LAST_FIRST = 0x01011;

    @Retention(SOURCE)
    @IntDef({FORMAT_ALL_DASHES, FORMAT_WITH_PARENS, FORMAT_WITH_SPACES})
    public @interface PhoneFormat {}
    public static final int FORMAT_ALL_DASHES = 0x02010;
    public static final int FORMAT_WITH_PARENS = 0x02011;
    public static final int FORMAT_WITH_SPACES = 0x02012;


    public static String formatName(@NameFormat int _format, String _firstName, String _lastName)
    {
        String name = _firstName + " " + _lastName;

        if(_format == FORMAT_FIRST_LAST)
        {
            // TODO: Format person name as "FirstName LastName"
            name = _firstName + " " + _lastName;
        }
        else if(_format == FORMAT_LAST_FIRST)
        {
            // TODO: Format person name as "LastName, FirstName"
            name = _lastName + " " + _firstName;
        }

        // TODO: Change this to match formatting.
        return name;
    }

    public static String formatPhoneNumber(@PhoneFormat int _format, String _phone)
    {
        String newNumber = null;

        _phone = unformatPhoneNumber(_phone);

        if(_format == FORMAT_ALL_DASHES)
        {
            newNumber = typeOne(_phone);
        }
        else if(_format == FORMAT_WITH_PARENS)
        {
            newNumber = typeTwo(_phone);
        }
        else if(_format == FORMAT_WITH_SPACES)
        {
            newNumber = typeThree(_phone);
        }

        //TODO: Change this to match formatting
        return newNumber;
    }

    public static String unformatPhoneNumber(String _phone)
    {
        // TODO: Convert any phone number string into contiguous format. (i.e. 4075550123)
        _phone = _phone.replaceAll("[^0-9\\+]", "");

        _phone = _phone.trim();

        isPhoneNumberValid(_phone);

       return _phone;
    }

    public static String typeOne(String _phone)
    {
        String output;

        switch (_phone.length())
        {
            case 7:
                output = String.format("%s-%s", _phone.substring(0,3), _phone.substring(3,7));
                break;
            case 10:
                output = String.format("%s-%s-%s", _phone.substring(0,3), _phone.substring(3,6), _phone.substring(6,10));
                break;
            case 11:
                output = String.format("%s-%s-%s-%s", _phone.substring(0,1) ,_phone.substring(1,4), _phone.substring(4,7), _phone.substring(7,11));
                break;
            case 12:
                output = String.format("+%s-%s-%s-%s", _phone.substring(0,2) ,_phone.substring(2,5), _phone.substring(5,8), _phone.substring(8,12));
                break;
            default:
                return null;
        }

        return output;
    }

    public static String typeTwo(String _phone)
    {
        String output;

        switch (_phone.length())
        {
            case 7:
                output = String.format("%s-%s", _phone.substring(0,3), _phone.substring(3,7));
                break;
            case 10:
                output = String.format("(%s)%s-%s", _phone.substring(0,3), _phone.substring(3,6), _phone.substring(6,10));
                break;
            case 11:
                output = String.format("%s (%s)%s-%s", _phone.substring(0,1) ,_phone.substring(1,4), _phone.substring(4,7), _phone.substring(7,11));
                break;
            case 12:
                output = String.format("+%s (%s)%s-%s", _phone.substring(0,2) ,_phone.substring(2,5), _phone.substring(5,8), _phone.substring(8,12));
                break;
            default:
                return null;
        }

        return output;
    }

    public static String typeThree(String _phone)
    {
        String output;

        switch (_phone.length())
        {
            case 7:
                output = String.format("%s %s", _phone.substring(0,3), _phone.substring(3,7));
                break;
            case 10:
                output = String.format("%s %s %s", _phone.substring(0,3), _phone.substring(3,6), _phone.substring(6,10));
                break;
            case 11:
                output = String.format("%s %s %s %s", _phone.substring(0,1) ,_phone.substring(1,4), _phone.substring(4,7), _phone.substring(7,11));
                break;
            case 12:
                output = String.format("+%s %s %s %s", _phone.substring(0,2) ,_phone.substring(2,5), _phone.substring(5,8), _phone.substring(8,12));
                break;
            default:
                return null;
        }

        return output;
    }


    public static boolean isPhoneNumberValid(String _phone)
    {

        // TODO: Determine if the entered number is a valid US phone number. (i.e. 4075550123)
        boolean check;

        if(!Pattern.matches("[a-zA-Z]+", _phone))
        {
            if(_phone.length() < 10 || _phone.length() > 10)
            {
                // if(phone.length() != 10) {
                check = false;
            }

            else
                {
                check = true;
            }
        }
        else
            {

            check = false;
        }

        return check;

    }
}
