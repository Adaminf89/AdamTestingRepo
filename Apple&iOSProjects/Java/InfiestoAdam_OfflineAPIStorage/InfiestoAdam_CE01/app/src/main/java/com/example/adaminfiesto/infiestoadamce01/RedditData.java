/*Adam S Infiesto
* Java 2
* RedditData */

package com.example.adaminfiesto.infiestoadamce01;

import java.io.Serializable;


class RedditData implements Serializable
{
    private final String mTitle;
    private final String mUps;
    public RedditData(String mTitle, String mUps) {
        this.mTitle = mTitle;
        this.mUps = mUps;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmUps() {
        return mUps;
    }
}
