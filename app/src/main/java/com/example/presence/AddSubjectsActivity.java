package com.example.presence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.presence.widgets.AddSubjectDialog;

import org.jetbrains.annotations.NotNull;

public class AddSubjectsActivity extends AppCompatActivity implements AddSubjectDialog.SaveDetailsListener {

    Button btnAddSubjects;
    AddSubjectDialog addSubjectDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);

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
        Toast.makeText(getApplicationContext(), subjectName+" "+daysOfClass, Toast.LENGTH_LONG).show();
    }
}
