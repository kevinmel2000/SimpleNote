package com.example.leos.simplenote.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leos.simplenote.R;
import com.example.leos.simplenote.model.room.Note;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder>{
    private List<Note> notes;
    private Context mContext;

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public NoteListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NoteListAdapter.ViewHolder holder, int position) {
        holder.noteTitle.setText(notes.get(position).getTitle());
        holder.noteCreatedDate.setText((notes.get(position).getFormatedDate()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView noteTitle, noteCreatedDate;

        public ViewHolder(View itemView) {
            super(itemView);
            noteTitle = (TextView) itemView.findViewById(R.id.tv_item_note_title);
            noteCreatedDate = (TextView) itemView.findViewById(R.id.tv_item_note_created_date);
        }
    }
}
