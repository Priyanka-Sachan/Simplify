package com.example.simplify;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    NoteRepository noteRepository;
    LiveData<List<NoteClass>> allNotes;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository=new NoteRepository(application);
        allNotes=noteRepository.getAllNotes();
    }
    void insert(NoteClass note){
        noteRepository.Insert(note);
    }
    void update(NoteClass note){
        noteRepository.Update(note);
    }
    void delete(NoteClass note){
        noteRepository.Delete(note);
    }
    void deleteAllNotes(){
        noteRepository.DeleteAllNotes();
    }
    LiveData<List<NoteClass>> getAllNotes(){
        return noteRepository.getAllNotes();
    }
}
