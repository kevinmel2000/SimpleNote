package com.example.leos.simplenote.ui.main;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.leos.simplenote.R;
import com.example.leos.simplenote.model.NoteRepository;
import com.example.leos.simplenote.model.room.Note;
import com.example.leos.simplenote.model.room.NoteDao;
import com.example.leos.simplenote.model.room.NoteDatabase;
import com.example.leos.simplenote.ui.edit.NoteEditorActivity;
import com.example.leos.simplenote.utilities.DateUtils;
import com.example.leos.simplenote.viewmodel.DialogNoteViewModel;
import com.example.leos.simplenote.viewmodel.NoteViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogNoteFragment extends DialogFragment implements View.OnClickListener {
    @BindView(R.id.tv_dialog_note_title) TextView tvTitle;
    @BindView(R.id.tv_dialog_note_content) TextView tvContent;
    @BindView(R.id.tv_dialog_note_created_date) TextView tvCreatedDate;
    @BindView(R.id.tv_dialog_note_modified_date) TextView tvModifiedDate;
    @BindView(R.id.btn_dialog_note_edit) Button btnEdit;
    @BindView(R.id.btn_dialog_note_delete) Button btnDelete;

    private DialogNoteViewModel viewModel;
    private Note note;

    public DialogNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_note, container, false);
        ButterKnife.bind(this, view);

        note = getArguments().getParcelable("Note");
        tvTitle.setText(note.getTitle());
        tvContent.setText(note.getContent());
        if (note.getDateCreated() != null){
            tvCreatedDate.setText("Created At : " +
                    DateUtils.getFormatedDate(note.getDateCreated())
                    + ", " +
                    DateUtils.getFormatedTime(note.getDateCreated()));
        }
        if (note.getDateModified() != null) {
            tvModifiedDate.setText("Modified At : " +
                    DateUtils.getFormatedDate(note.getDateModified())
                    + ", " +
                    DateUtils.getFormatedTime(note.getDateModified()));
        }

        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NoteDao dao = NoteDatabase.getsInstance(getContext()).noteDao();
        NoteRepository repository = new NoteRepository(dao);
        ViewModelProvider.Factory factory = new NoteViewModelFactory(repository);
        viewModel = ViewModelProviders.of(this, factory).get(DialogNoteViewModel.class);
    }

    private void startNoteEditorActivity() {
        Intent intent = new Intent(getActivity(), NoteEditorActivity.class);
        intent.putExtra("Note", getArguments().getParcelable("Note"));
        intent.setAction(NoteEditorActivity.ACTION_EDIT);
        startActivity(intent);
    }

    private void deleteNote() {
        viewModel.deleteNote(String.valueOf(note.getId()));
        dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_note_edit:
                startNoteEditorActivity();
                break;
            case R.id.btn_dialog_note_delete:
                deleteNote();
                break;
        }
    }
}
