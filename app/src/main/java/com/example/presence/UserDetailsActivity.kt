package com.example.presence

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_user_details.*


class UserDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        supportActionBar?.hide()

        btnAddSubjects.setOnClickListener {
            val addSubjectsActivity = Intent(this, AddSubjectsActivity::class.java)
            startActivity(addSubjectsActivity)
        }
    }
}
