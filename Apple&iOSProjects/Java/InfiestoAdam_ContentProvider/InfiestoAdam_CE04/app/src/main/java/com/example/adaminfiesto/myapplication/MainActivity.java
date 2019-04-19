/*
Adam S Infiesto
JAVA 2
MainActivity
* */
package com.example.adaminfiesto.myapplication;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.adaminfiesto.myapplication.Class.Contact;
import com.example.adaminfiesto.myapplication.fragments.ContactListFrag;
import com.example.adaminfiesto.myapplication.fragments.InfoFragment;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements ContactListFrag.ListFragmentItemClickListener {

    private static final int PERMISSION_REQUEST = 0x01001;
    private final ArrayList<Contact> mArrayCList = new ArrayList<>();
    // --Commented out by Inspection (5/9/18, 8:29 PM):ContactListFrag frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //frag = new ContactListFrag();

            start();

    }

    private void start()
    {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_main);

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST);
            }
            else
            {
                getContact();
                getSupportFragmentManager().beginTransaction().replace(R.id.contactFrame, ContactListFrag.newInstance(this, mArrayCList)).commit();
            }
        }
        else
        {
            setContentView(R.layout.activity_main);
        }
    }

    //ref from https://stackoverflow.com/questions/28827373/how-to-get-the-contact-number-from-contacts-in-android?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
    private void getContact()
    {

        ContentResolver cr = this.getContentResolver();

        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if(Objects.requireNonNull(cursor).moveToFirst())
        {
            do
            {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",new String[]{ id }, null);
                    while (Objects.requireNonNull(pCur).moveToNext())
                    {
                        String name = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        String phoneNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String contactId = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                        String imageUri = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI));

                        Contact c = new Contact(contactId, name, phoneNumber, imageUri);
                        c.setId(contactId);
                        c.setmName(name);
                        c.setCellNumber(phoneNumber);
                        c.setPic(imageUri);

                        mArrayCList.add(c);
                        break;

                    }
                    pCur.close();
                }

            } while (cursor.moveToNext()) ;
        }
    }

    //ref : https://stackoverflow.com/questions/19705381/how-to-query-all-the-details-of-the-contact-at-once?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
    @Override
    public void onListFragmentItemClick(String position)
    {

        String cFullName;
        ArrayList<String> phoneNumbers = new ArrayList<>();
        String imageUri;
        String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?";
        String[] selectionArgs = {
                position
        };

        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, selection, selectionArgs,null);

        while (Objects.requireNonNull(cursor).moveToNext())
        {
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            phoneNumbers.add(number);

        }

        if(phoneNumbers.size() >= 1)
        {
            phoneNumbers.add("No second Number");
        }

        selection = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE + "' AND " +
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?";

        cursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, selection, selectionArgs,null);

        Objects.requireNonNull(cursor).moveToFirst();

        String fName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
        String mName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME));
        String lName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));

        cFullName = ""+fName+" "+mName+" "+lName+"";
        Log.i("TAG", "onListFragmentItemClick: "+phoneNumbers);

        imageUri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI));

        getSupportFragmentManager().beginTransaction().replace(R.id.InfoFramePic, InfoFragment.newInstance(cFullName, phoneNumbers, imageUri)).commit();

    }
}
