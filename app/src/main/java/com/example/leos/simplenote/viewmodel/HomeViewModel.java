package com.example.leos.simplenote.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.leos.simplenote.model.NoteRepository;
import com.example.leos.simplenote.model.room.Note;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private NoteRepository noteRepository;

    public HomeViewModel(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    //i can use rxJava here
    public LiveData<List<Note>> getAllNote(){
        return noteRepository.getAllNote();
    }

    public LiveData<Note> getNote(String noteId){
        return noteRepository.getNote(noteId);
    }

    public void deleteNote(String noteId){
        noteRepository.deleteNote(noteId);
    }
}
