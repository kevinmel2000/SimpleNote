package com.example.leos.simplenote.model.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import javax.inject.Inject;


@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static NoteDatabase sInstance;


    public static synchronized NoteDatabase getsInstance(Context context) {
        if (sInstance == null){
            sInstance = Room.databaseBuilder(
                    context,
                    NoteDatabase.class,
                    Note.TABLE_NAME)
                    .build();
        }
        return sInstance;
    }
}
