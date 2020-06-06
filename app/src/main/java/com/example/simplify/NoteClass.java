package com.example.simplify;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//@Entity is used to create table in room database
@Entity(tableName = "notes")
public class NoteClass {

    @PrimaryKey(autoGenerate = true)
    private int id;
    //@ColumnInfo(name = "Title") can be used for alternate column name
    //@Ignore can be used to ignore  attribute
    private String title;
    private String content;
    private int priority;
    private int hour;
    private int minute;

    public NoteClass(String title, String content, int priority,int hour,int minute) {

        this.title = title;
        this.content = content;
        this.priority = priority;
        this.hour=hour;
        this.minute=minute;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getPriority() {
        return priority;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
