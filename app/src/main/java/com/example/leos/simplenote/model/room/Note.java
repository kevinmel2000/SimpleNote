package com.example.leos.simplenote.model.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Leo on 13/05/2017.
 */

@Entity(tableName = Note.TABLE_NAME)
public class Note implements Parcelable {
    public static final String TABLE_NAME = "notes";

    @PrimaryKey(autoGenerate = true) private long id;
    private String title;
    private String content;
    private Long dateCreated;
    private Long dateModified;


    public Note() {
    }

    @Ignore
    public Note(long id, String title, String content, Long dateCreated, Long dateModified) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getDateModified() {
        return dateModified;
    }

    public void setDateModified(Long dateModified) {
        this.dateModified = dateModified;
    }

    public String getFormatedDate(){
        Date now = new Date();
        String format1 = "yyyy-MM-dd  HH:mm:ss";
        String format2 = "MMM dd, yyyy - h:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(format2, Locale.getDefault());
        String displayDate = sdf.format(now);
        return displayDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeValue(this.dateCreated);
        dest.writeValue(this.dateModified);
    }

    protected Note(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.content = in.readString();
        this.dateCreated = (Long) in.readValue(Long.class.getClassLoader());
        this.dateModified = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
