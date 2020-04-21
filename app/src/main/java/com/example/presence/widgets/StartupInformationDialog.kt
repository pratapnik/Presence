package com.example.presence.widgets

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.presence.R

class StartupInformationDialog: DialogFragment() {

    private lateinit var startupDialog: AlertDialog.Builder
    private lateinit var btnOk: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var layoutInflater: LayoutInflater? = activity?.layoutInflater
        val view = layoutInflater?.inflate(R.layout.startup_dialog_layout, null)

        startupDialog = AlertDialog.Builder(activity, R.style.CustomAlertDialog)
        startupDialog.setView(view)

        btnOk = view?.findViewById(R.id.btnStartupDialogOk)!!

        btnOk.setOnClickListener {
            dismiss()
        }

        return startupDialog.create()
    }

}