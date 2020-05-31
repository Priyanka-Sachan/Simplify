package com.example.simplify;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//More entities can be added in the array
@Database(entities = {NoteClass.class},version = 1)
public abstract class Simplify_database extends RoomDatabase {

    //only one instance of database
    private static Simplify_database instance;
    public abstract NoteDao noteDao();

    public static synchronized Simplify_database getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    Simplify_database.class,"simplify_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }





}
