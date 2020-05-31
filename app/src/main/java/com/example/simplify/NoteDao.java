package com.example.simplify;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Data Access Object
@Dao
public interface NoteDao {

    @Insert
    void insert(NoteClass noteClass);

    @Update
    void update(NoteClass noteClass);

    @Delete
    void delete(NoteClass noteClass);

    //@Query to pass raw query
    @Query("DELETE FROM notes")
    void deleteAllNotes();

    //LiveData<> to observe the <...>
    @Query("SELECT * FROM notes ORDER BY priority DESC")
    LiveData<List<NoteClass>> getAllNotes();
}
