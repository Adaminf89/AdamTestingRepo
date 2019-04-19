/*
Adam S Infiesto
JAVA 2
Contact
* */
package com.example.adaminfiesto.myapplication.Class;

import java.io.Serializable;

public class Contact implements Serializable
{


    private String id;
    private String mName;
    public String cellNumber;
    private String pic;


    public Contact(String id, String mName, String cellNumber, String pic) {
        this.id = id;
        this.mName = mName;
        this.cellNumber = cellNumber;
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }


    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
