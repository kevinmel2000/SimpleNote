package com.example.leos.simplenote.ui.main;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import com.example.leos.simplenote.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    private String title = "New Era";
    private String content = "Whatever it is";

    private String newTitle = "Edith";
    private String newContent = "The elegance";

    @Before
    public void setUp() throws Exception {

    }

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    //the test should run with only 1 item in rv
    private int position = 1;

    @Test
    public void addNote() {
        //click fab in main then open add note activity
        onView(withId(R.id.fab_main))
                .perform(click());

        //input the data that needed
        onView(withId(R.id.edt_edit_note_title))
                .perform(typeText(title));
        onView(withId(R.id.edt_edit_note_content))
                .perform(typeText(content), closeSoftKeyboard());

        //click done to save the note
        onView(withId(R.id.action_save))
                .perform(click());

        //verify if the note is displayed
        onView(ViewMatchers.withText(title))
                .check(matches(isDisplayed()));
    }

    @Test
    public void showNote(){
        //click item in recycler view with the title
        onView(withId(R.id.rv_main_list_note))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));

        onView(withId(R.id.tv_dialog_note_title))
                .check(matches(isDisplayed()));
        onView(withText(title))
                .check(matches(isDisplayed()));
    }


    @Test
    public void editNote(){
        //click item at position in recycler view
        onView(withId(R.id.rv_main_list_note))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));

        //Click edit
        onView(withId(R.id.btn_dialog_note_edit)).perform(click());

        //clear text then input the data that needed
        onView(withId(R.id.edt_edit_note_title))
                .perform(clearText(), typeText(newTitle));
        onView(withId(R.id.edt_edit_note_content))
                .perform(clearText(), typeText(newContent), closeSoftKeyboard());

        //click done
        onView(withId(R.id.action_save))
                .perform(click());

        //verify if the note saved and displayed at the main activity
        onView(withText(newTitle))
                .check(matches(isDisplayed()));
    }

    @Test
    public void deleteNote(){
        //click item at position in recycler view
        onView(withId(R.id.rv_main_list_note))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));

        //Click edit
        onView(withId(R.id.btn_dialog_note_delete))
                .perform(click());

        //verify if the note is deleted
        //if the item didn't edited use title, otherwise use newTitle;
        onView(withText(newTitle))
                .check(doesNotExist());
    }
}