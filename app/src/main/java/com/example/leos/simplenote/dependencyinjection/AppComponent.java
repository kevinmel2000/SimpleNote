package com.example.leos.simplenote.dependencyinjection;

import android.arch.lifecycle.ViewModelProvider;

import dagger.Component;

@RoomScope
@Component(modules = {RoomModule.class})
public interface AppComponent {
    ViewModelProvider.Factory geViewModelFactory();
}
