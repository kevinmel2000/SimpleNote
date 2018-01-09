package com.example.leos.simplenote.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leos.simplenote.R;
import com.example.leos.simplenote.model.room.Note;
import com.example.leos.simplenote.utilities.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Leo on 1/8/2018.
 */

public class NoteCardAdapter extends RecyclerView.Adapter<NoteCardAdapter.NoteCardViewHolder> {
    private List<Note> notes;
    private Context mContext;

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public NoteCardAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public NoteCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_card, parent, false);
        NoteCardAdapter.NoteCardViewHolder viewHolder = new NoteCardAdapter.NoteCardViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NoteCardViewHolder holder, int position) {
        Note note = getNotes().get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvContent.setText(note.getContent());
        if (note.getDateModified() != null){
            holder.tvCreatedDate.setText("Modified At : " +
                    DateUtils.getFormatedDate(note.getDateModified())
                    + ", " +
                    DateUtils.getFormatedTime(note.getDateModified()));
        }else if (note.getDateCreated() != null) {
            holder.tvCreatedDate.setText("Created At : " +
                    DateUtils.getFormatedDate(note.getDateCreated())
                    + ", " +
                    DateUtils.getFormatedTime(note.getDateCreated()));
        }


        final boolean marked = false; //note.getMarked();
        if (marked){
            holder.imgBookmark.setColorFilter(ContextCompat.getColor(mContext, R.color.primary));
        }
        else {
            holder.imgBookmark.setColorFilter(ContextCompat.getColor(mContext, R.color.secondary_text));
        }
        holder.imgBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (marked){
                   //remove mark
               }else {
                   //add mark
               }
            }
        });
    }

    @Override
    public int getItemCount() {
        return getNotes().size();
    }

    public class NoteCardViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_note_title) TextView tvTitle;
        @BindView(R.id.tv_item_note_content) TextView tvContent;
        @BindView(R.id.tv_item_note_created_date) TextView tvCreatedDate;
        @BindView(R.id.img_item_note_bookmark) ImageView imgBookmark;

        public NoteCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
