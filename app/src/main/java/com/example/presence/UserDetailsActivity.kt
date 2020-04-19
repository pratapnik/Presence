package com.example.presence

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class UserDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        supportActionBar?.hide()

    }
}
