package com.example.saurabh.to_do_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saura on 8/21/2017.
 */

public class ToDoDatabase extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "todoDatabase";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TODO= "todotable";

    private static final String ToDoItem="todoitem";
    private static final  String Date="date";
    private static final String ToDoNotes="todonotes";
    private static final String Status="status";
    private static final String Priority="priority";


    private static ToDoDatabase sInstance;


    public ToDoDatabase(Context applicationContext)
    {
        super(applicationContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized ToDoDatabase getInstance(Context context)
    {
        if (sInstance == null)
        {
            sInstance = new ToDoDatabase(context.getApplicationContext());
        }
        return sInstance;
    }

    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);

    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TODO + "("
                + "ID INTEGER PRIMARY KEY," + ToDoItem + " TEXT,"
                + Date + " TEXT, " + Status + " TEXT," + ToDoNotes + " TEXT, "+ Priority + " TEXT )";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        // Create tables again
        onCreate(db);
    }

    public void addToDo(ToDoClass toDoClass)
    {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(ToDoItem, toDoClass.getToDoItem());
            values.put(Date, toDoClass.getDate());
            values.put(ToDoNotes, toDoClass.getToDoNotes());
            values.put(Status, toDoClass.getStatus());
            values.put(Priority, toDoClass.getPriority());

            Log.d("Sqlite test", "values="+values.getAsString(Priority));
            db.insertOrThrow(TABLE_TODO, null, values);
            db.setTransactionSuccessful();

        } catch (Exception e)
        {
            Log.d("Sqlite call", "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }

    // Get all posts in the database
    public List<ToDoClass> getAllToDo() {
        List<ToDoClass> toDolist = new ArrayList<>();
        String All_SELECT_QUERY =
                String.format("SELECT * FROM  %s ;",
                        TABLE_TODO
                        );

        Log.d("Sqlite test", "POSTS_SELECT_QUERY="+All_SELECT_QUERY);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(All_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    String newtodotem = cursor.getString(cursor.getColumnIndex(ToDoItem));
                    String newtodonote = cursor.getString(cursor.getColumnIndex(ToDoNotes));
                    String newtodostatus = cursor.getString(cursor.getColumnIndex(Status));
                    String newtodopriority = cursor.getString(cursor.getColumnIndex(Priority));
                    String newtododate = cursor.getString(cursor.getColumnIndex(Date));
                    Integer id=cursor.getInt(cursor.getColumnIndex("ID"));

                    Log.d("Sqlite test", "id="+id);

                    ToDoClass newItem = new ToDoClass(id,newtodotem,newtododate,newtodonote,newtodopriority,newtodostatus);
                    toDolist.add(newItem);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("Sqlite test", "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return toDolist;
    }

    // Get single  posts in the database
    public ToDoClass getSingleToDo(Integer Id) {
        ToDoClass newItem = null;
        String SELECT_QUERY = String.format("SELECT * FROM "+ TABLE_TODO + " where ID="+Id);


        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY, null);
        Log.d("Sqlite test", SELECT_QUERY);
        Log.d("Sqlite test", "id="+cursor.getCount());
        cursor.moveToFirst();

        String newtodotem = cursor.getString(cursor.getColumnIndex(ToDoItem));
        String newtodonote = cursor.getString(cursor.getColumnIndex(ToDoNotes));
        String newtodostatus = cursor.getString(cursor.getColumnIndex(Status));
        String newtodopriority = cursor.getString(cursor.getColumnIndex(Priority));
        String newtododate = cursor.getString(cursor.getColumnIndex(Date));
        Integer id=cursor.getInt(cursor.getColumnIndex("ID"));
        Log.d("Sqlite test", "id="+id);
        newItem= new ToDoClass(id,newtodotem,newtododate,newtodonote,newtodopriority,newtodostatus);
        cursor.close();

        return newItem;
    }

    public int updateToDoItem(ToDoClass toDoClass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ToDoItem, toDoClass.getToDoItem());
        values.put(Date, toDoClass.getDate());
        values.put(ToDoNotes, toDoClass.getToDoNotes());
        values.put(Status, toDoClass.getStatus());
        values.put(Priority, toDoClass.getPriority());


        // Updating profile picture url for user with that userName
        return db.update(TABLE_TODO, values,"ID = ?",
                new String[] { String.valueOf(toDoClass.getId()) });
    }

    // Delete all posts and users in the database
    public void deleteToDoItem(Integer id) {
        SQLiteDatabase db = getWritableDatabase();
        Log.d("sqlite", "id="+id);
        db.beginTransaction();
        try {
           // db.delete(TABLE_TODO,"ID="+"'"+id+"'",null);
            db.execSQL("DELETE FROM "+TABLE_TODO +" WHERE ID="+id);

            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("sqlite", "Error while trying to delete all posts and users");
        } finally {
            db.endTransaction();
        }
    }

}
