/*
Adam S Infiesto
Java 2
OutboundContract
* */
package com.example.adaminfiesto.infiestoadam_ce05.DataBase;

public class OutboundContract
{
    /*Authority = com.fullsail.ce6.provider
   Permission = com.fullsail.ce6.provider.AccessData
   Data Source = books
   Column names = _id, title, thumbnail, Description, book_id
   * */

    //authority
    public static final String URI_REDDIT_AUTHORITY = "com.fullsail.ce6.student.provider";
    //tlb name
    public static final String DATA_TABLE = "articles";

    // columns names sould be the same as reddeit json name
    public static final String REDDIT_ID = "_id";
    public static final String REDDIT_THUMBNAIL = "thumbnail";
    public static final String REDDIT_TITLE = "title";
    public static final String REDDIT_DESC = "body";
    public static final String REDDIT_UPS = "ups";

}
