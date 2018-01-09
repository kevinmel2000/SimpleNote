package com.example.leos.simplenote.ui.edit;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
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
    public static final String ACTION_EDIT = "com.example.leos.simplenote.ui.edit.action_edit";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fl_edit_container) FrameLayout flContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

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

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0){
            showDialogExitConfirmation();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home :
                NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialogExitConfirmation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Cancel Confirmation")
                .setMessage("Are sure want to cancel? The note is not gonna be saved")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
