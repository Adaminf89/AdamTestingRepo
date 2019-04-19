/**
 * Created by adaminfiesto on 2/7/18.
 * Java1
 * UserItemClass
 */
package com.example.adaminfiesto.infiestoadam_ce05;
import android.graphics.drawable.Drawable;


class UserItem
{

    private final Drawable picId;
    private final String userName;
    private final String userDesc;
    private final String userDate;
    private final String userID;

    UserItem(Drawable _picId, String _userName,String _userDesc, String _userDate,String _userID)
    {
        picId = _picId;
        userName = _userName;
        userDesc = _userDesc;
        userDate = _userDate;
        userID = _userID;
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

    String getUserDate()
    { return userDate;}

    public String getUserID() {
        return userID;
    }

    public String toString()
    {
        return ""+this.userName;
    }

}
