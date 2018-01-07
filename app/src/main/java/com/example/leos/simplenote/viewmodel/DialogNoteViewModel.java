package com.example.leos.simplenote.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.leos.simplenote.model.NoteRepository;

public class DialogNoteViewModel extends ViewModel {
    private NoteRepository noteRepository;

    public DialogNoteViewModel(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void deleteNote(String noteId) {
        noteRepository.deleteNote(noteId);
    }
}
