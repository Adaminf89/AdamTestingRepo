//Adam S Infiesto
//JAVA 2
//Employees

package com.example.adaminfiesto.infiesto_adam_ce02;

import java.io.Serializable;

public class Employees implements Serializable
{
    private String mName;
    private final Integer mEnumber;
    private String mLName;
    private String mStatus;
    private final String mDate;

    public Employees(String mName, String mLast, Integer mEnumber,  String mStatus, String mDate) {
        this.mName = mName;
        this.mEnumber = mEnumber;
        this.mLName = mLast;
        this.mStatus = mStatus;
        this.mDate = mDate;
    }

    public String getmName() {
        return mName;
    }

    public Integer getmEnumber() {
        return mEnumber;
    }

    public String getmLast() {
        return mLName;
    }

    public String getmStatus() {
        return mStatus;
    }

    public String getmDate() {
        return mDate;
    }

    public void setFname(String Value)
    {
        this.mName = Value;
    }

    public void setLname(String Value)
    {
        this.mLName = Value;
    }

    // --Commented out by Inspection (5/3/18, 11:03 PM):public void setEnumber(Integer Value){this.mEnumber = Value;}

    public void setmStatus(String Value){this.mStatus = Value;}

    @Override
    public String toString()
    {
        return "First Name: "+ mName + " Last Name: "+ mLName +".";
    }
}
