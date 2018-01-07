package com.example.leos.simplenote.dependencyinjection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Leo on 06/01/2018.
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface RoomScope {
}
