package com.example.adaminfiesto.infiestoadam_ce04;

import android.graphics.drawable.Drawable;

/**
 * Created by adaminfiesto on 2/6/18.
 * //ADam S Infiesto
 //Java 1
 //MainActivity

 */

public class UserItem {

    final Drawable picId;
    private final String userName;
    private final String userDesc;

    UserItem(Drawable _picId, String _userName,String _userDesc)
    {
        picId = _picId;
        userName = _userName;
        userDesc = _userDesc;
    }

    Drawable getPicId()
    {
        return picId;
    }

    String getUserName()
    {
        return userName;
    }

    String getUserDesc()
    {
        return userDesc;
    }

    public String toString()
    {
        return ""+this.userName;
    }

}
