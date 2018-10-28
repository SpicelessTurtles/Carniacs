package com.kyrylo.kotlinmessenger.utilities

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

/**
 * Created by jyotidubey on 11/01/18.
 */
object CommonUtil {

    fun showLoadingDialog(context: Context?): ProgressDialog {
        val progressDialog = ProgressDialog(context)

        progressDialog.let {
            it.show()
            it.isIndeterminate = true
            it.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            return it
        }

    }

}