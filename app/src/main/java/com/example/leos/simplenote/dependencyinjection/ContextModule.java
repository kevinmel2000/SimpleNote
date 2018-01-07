package com.example.leos.simplenote.dependencyinjection;

import android.content.Context;

import dagger.Module;
import dagger.Provides;


@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    public Context provideContext(){
        return context;
    }
}
