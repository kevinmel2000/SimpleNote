package com.example.leos.simplenote.dependencyinjection;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.leos.simplenote.model.NoteRepository;
import com.example.leos.simplenote.model.room.Note;
import com.example.leos.simplenote.model.room.NoteDao;
import com.example.leos.simplenote.model.room.NoteDatabase;
import com.example.leos.simplenote.viewmodel.NoteViewModelFactory;

import dagger.Module;
import dagger.Provides;


@RoomScope
@Module(includes = ContextModule.class)
public class RoomModule {

    @RoomScope
    @Provides
    public NoteDatabase provideNoteDatabase(Context context){
        return  Room.databaseBuilder(
                context,
                NoteDatabase.class,
                Note.TABLE_NAME
        ).build();
    }

    @RoomScope
    @Provides
    public NoteDao provideNoteDao(NoteDatabase database){
        return database.noteDao();
    }

    @RoomScope
    @Provides
    public NoteRepository provideNoteRepository(NoteDao noteDao){
        return new NoteRepository(noteDao);
    }

    @RoomScope
    @Provides
    public ViewModelProvider.Factory getVieFactory(NoteRepository noteRepository){
        return new NoteViewModelFactory(noteRepository);
    }
}
