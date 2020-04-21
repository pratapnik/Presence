package com.example.presence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddSubjectsActivity extends AppCompatActivity {
    private LinearLayout llSubjectList;
    private Button btnAddSubject;
    private EditText etSubjectTitle;
    private LayoutInflater inflater;
    private List<String> subjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        llSubjectList = findViewById(R.id.llSubjectList);
        btnAddSubject = findViewById(R.id.btnAddSubject);
        etSubjectTitle = findViewById(R.id.etSubjectTitle);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        subjectList = new ArrayList<>();

        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectTitle = etSubjectTitle.getText().toString().trim();
                if (!TextUtils.isEmpty(subjectTitle)) {
                    addSubject(subjectTitle);
                }
                else {
                    showToast("Please enter a subject");
                }
            }
        });
    }

    private void addSubject(String subjectTitle) {
        if (!subjectList.contains(subjectTitle)) {
            final View itemView = inflater.inflate(R.layout.add_subject_item, null);
            TextView tvTitle = itemView.findViewById(R.id.tvSubjectTitle);
            tvTitle.setText(subjectTitle);
            llSubjectList.addView(itemView);
            subjectList.add(subjectTitle);
        }
        else {
            showToast("Subject already exists");
        }
    }

    public void deleteSubjectItem(View view) {
        View rootView = (View) view.getParent();
        TextView tvTitle = rootView.findViewById(R.id.tvSubjectTitle);
        subjectList.remove(tvTitle.getText().toString());
        llSubjectList.removeView(rootView);
    }

    private void showToast(String msg) {
        Toast.makeText(AddSubjectsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
