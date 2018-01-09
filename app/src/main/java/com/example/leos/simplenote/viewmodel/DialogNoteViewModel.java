package com.example.leos.simplenote.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.example.leos.simplenote.model.NoteRepository;

public class DialogNoteViewModel extends ViewModel {
    private NoteRepository noteRepository;

    public DialogNoteViewModel(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void deleteNote(String noteId) {
        new AsyncDelete().execute(noteId);
    }
    private class AsyncDelete extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            noteRepository.deleteNote(params[0]);
            return null;
        }
    }
}
