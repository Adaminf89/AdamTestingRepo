/*
Adam S Infiesto
Java 2
InBoundContract
* */
package com.example.adaminfiesto.infiestoadam_ce05.DataBase;

import android.net.Uri;

//ref https://stackoverflow.com/questions/9243361/what-is-a-contract-class-and-how-is-it-used?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
public class InBoundContract
{
    /*Authority = com.fullsail.ce6.provider
    Permission = com.fullsail.ce6.provider.AccessData
    Data Source = books
    Column names = _id, title, thumbnail, Description, book_id
    * */


    //authorty
    private static final String URI_BOOK_AUTHORITY = "com.fullsail.ce6.provider";
    //tbl name
    private static final String BOOK_DATA_TABLE = "books";

    private static final String URI_BOOK_STRING = "content://" + URI_BOOK_AUTHORITY + "/" + BOOK_DATA_TABLE;

    public static final Uri BOOK_CONTENT_URI = Uri.parse(URI_BOOK_STRING + BOOK_DATA_TABLE);

    //public static final Uri CONTENT_URI = BOOK_CONTENT_URI.buildUpon().build();

    //fields needed
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_THUMBNAIL = "thumbnail";
    public static final String BOOK_DESCRIPTION = "Description";



}
