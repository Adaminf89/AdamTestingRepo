/*
Adam S Infiesto
Java 2
Books
* */
package com.example.adaminfiesto.infiestoadam_ce05.GoogleBooks;

import java.io.Serializable;

public class Books implements Serializable
{
    private final String title;
    private final String imageUri;
    private final String description;

    public Books(String title, String imageUri,  String description)
    {
        this.title = title;
        this.imageUri = imageUri;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getDescription() {
        return description;
    }

}
