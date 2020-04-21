package com.example.presence

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.presence.widgets.StartupInformationDialog
import kotlinx.android.synthetic.main.activity_user_details.*


class UserDetailsActivity : AppCompatActivity() {

    private lateinit var startupDialog: StartupInformationDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        supportActionBar?.hide()

        openStartupDialog()

        btnAddSubjects.setOnClickListener {
            val addSubjectsActivity = Intent(this, AddSubjectsActivity::class.java)
            startActivity(addSubjectsActivity)
        }
    }

    fun openStartupDialog(){
        startupDialog = StartupInformationDialog()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        startupDialog.show(fragmentTransaction,
            resources.getString(R.string.label_start_up_dialog_tag))
    }
}
