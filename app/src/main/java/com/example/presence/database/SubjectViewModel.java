package com.example.presence.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SubjectViewModel extends AndroidViewModel {
    private SubjectRepository repository;
    private LiveData<List<Subject>> subjectList;

    public SubjectViewModel(@NonNull Application application) {
        super(application);
        repository = new SubjectRepository(application);
        subjectList = repository.getAllSubjects();
    }

    public LiveData<List<Subject>> getAllSubjects() {
        return subjectList;
    }

    public void insert(Subject subject) {
        repository.insert(subject);
    }

    public void delete(Subject subject) {
        repository.delete(subject);
    }

    public void modify(Subject subject) {
        repository.modify(subject);
    }
}
