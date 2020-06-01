package com.example.simplify;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<NoteClass>> allNotes;

    public NoteRepository(Application application){

        //since application is a subclass of context
        Simplify_database simplify_database= Simplify_database.getInstance(application);
        //Though it was abstract method,it has been populated by ROOM,hence we can use it here
        noteDao=simplify_database.noteDao();
        allNotes=noteDao.getAllNotes();
    }

    public void Insert(NoteClass noteClass){
        new InsertNoteAsyncTask(noteDao).execute(noteClass);
    }
    public void Update(NoteClass noteClass){
        new UpdateNoteAsyncTask(noteDao).execute(noteClass);
    }
    public void Delete(NoteClass noteClass){
        new DeleteNoteAsyncTask(noteDao).execute(noteClass);
    }
    public void DeleteAll(){
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }
    //LiveData process is automatically done on background thread
    public LiveData<List<NoteClass>> getAllNotes(){
        return allNotes;
    }

    public static class InsertNoteAsyncTask extends AsyncTask<NoteClass,Void,Void>{

        NoteDao noteDao;
        public InsertNoteAsyncTask (NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(NoteClass... noteClasses) {
            noteDao.insert(noteClasses[0]);
            return null;
        }
    }

    public static class UpdateNoteAsyncTask extends AsyncTask<NoteClass,Void,Void>{

        NoteDao noteDao;
        public UpdateNoteAsyncTask (NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(NoteClass... noteClasses) {
            noteDao.update(noteClasses[0]);
            return null;
        }
    }

    public static class DeleteNoteAsyncTask extends AsyncTask<NoteClass,Void,Void>{

        NoteDao noteDao;
        public DeleteNoteAsyncTask (NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(NoteClass... noteClasses) {
            noteDao.delete(noteClasses[0]);
            return null;
        }
    }

    public static class DeleteAllNotesAsyncTask extends AsyncTask<Void,Void,Void>{

        NoteDao noteDao;
        public DeleteAllNotesAsyncTask (NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Void...voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
