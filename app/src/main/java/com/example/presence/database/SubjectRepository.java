package com.example.presence.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SubjectRepository {
    private SubjectDao subjectDao;
    private LiveData<List<Subject>> subjectList;

    SubjectRepository(Application application) {
        Database db = Database.getDatabase(application);
        subjectDao = db.subjectDao();
        subjectList = subjectDao.getAllSubjects();
    }

    LiveData<List<Subject>> getAllSubjects() {
        return subjectList;
    }

    void insert(Subject subject) {
        new InsertTask(subjectDao).execute(subject);
    }

    void delete(Subject subject) {
        new DeleteTask(subjectDao).execute(subject);
    }

    void modify(Subject subject) {
        new ModifyTask(subjectDao).execute(subject);
    }

    private static class InsertTask extends AsyncTask<Subject, Void, Void> {
        SubjectDao mDao;

        InsertTask(SubjectDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Subject... subjects) {
            mDao.insert(subjects[0]);
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Subject, Void, Void> {
        SubjectDao mDao;

        DeleteTask(SubjectDao dao) {
            mDao = dao;
        }
        @Override
        protected Void doInBackground(Subject... subjects) {
            mDao.delete(subjects[0]);
            return null;
        }
    }

    private static class ModifyTask extends AsyncTask<Subject, Void, Void> {
        SubjectDao mDao;

        ModifyTask(SubjectDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Subject... subjects) {
            mDao.modify(subjects[0]);
            return null;
        }
    }
}
