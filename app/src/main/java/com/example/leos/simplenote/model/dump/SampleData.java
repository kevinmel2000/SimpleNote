package com.example.leos.simplenote.model.dump;

import com.example.leos.simplenote.model.room.Note;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Leo on 23/05/2017.
 */

public class SampleData {
    public static List<Note> getSampleNotes() {
        List<Note> sampleNotes = new ArrayList<Note>();


        //create the dummy note
        Note note1 = new Note();
        note1.setTitle("DisneyLand Trip");
        note1.setContent("We went to Disneyland today and the kids had lots of fun!");
        note1.getFormatedDate();
        //add note1 to the list
        sampleNotes.add(note1);


        //create the dummy note
        Note note2 = new Note();
        note2.setTitle("Gym Work Out");
        note2.setContent("I went to the Gym today and I got a lot of exercises");

        note1.getFormatedDate();

        //add note2 to the list
        sampleNotes.add(note2);

        //create the dummy note
        Note note3 = new Note();
        note3.setTitle("Blog Post Idea");
        note3.setContent("I will like to write a blog post about how to make money online");

        //change the date to random time
        note1.getFormatedDate();

        //add note3 to the list
        sampleNotes.add(note3);


        //create the dummy note
        Note note4 = new Note();
        note4.setTitle("Cupcake Recipe");
        note4.setContent("Today I found a recipe to make cup cake from www.google.");

        //pad the date with random number of days and minute
        //so all the notes do not have the same time stamp
        note1.getFormatedDate();

        //add note4 to the list
        sampleNotes.add(note4);


        //create the dummy note
        Note note5 = new Note();
        note5.setTitle("Notes from Networking Event");
        note5.setContent("Today I attended a developer's networking event and it was great");

        //pad the date with two days
        //pad the date with random number of days and minute
        //so all the notes do not have the same time stamp
        note1.getFormatedDate();

        //add note5 to the list
        sampleNotes.add(note5);

        return sampleNotes;
    }
}
