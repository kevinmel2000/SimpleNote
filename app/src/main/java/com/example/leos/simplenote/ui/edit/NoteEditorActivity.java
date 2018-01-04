package com.example.leos.simplenote.ui.edit;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.leos.simplenote.R;
import com.example.leos.simplenote.model.room.Note;
import com.example.leos.simplenote.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteEditorActivity extends BaseActivity {

    public static final String ACTION_NEW = "com.example.leos.simplenote.ui.edit.action_new";
    public static final String ACTION_VIEW = "com.example.leos.simplenote.ui.edit.action_view";
    public static final String ACTION_EDIT = "com.example.leos.simplenote.ui.edit.action_edit";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fl_edit_container) FrameLayout flContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String actionReceived = getIntent().getAction();

        NoteEditorFragment fragment = new NoteEditorFragment();

        if (actionReceived.equals(ACTION_NEW)) {
            fragment.setArguments(null);

            replaceFragment(
                    fragment,
                    R.id.fl_edit_container,
                    ACTION_NEW,
                    "New Note");
        } else if (actionReceived.equals(ACTION_EDIT)) {
            Bundle args = new Bundle();
            args.putParcelable("Note", getIntent().getParcelableExtra("Note"));
            fragment.setArguments(args);

            replaceFragment(
                    fragment,
                    R.id.fl_edit_container,
                    ACTION_EDIT,
                    "Edit Note");
        }
    }
}
