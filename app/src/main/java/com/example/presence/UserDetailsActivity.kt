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

        etAttendancePercent.addTextChangedListener(object : TextWatcher {
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
                otfAttendancePercent.isHelperTextEnabled = s.isEmpty()
            }
        })

        btnAddSubjects.setOnClickListener {
            if (validateInputTexts()) {
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

    private fun validateInputTexts(): Boolean {
        var isValid = true
        if (etUserName.text.toString().isEmpty()) {
            otfUserName.helperText = resources.getString(R.string.label_user_name_helper_text)
            otfUserName.error = ""
            otfUserName.setErrorIconDrawable(R.drawable.ic_error_outline)
            isValid = false
        }
        if (etInstitutionName.text.toString().isEmpty()) {
            otfInstitutionName.helperText =
                resources.getString(R.string.label_institution_name_helper_text)
            otfInstitutionName.error = ""
            otfInstitutionName.setErrorIconDrawable(R.drawable.ic_error_outline)
            isValid = false
        }
        if (etClass.text.toString().isEmpty()) {
            otfClass.helperText = resources.getString(R.string.label_class_value_helper_text)
            otfClass.error = ""
            otfClass.setErrorIconDrawable(R.drawable.ic_error_outline)
            isValid = false
        }
        if (etAttendancePercent.text.toString().isEmpty()) {
            otfAttendancePercent.helperText =
                resources.getString(R.string.label_overall_attendance_percent_hint)
            otfAttendancePercent.error = ""
            otfAttendancePercent.setErrorIconDrawable(R.drawable.ic_error_outline)
            isValid = false
        }

        return isValid
    }
}
