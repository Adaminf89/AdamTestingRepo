/*
Adam S Infiesto
Java 2
CustomRedditProvider
* */
package com.example.adaminfiesto.infiestoadam_ce05.RedditStuff;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.adaminfiesto.infiestoadam_ce05.DataBase.DatabaseHelper;
import com.example.adaminfiesto.infiestoadam_ce05.DataBase.OutboundContract;

public class CustomRedditProvider extends ContentProvider
{
    private static final int TABLE_MATCH = 99;
    private DatabaseHelper mDataBase = null;
    private UriMatcher mMatcher = null;

    @Override
    public boolean onCreate()
    {
        mDataBase = new DatabaseHelper(getContext());

        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        mMatcher.addURI(OutboundContract.URI_REDDIT_AUTHORITY, OutboundContract.DATA_TABLE , TABLE_MATCH);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        int resultCode = mMatcher.match(uri);

        if (resultCode == TABLE_MATCH)
        {
            return mDataBase.getWritableDatabase().query(OutboundContract.DATA_TABLE, projection
                            , selection
                            , selectionArgs
                            ,null
                            , null
                            , sortOrder );
        }

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int resultCode = mMatcher.match(uri);

        if (resultCode == TABLE_MATCH)
        {
            return "vnd.android.cursor.dir/vnd." +
                    OutboundContract.URI_REDDIT_AUTHORITY + "." +
                    OutboundContract.DATA_TABLE;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
