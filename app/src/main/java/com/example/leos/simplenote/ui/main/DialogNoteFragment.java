package com.example.leos.simplenote.ui.main;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leos.simplenote.R;
import com.example.leos.simplenote.model.room.Note;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogNoteFragment extends DialogFragment {
    @BindView(R.id.tv_dialog_note_title) TextView tvTitle;

    private static DialogNoteFragment instance;

    public DialogNoteFragment() {
        // Required empty public constructor
    }

    public static DialogNoteFragment getInstance() {
        if (instance == null){
            instance = new DialogNoteFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_note, container, false);
        ButterKnife.bind(this, view);

        Note note = getArguments().getParcelable("Note");
        tvTitle.setText(note.getTitle());

        return view;
    }

}
