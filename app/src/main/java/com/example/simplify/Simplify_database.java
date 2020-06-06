package com.example.simplify;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//More entities can be added in the array
@Database(entities = {NoteClass.class},version = 2)
public abstract class Simplify_database extends RoomDatabase {

    //only one instance of database
    private static Simplify_database instance;
    public abstract NoteDao noteDao();

    public static synchronized Simplify_database getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    Simplify_database.class,"simplify_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;

    }
    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback(){
      @Override
      public void onCreate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase){
          super.onCreate(supportSQLiteDatabase);
          new PopulateDbAsyncTask(instance).execute();
      }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        private PopulateDbAsyncTask(Simplify_database simplify_database){
            this.noteDao=simplify_database.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new NoteClass("Title 1","Content 1",1,2,3));
            noteDao.insert(new NoteClass("Title 2","Content 2",2,5,4));
            noteDao.insert(new NoteClass("Title 3","Content 3",3,7,21));
            return null;
        }
    }




}
