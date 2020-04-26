package com.example.presence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.presence.adapters.SubjectAdapter;
import com.example.presence.database.Subject;
import com.example.presence.database.SubjectViewModel;
import com.example.presence.widgets.AddSubjectDialog;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AddSubjectsActivity extends AppCompatActivity implements AddSubjectDialog.SaveDetailsListener {

    Button btnAddSubjects;
    AddSubjectDialog addSubjectDialog;
    SubjectViewModel subjectViewModel;
    RecyclerView rvSubjectsList;
    SubjectAdapter subjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);

        rvSubjectsList = findViewById(R.id.rvSubjectsList);
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

        btnAddSubjects = findViewById(R.id.btnAddSubjects);
        btnAddSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddSubjectsDialog();
            }
        });
    }

    private void openAddSubjectsDialog(){
        addSubjectDialog = new AddSubjectDialog(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        addSubjectDialog.show(fragmentTransaction, getResources().getString(R.string.label_add_subject_dialog));
    }

    @Override
    public void onSaveDetailsListener(@NotNull String subjectName, @NotNull String daysOfClass) {
        final Subject item = new Subject(subjectName, 0, 0, 0, daysOfClass);
        subjectViewModel.insert(item);
    }
}
