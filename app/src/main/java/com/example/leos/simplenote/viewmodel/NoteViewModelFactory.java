package com.example.leos.simplenote.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.leos.simplenote.model.NoteRepository;

public class NoteViewModelFactory implements ViewModelProvider.Factory {
    private NoteRepository repository;

    public NoteViewModelFactory(NoteRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)){
            return (T) new HomeViewModel(repository);
        }
        else if (modelClass.isAssignableFrom(EditorViewModel.class)){
            return (T) new EditorViewModel(repository);
        }
        else if (modelClass.isAssignableFrom(DialogNoteViewModel.class)){
            return (T) new DialogNoteViewModel(repository);
        }
        else {
            throw new IllegalArgumentException("View Model Not Found");
        }
    }
}
