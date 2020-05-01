package com.example.presence.widgets

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.presence.R
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class AddSubjectDialog(private val saveDetailsListener: SaveDetailsListener) : DialogFragment() {

    private lateinit var addSubjectDialog: AlertDialog.Builder

    lateinit var btnCancel: Button
    lateinit var btnSave: Button

    lateinit var etSubjectName: EditText
    lateinit var otfSubjectName: TextInputLayout
    lateinit var otfAttendance: TextInputLayout
    lateinit var etAttendance: EditText

    lateinit var chipMonday: Chip
    lateinit var chipTuesday: Chip
    lateinit var chipWednesday: Chip
    lateinit var chipThursday: Chip
    lateinit var chipFriday: Chip
    lateinit var chipSaturday: Chip
    var daysOfClass: String = ""
    var subjectName: String = ""

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var layoutInflater: LayoutInflater? = activity?.layoutInflater
        val view = layoutInflater?.inflate(R.layout.add_subjects_dialog_layout, null)

        btnCancel = view?.findViewById(R.id.btnCancel)!!
        btnSave = view.findViewById(R.id.btnSave)!!
        etSubjectName = view.findViewById(R.id.etSubjectName)
        otfSubjectName = view.findViewById(R.id.otfSubjectName)
        otfAttendance = view.findViewById(R.id.otfAttendance)
        etAttendance = view.findViewById(R.id.etAttendance)

        chipMonday = view.findViewById(R.id.chipMonday)
        chipTuesday = view.findViewById(R.id.chipTuesday)
        chipWednesday = view.findViewById(R.id.chipWednesday)
        chipThursday = view.findViewById(R.id.chipThursday)
        chipFriday = view.findViewById(R.id.chipFriday)
        chipSaturday = view.findViewById(R.id.chipSaturday)

        addSubjectDialog = AlertDialog.Builder(activity, R.style.CustomAlertDialog)
        addSubjectDialog.setView(view)

        btnCancel.setOnClickListener {
            dismiss()
        }

        btnSave.setOnClickListener {
            if(checkInputValues(view))
                dismiss()
        }

        etSubjectName.addTextChangedListener(object : TextWatcher {
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
                otfSubjectName.isHelperTextEnabled = s.isEmpty()
            }
        })

        etAttendance.addTextChangedListener(object : TextWatcher {
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
                otfAttendance.isHelperTextEnabled = s.isEmpty()
            }
        })


        return addSubjectDialog.create()
    }

    private fun checkInputValues(view: View): Boolean{
        val isSubjectEmpty = etSubjectName.text.toString().isEmpty()
        val isAttendanceEmpty = etAttendance.text.toString().isEmpty()
        if (isSubjectEmpty && isAttendanceEmpty) {
            otfSubjectName.helperText = "Subject Name cannot be empty"
            otfSubjectName.error = ""
            otfSubjectName.setErrorIconDrawable(R.drawable.ic_error_outline)

            otfAttendance.helperText = "Please give a minimum percentage"
            otfAttendance.error = ""
            otfAttendance.setErrorIconDrawable(R.drawable.ic_error_outline)
        }
        else if(isSubjectEmpty){
            otfSubjectName.helperText = "Subject Name cannot be empty"
            otfSubjectName.error = ""
            otfSubjectName.setErrorIconDrawable(R.drawable.ic_error_outline)
        }
        else if (!chipMonday.isChecked && !chipTuesday.isChecked && !chipWednesday.isChecked
            && !chipThursday.isChecked && !chipFriday.isChecked && !chipSaturday.isChecked
        ) {
            val snackbar =
                Snackbar.make(view, "You should select atleast one day", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
        else if(isAttendanceEmpty){
            otfAttendance.helperText = "Please give a minimum percentage"
            otfAttendance.error = ""
            otfAttendance.setErrorIconDrawable(R.drawable.ic_error_outline)
        }
        else {
            subjectName = etSubjectName.text.toString()
            if (chipMonday.isChecked)
                daysOfClass = daysOfClass.plus(chipMonday.text.toString()).plus(",")
            if (chipTuesday.isChecked)
                daysOfClass = daysOfClass.plus(chipTuesday.text.toString()).plus(",")
            if (chipWednesday.isChecked)
                daysOfClass = daysOfClass.plus(chipWednesday.text.toString()).plus(",")
            if (chipThursday.isChecked)
                daysOfClass = daysOfClass.plus(chipThursday.text.toString()).plus(",")
            if (chipFriday.isChecked)
                daysOfClass = daysOfClass.plus(chipFriday.text.toString()).plus(",")
            if (chipSaturday.isChecked)
                daysOfClass = daysOfClass.plus(chipSaturday.text.toString()).plus(",")
            saveDetailsListener.onSaveDetailsListener(subjectName, daysOfClass, etAttendance.text.toString().toInt())
            return true
        }
        return false
    }

    interface SaveDetailsListener {
        fun onSaveDetailsListener(subjectName: String, daysOfClass: String, subjectPercent: Int)
    }
}