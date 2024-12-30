package com.example.calenderapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context){
        super(context, "Events.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table Event_table(TimeStamp text, Title text, Description text)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists already");
        onCreate(db);
    }

    public boolean save_event(Event event){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TimeStamp", event.getDate());
        values.put("Title", event.getTitle());
        values.put("Description", event.getDescription());

        long inserted = db.insert("Event_table", null, values);
        if (inserted == -1){
            return false;
        }else {
            return true;
        }
    }

    public ArrayList<Event> view_event(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Event> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Event_table", null);
        if (cursor.moveToFirst()){
            do{
                String timeStamp = cursor.getString(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                Event event = new Event(timeStamp, title, description);
                arrayList.add(event);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return arrayList;
    }
}
