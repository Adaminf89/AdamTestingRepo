/*
Adam S Infiesto
Java 2
RedditStuff
* */
package com.example.adaminfiesto.infiesto_adam_ce07;

import java.io.Serializable;

public class RedditStuff implements Serializable
{
    private final String title;
    private final String imageUri;
    private final String description;
    private final String link;
    private final String id;


    public RedditStuff(String title, String imageUri, String description, String link, String id)
    {
        this.title = title;
        this.imageUri = imageUri;
        this.description = description;
        this.link = link;
        this.id = id;
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

    public String getLink() {
        return link;
    }

    public String getId() {
        return id;
    }


}
