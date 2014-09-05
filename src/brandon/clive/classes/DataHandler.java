package brandon.clive.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by staff on 8/29/2014.
 * Edited by Brandon and Clive
 */
public class DataHandler {

    //Declare three variables to enter the three inputs
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String AGE = "age";
    public static final String CITY = "city";
    public static final String POSTAL_CODE = "postal_code";

    //Create a database by indicating both the name of your database and the version number
    //NB: DATABASE_VERSION is one of the very important database properties that we use when creating
    //a database
    public static final String DATABASE_NAME = "mydb";
    public static final int DATABASE_VERSION = 1;

    //Name the database table
    public static final String TABLE_NAME = "people";

    //Create a database table with three fields or columns namely name, age and Dependency
        public static final String TABLE_CREATE = "CREATE TABLE people(Name text not null," +
            "Surname text not null," +
            "Age text not null," +
            "City text not null," +
            "Postal_code text not null);";


    Context context;
    DatabaseHelper dbhelper;
    SQLiteDatabase db;

    public DataHandler(Context context) {

        this.context = context;
        dbhelper = new DatabaseHelper(context);
    }

    //Create an inner class that will inherit methods from the SQLiteOpenHelper
    //Remember: SQLiteOpenHelper will always help you to clean your code especially when
    //deleting a database without creating another version of it
    private static class DatabaseHelper extends SQLiteOpenHelper {

     //Constructor using a Context class to access a service

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        //execute the table based on the instructions that will be coded on your main activity
        //This must catch an SQLException in case the table does not exist in that database
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(TABLE_CREATE);
            } catch (android.database.SQLException e) {
                e.printStackTrace();
            }
        }
       //This method is very important in SQLite as it will delete
        //your oldVersion of your database and replace it with the new
        //database, apply normalization principles

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS people");
            onCreate(db);
        }
    }
    //a method that will open a database
    public DataHandler open(){
        dbhelper.getWritableDatabase();
        return this;
    }
    //a method that will close a database
    public void close(){
        dbhelper.close();
    }
    //We use ContentValues class to insert data inside the table
    //content.put(NAME, name);
    //NAME is a key value and name in small letters is a value the way you declare them at the
    //beginning of this class "public static final String NAME (Key) = "name (value)";"
    public long insertInfo(String name,String surname,String age, String city, String postalCode){
        ContentValues content = new ContentValues();
        content.put(NAME, name);
        content.put(SURNAME, surname);
        content.put(AGE, age);
        content.put(CITY, city);
        content.put(POSTAL_CODE, postalCode);
        if(db == null)
        {
        	db = dbhelper.getWritableDatabase();
        }
        return db.insertOrThrow(TABLE_NAME,null,content);
    }
    //when you want to query from the database, you use a Cursor to extract data from the database
    //in our case we extract the name, age, and dependencies that are in the database
    //NB: The SQLiteCursor class only queries from the database, it does not save to the database
    //unless you instruct it to do so
    //Refer to slide 11 and 12 in the powerpoint presentation that I have sent you
    public Cursor returnInfo(){
    	if(db == null)
        {
        	db = dbhelper.getWritableDatabase();
        }
        return db.query(TABLE_NAME, new String[]{NAME, SURNAME,AGE,CITY, POSTAL_CODE}, null,null,null,null,null);
    }

}
