package com.example.presence.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SubjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Subject subject);

    @Delete
    void delete(Subject subject);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void modify(Subject subject);

    @Query("SELECT * FROM subject ORDER BY id DESC")
    LiveData<List<Subject>> getAllSubjects();
}
