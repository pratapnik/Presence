package com.example.presence;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.presence.adapters.SubjectAdapter;
import com.example.presence.database.Subject;
import com.example.presence.database.SubjectViewModel;
import com.example.presence.widgets.AddSubjectDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AddSubjectsActivity extends AppCompatActivity implements AddSubjectDialog.SaveDetailsListener {

    AddSubjectDialog addSubjectDialog;
    SubjectViewModel subjectViewModel;
    RecyclerView rvSubjectsList;
    SubjectAdapter subjectAdapter;
    FloatingActionButton fabAddSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);

        //Temporary code for userdetails
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPref", 0);
        Log.d("nikhil", sharedPreferences.getString("user_name","null")+" "+
                sharedPreferences.getInt("class_value",0));

        rvSubjectsList = findViewById(R.id.rvSubjectsList);
        fabAddSubject = findViewById(R.id.fabAddSubject);

        subjectAdapter = new SubjectAdapter(this);
        rvSubjectsList.setAdapter(subjectAdapter);
        rvSubjectsList.setLayoutManager(new LinearLayoutManager(this));
        rvSubjectsList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);
        subjectViewModel.getAllSubjects().observe(this, new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                subjectAdapter.setItems(subjects);
            }
        });

        fabAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddSubjectsDialog();
            }
        });

    }

    private void openAddSubjectsDialog() {
        addSubjectDialog = new AddSubjectDialog(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        addSubjectDialog.show(fragmentTransaction, getResources().getString(R.string.label_add_subject_dialog));
    }

    @Override
    public void onSaveDetailsListener(@NotNull String subjectName, @NotNull String daysOfClass, int subjectPercent) {
        final Subject item = new Subject(subjectName, 0, 0, 0, daysOfClass);
        subjectViewModel.insert(item);
    }
}
