package com.example.leos.simplenote.ui.main;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.leos.simplenote.R;
import com.example.leos.simplenote.dependencyinjection.AppComponent;
import com.example.leos.simplenote.dependencyinjection.ContextModule;
import com.example.leos.simplenote.dependencyinjection.DaggerAppComponent;
import com.example.leos.simplenote.dependencyinjection.RoomModule;
import com.example.leos.simplenote.model.NoteRepository;
import com.example.leos.simplenote.model.room.NoteDao;
import com.example.leos.simplenote.model.room.NoteDatabase;
import com.example.leos.simplenote.ui.adapter.NoteListAdapter;
import com.example.leos.simplenote.model.room.Note;
import com.example.leos.simplenote.ui.edit.NoteEditorActivity;
import com.example.leos.simplenote.utilities.ItemClickSupport;
import com.example.leos.simplenote.viewmodel.HomeViewModel;
import com.example.leos.simplenote.viewmodel.NoteViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.fab_main) FloatingActionButton fab;
    @BindView(R.id.rv_main_list_note) RecyclerView rvHomeNoteList;

    private HomeViewModel viewModel;
    private NoteListAdapter listAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, mRootView);

        listAdapter = new NoteListAdapter(getActivity());
        fab.setOnClickListener(this);

        return mRootView;
    }

    private ViewModelProvider.Factory factory;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*if (viewModel == null){
            NoteDatabase noteDatabase = NoteDatabase.getsInstance(getContext());
            NoteDao noteDao = noteDatabase.noteDao();
            NoteRepository noteRepository = new NoteRepository(noteDao);
            NoteViewModelFactory viewModelFactory = new NoteViewModelFactory(noteRepository);
            viewModel= ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);

        }*/



        if (viewModel == null){
            AppComponent appComponent = DaggerAppComponent
                    .builder()
                    .contextModule(new ContextModule(getContext()))
                    .build();
            factory = appComponent.geViewModelFactory();
            viewModel= ViewModelProviders.of(this, factory).get(HomeViewModel.class);
        }

        viewModel.getAllNote().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                Log.w("MAIN FRAGMENT", "onChanged: " + notes.size() );
                ArrayList<Note> notesArr = new ArrayList<>();
                notesArr.addAll(notes);
                showNoteListInListView(notesArr);
            }
        });

    }

    private void showNoteListInListView(ArrayList<Note> notes) {
        rvHomeNoteList.setHasFixedSize(true);
        rvHomeNoteList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHomeNoteList.setAdapter(listAdapter);

        listAdapter.setNotes(notes);

        ItemClickSupport.addTo(rvHomeNoteList).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        startNoteDetail(listAdapter.getNotes().get(position));
            }
        });

        //add setOnlongclicklistener
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_main :
                startEditorActivity(NoteEditorActivity.ACTION_NEW, null);
                break;
        }
    }

    public void startNoteDetail(Note note) {
        Bundle args = new Bundle();
        args.putParcelable("Note", note);

        DialogNoteFragment dialogNoteFragment = new DialogNoteFragment();
        dialogNoteFragment.setArguments(args);
        dialogNoteFragment.show(
                getChildFragmentManager(),
                DialogNoteFragment.class.getSimpleName());
    }

    private void startEditorActivity(String action, Note note){
        Intent intent = new Intent(getActivity(), NoteEditorActivity.class);
        intent.setAction(action);
        getActivity().startActivity(intent);
    }

    public void showToast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
