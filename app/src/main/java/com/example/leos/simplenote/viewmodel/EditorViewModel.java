package com.example.leos.simplenote.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.example.leos.simplenote.model.NoteRepository;
import com.example.leos.simplenote.model.room.Note;

/**
 * Created by Leo on 03/01/2018.
 */

public class EditorViewModel extends ViewModel {

    private NoteRepository noteRepository;

    public EditorViewModel(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void insertNote(Note note){
        new AsyncInsert().execute(note);
    }

    private class AsyncInsert extends AsyncTask<Note, Void, Void>{
        @Override
        protected Void doInBackground(Note... params) {
            noteRepository.insertNote(params[0]);
            return null;
        }
    }
}
