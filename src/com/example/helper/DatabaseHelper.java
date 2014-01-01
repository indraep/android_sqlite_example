package com.example.helper;


import java.util.ArrayList;
import java.util.List;

import com.example.model.Person;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static DatabaseHelper mInstance = null;
	
	// Logcat tag
    private static final String LOG = DatabaseHelper.class.getName();
 
    // Database Version
    private static final int DATABASE_VERSION = 2;
 
    // Database Name
    private static final String DATABASE_NAME = "sqlite_examplev2";
    
    // Create Person Table
    private static final String CREATE_TABLE_PERSON = "CREATE TABLE PERSON (" + 
    		"id INTEGER PRIMARY KEY," + 
    		"name TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public static DatabaseHelper getInstance(Context context) {
    	if (mInstance == null) {
    		mInstance = new DatabaseHelper(context.getApplicationContext());
    	}
    	return mInstance;
    }
    
	@Override
	public void onCreate(SQLiteDatabase db) { //
		Log.e(LOG, "onCreate method is executed");
		
		db.execSQL(CREATE_TABLE_PERSON);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.e(LOG, "onUpgrade method is executed");
		db.execSQL("ALTER TABLE PERSON ADD COLUMN age int");
	}
	
	/**
     * Creating a person
     */
    public long createPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put("name", person.getName());
        values.put("age", person.getAge());
 
        // insert row
        long person_id = db.insert("PERSON", null, values);
 
        return person_id;
    }
    
    /**
     * get single person
     */
    public Person getPerson(long person_id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        String selectQuery = "SELECT  * FROM PERSON WHERE "
                + "id = " + person_id;
 
        Log.e(LOG, selectQuery);
 
        Cursor c = db.rawQuery(selectQuery, null);
 
        if (c != null)
            c.moveToFirst();
 
        Person person = new Person();
        try {
	        person.setId(c.getInt(c.getColumnIndex("id")));
	        person.setName((c.getString(c.getColumnIndex("name"))));
	        person.setAge(Integer.parseInt(c.getString(c.getColumnIndex("age"))));
        }
        catch(Exception e) {}
        
        c.close();
        
        return person;
    }
    
    /**
     * getting all people
     * */
    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<Person>();
        String selectQuery = "SELECT  * FROM PERSON";
 
        Log.e(LOG, selectQuery);
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
            	Person person = new Person();
            	
            	try {
	                person.setId(c.getInt((c.getColumnIndex("id"))));
	                person.setName((c.getString(c.getColumnIndex("name"))));
	                person.setAge(Integer.parseInt(c.getString(c.getColumnIndex("age"))));
            	}
            	catch(Exception e) {
            		
            	}
            	finally {
            		// adding to person list
            		people.add(person);
            	}
            } while (c.moveToNext());
        }
 
        c.close();
        
        return people;
    }
    
    /**
     * Deleting a person
     */
    public void deletePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        // now delete the person
        db.delete("PERSON", "id = ?", new String[] { String.valueOf(person.getId()) });
    }
}
