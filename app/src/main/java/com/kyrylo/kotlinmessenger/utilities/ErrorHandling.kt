package com.kyrylo.kotlinmessenger.utilities

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.kyrylo.kotlinmessenger.R

fun Any?.showErrorMessage(context: Context,message : String){
    val dialog  = AlertDialog.Builder(context)

    dialog.setTitle("Error").setMessage(message).setPositiveButton(R.string.abc_action_mode_done) { _: DialogInterface, i: Int ->
    }.show()

}