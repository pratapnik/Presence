package com.example.presence

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.presence.ui.UserInterfaceHelper
import com.example.presence.ui.UserInterfaceService
import com.example.presence.widgets.StartupInformationDialog
import kotlinx.android.synthetic.main.activity_user_details.*


class UserDetailsActivity : AppCompatActivity() {

    private lateinit var startupDialog: StartupInformationDialog
    lateinit var userName: String
    lateinit var institutionName: String
    lateinit var classValue: String
    lateinit var userInterfaceService: UserInterfaceService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        userInterfaceService = UserInterfaceHelper()

        openStartupDialog()

        etUserName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                otfUserName.isHelperTextEnabled = s.isEmpty()
            }
        })

        etInstitutionName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                otfInstitutionName.isHelperTextEnabled = s.isEmpty()
            }
        })

        etClass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                // TODO Auto-generated method stub
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                otfClass.isHelperTextEnabled = s.isEmpty()
            }
        })

        btnAddSubjects.setOnClickListener {

            getValuesFromViews()

            var goToAddSubjects = true

            if (isTextFieldEmpty(userName)) {
                otfUserName.helperText = "Name cannot be empty"
                otfUserName.error = ""
                otfUserName.setErrorIconDrawable(R.drawable.ic_error_outline)
                goToAddSubjects = false
            }
            if (isTextFieldEmpty(institutionName)) {
                otfInstitutionName.helperText = "Institution name cannot be empty"
                otfInstitutionName.error = ""
                otfInstitutionName.setErrorIconDrawable(R.drawable.ic_error_outline)
                goToAddSubjects = false
            }
            if (isTextFieldEmpty(classValue)) {
                otfClass.helperText = "Class/Semester cannot be empty"
                otfClass.error = ""
                otfClass.setErrorIconDrawable(R.drawable.ic_error_outline)
                goToAddSubjects = false
            }

            if (goToAddSubjects) {
                val addSubjectsActivity = Intent(this, AddSubjectsActivity::class.java)
                startActivity(addSubjectsActivity)
            }
        }
    }

    private fun openStartupDialog() {
        startupDialog = StartupInformationDialog()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        startupDialog.show(
            fragmentTransaction,
            resources.getString(R.string.label_start_up_dialog_tag)
        )
    }

    private fun isTextFieldEmpty(editTextString: String): Boolean {
        return editTextString.isEmpty()
    }

    private fun getValuesFromViews() {
        userName = etUserName.text.toString()
        institutionName = etInstitutionName.text.toString()
        classValue = etClass.text.toString()
    }
}
