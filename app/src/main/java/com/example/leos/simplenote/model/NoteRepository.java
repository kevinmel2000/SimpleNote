package com.example.leos.simplenote.model;

import android.arch.lifecycle.LiveData;

import com.example.leos.simplenote.model.room.Note;
import com.example.leos.simplenote.model.room.NoteDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 03/01/2018.
 */

public class NoteRepository {
    private NoteDao noteDao;

    public NoteRepository(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public LiveData<List<Note>> getAllNote(){
        return noteDao.getAllNote();
    }

    public LiveData<Note> getNote(String noteId){
        return noteDao.getNote(noteId);
    }

    public long insertNote(Note note){
        return noteDao.insertNote(note);
    }

    public void deleteNote(String noteId){
        noteDao.deleteNote(noteId);
    }
}
