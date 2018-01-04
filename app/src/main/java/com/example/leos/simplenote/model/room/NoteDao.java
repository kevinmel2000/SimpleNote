package com.example.leos.simplenote.model.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 03/01/2018.
 */

@Dao
public interface NoteDao {

    @Query("SELECT * FROM " + Note.TABLE_NAME)
    LiveData<List<Note>> getAllNote();

    @Query("SELECT * FROM " + Note.TABLE_NAME + " WHERE id = :noteId")
    LiveData<Note> getNote(String noteId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertNote(Note note);

    @Query("DELETE FROM " + Note.TABLE_NAME + " WHERE id = :noteId")
    void deleteNote(String noteId);
}
