package com.example.presence.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "subject")
public class Subject {
    @PrimaryKey(autoGenerate = true) @NonNull
    private int id;

    @ColumnInfo(name = "subject")
    private String subjectName;

    @ColumnInfo(name = "total")
    private int classCount;

    @ColumnInfo(name = "present")
    private int classPresent;

    @ColumnInfo(name = "absent")
    private int classAbsent;

    @ColumnInfo(name = "cancelled")
    private int classCancel;

    @ColumnInfo(name = "daysOfWeek")
    private String daysOfWeek;

    @Ignore
    public Subject(String subjectName, int classCount, int classAbsent, int classCancel, String daysOfWeek) {
        this.subjectName = subjectName;
        this.classCount = classCount;
        this.classAbsent = classAbsent;
        this.classCancel = classCancel;
        this.daysOfWeek = daysOfWeek;
    }

    public Subject(int id, String subjectName, int classCount, int classAbsent, int classCancel, String daysOfWeek) {
        this.id = id;
        this.subjectName = subjectName;
        this.classCount = classCount;
        this.classAbsent = classAbsent;
        this.classCancel = classCancel;
        this.daysOfWeek = daysOfWeek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getClassCount() {
        return classCount;
    }

    public void setClassCount(int classCount) {
        this.classCount = classCount;
    }

    public int getClassPresent() {
        return classPresent;
    }

    public void setClassPresent(int classPresent) {
        this.classPresent = classPresent;
    }

    public int getClassAbsent() {
        return classAbsent;
    }

    public void setClassAbsent(int classAbsent) {
        this.classAbsent = classAbsent;
    }

    public int getClassCancel() {
        return classCancel;
    }

    public void setClassCancel(int classCancel) {
        this.classCancel = classCancel;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
}
