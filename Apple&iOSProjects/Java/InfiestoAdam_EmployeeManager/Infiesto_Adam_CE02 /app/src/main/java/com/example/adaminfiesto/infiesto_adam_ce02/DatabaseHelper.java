//Adam S Infiesto
//JAVA 2
//DataBaseHelper

package com.example.adaminfiesto.infiesto_adam_ce02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    // singleton
    private static DatabaseHelper mInstance = null;
    private static final String DATABASE_FILE = "example_database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String Table_name = "article";
    private static final String Column_ID = "_id";
    private static final String COLUMN_FIRST_NAME = "fname";
    private static final String COLUMN_LAST_NAME = "lname";
    private static final String COLUMN_EMPLOYEE_NUMBER = "enumber";
    private static final String COLUMN_HIRE_DATE = "hdate";
    private static final String COLUMN_EMPLOYMENT_STATUS = "estatus";
    private final String[] ALLCOLUMN =
            {Column_ID,
                    COLUMN_FIRST_NAME,
                    COLUMN_LAST_NAME,
                    COLUMN_EMPLOYEE_NUMBER,
                    COLUMN_EMPLOYMENT_STATUS,
                    COLUMN_HIRE_DATE
            };

    private final SQLiteDatabase mDb;

    DatabaseHelper(Context context)
    {
        super(context, DATABASE_FILE, null, DATABASE_VERSION);

        mDb =  getWritableDatabase();
    }


    public static DatabaseHelper getInstance(Context _context)
    {
        if(mInstance == null)
        {
            mInstance = new DatabaseHelper(_context);
        }
        return mInstance;

    }

    private static final String Create_Table = "CREATE TABLE IF NOT EXISTS " +
            Table_name +" ("+
            Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_FIRST_NAME + " TEXT, " +
            COLUMN_LAST_NAME + " TEXT, " +
            COLUMN_EMPLOYEE_NUMBER + " INTEGER, " +
            COLUMN_HIRE_DATE + " DATETIME, " +
            COLUMN_EMPLOYMENT_STATUS + " TEXT" +
            " )";

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //sets up series of varibles
        db.execSQL(Create_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    //puts data into the database under table_name
    public void insertArticle(Employees employees)
    {
        //similar to hashmaps
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FIRST_NAME, employees.getmName());
        cv.put(COLUMN_LAST_NAME, employees.getmLast());
        cv.put(COLUMN_EMPLOYEE_NUMBER, employees.getmEnumber());
        cv.put(COLUMN_EMPLOYMENT_STATUS, employees.getmStatus());
        cv.put(COLUMN_HIRE_DATE, employees.getmDate());


        mDb.insert(Table_name,null,cv);

    }

    //get the users in data base
    public ArrayList<Employees>getEmployee()
    {
        ArrayList<Employees> allEmployees = new ArrayList<>();

        Cursor daCursor = getAllData();

        while (daCursor.moveToNext())
        {
            allEmployees.add(new Employees(daCursor.getString(daCursor.getColumnIndex(COLUMN_FIRST_NAME)),
                    daCursor.getString(daCursor.getColumnIndex(COLUMN_LAST_NAME)),
                    daCursor.getInt(daCursor.getColumnIndex(COLUMN_EMPLOYEE_NUMBER)),
                    daCursor.getString(daCursor.getColumnIndex(COLUMN_EMPLOYMENT_STATUS)),
                    daCursor.getString(daCursor.getColumnIndex(COLUMN_HIRE_DATE))
                    ));
            daCursor.getCount();
            //daCursor.close();
        }

        daCursor.close();

        return  allEmployees;
    }

    public void deleteName(int id)
    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "DELETE FROM " + Table_name + " WHERE "
//                + Column_ID + " = '" + id + "'";
//
//        db.execSQL(query);

        String mdbID = String.valueOf(id);
        mDb.delete(Table_name, COLUMN_EMPLOYEE_NUMBER + "=?", new String[]
                {
                        mdbID
                });
    }

   public void deleteAll()
   {
//       String[] deleteData = {COLUMN_FIRST_NAME,
//               COLUMN_LAST_NAME,
//               COLUMN_EMPLOYEE_NUMBER,
//               COLUMN_EMPLOYMENT_STATUS,
//               COLUMN_HIRE_DATE};
//       //todo test
//       Cursor daCursor = getAllData();
//
//       Cursor cursor = mDb.query(Table_name,deleteData,null,null,null,null,null);

       mDb.execSQL("delete from "+ Table_name);
   }

    public void updateEmployee(Employees employee) {

        ContentValues values = new ContentValues(1);

        //putting the values from EditActivity -> serialized Class ->  to SQLDB values
        values.put(COLUMN_FIRST_NAME, employee.getmName());
        values.put(COLUMN_LAST_NAME, employee.getmLast());
        values.put(COLUMN_EMPLOYMENT_STATUS, employee.getmStatus());

        //get the current number
        String idString = employee.getmEnumber() + "";
        //update for the employee number
        mDb.update(Table_name, values, COLUMN_EMPLOYEE_NUMBER + "=?", new String[]{
                idString
        });
    }

    //warnings for BELOW not being used... was trying to use both to sort data via spinner in main will continue to debug spinner selection
    public Cursor SortOrder()
    {
        String[] columns = new String[]
                {Column_ID,
                COLUMN_FIRST_NAME,
                COLUMN_LAST_NAME,
                COLUMN_EMPLOYEE_NUMBER,
                COLUMN_EMPLOYMENT_STATUS,
                COLUMN_HIRE_DATE
                };

        return mDb.query(Table_name, columns,null, null, null, null, COLUMN_LAST_NAME+" DESC");

    }

    public Cursor SelectActive()
    {
         return mDb.query(Table_name, ALLCOLUMN,COLUMN_EMPLOYMENT_STATUS + "=?", new String[]{"Active"},null,null,null);
    }

    private Cursor getAllData()
    {
        return mDb.query(Table_name,null,null,null,null,null,null);
    }

}
