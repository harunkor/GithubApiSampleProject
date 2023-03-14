package com.harunkor.githubapisampleproject.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.harunkor.githubapisampleproject.R

class ErrorDialog(private val message:String): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
           AlertDialog.Builder(it)
            .setMessage(message)
                .setPositiveButton(getString(R.string.okay)) { dialog, _ ->
                    dialog.dismiss()
                }
            .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}