package com.example.leos.simplenote.ui.edit;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leos.simplenote.R;
import com.example.leos.simplenote.model.NoteRepository;
import com.example.leos.simplenote.model.room.Note;
import com.example.leos.simplenote.model.room.NoteDao;
import com.example.leos.simplenote.model.room.NoteDatabase;
import com.example.leos.simplenote.utilities.DateUtils;
import com.example.leos.simplenote.viewmodel.EditorViewModel;
import com.example.leos.simplenote.viewmodel.NoteViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditorFragment extends Fragment {
    private static final String NOTE = "note";

    @BindView(R.id.edt_edit_note_title) EditText edtTitle;
    @BindView(R.id.edt_edit_note_content) EditText edtContent;

    private EditorViewModel viewModel;


    public NoteEditorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_editor, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (viewModel == null){
            NoteDao noteDao = NoteDatabase.getsInstance(getContext()).noteDao();
            NoteRepository repository = new NoteRepository(noteDao);
            NoteViewModelFactory viewModelFactory = new NoteViewModelFactory(repository);

            viewModel = ViewModelProviders
                    .of(this, viewModelFactory)
                    .get(EditorViewModel.class);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save :
                saveNote();
                break;
            case R.id.home :{
                getActivity().finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private String getNoteTitle(){
        return edtTitle.getText().toString();
    }

    private String getNoteContent(){
        return edtContent.getText().toString();
    }

    public void saveNote(){
        if (!getNoteTitle().isEmpty() && !getNoteContent().isEmpty()){
            Note note = new Note();
            note.setTitle(getNoteTitle());
            note.setContent(getNoteContent());

            if (getArguments() != null){
                note.setDateCreated(DateUtils.getCurrentDateTime());
            }else {
                note.setDateModified(DateUtils.getCurrentDateTime());
            }
            viewModel.insertNote(note);
            getActivity().finish();
        }else {
            showToast("Input may not empty!");
        }

    }
    private void showToast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
