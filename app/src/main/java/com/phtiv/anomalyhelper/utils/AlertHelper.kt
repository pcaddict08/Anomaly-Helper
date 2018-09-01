package com.phtiv.anomalyhelper.utils

import android.app.Activity
import android.content.Context
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.muddzdev.styleabletoastlibrary.StyleableToast
import com.phtiv.anomalyhelper.R


object AlertHelper {
    fun showToast(context: Context, string: String, warning: Boolean, icon: Int) {
        try {
            if (context is Activity)
                context.runOnUiThread {
                    try {
                        if (warning)
                            StyleableToast.Builder(context)
                                    .text(string)
                                    .textColor(ContextCompat.getColor(context, R.color.white))
                                    .backgroundColor(ContextCompat.getColor(context, R.color.mediumred))
                                    .length(Toast.LENGTH_LONG).iconEnd(icon)
                                    .show()
                        else
                            Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showThrowableAlert(context: Context, t: Throwable) {
        try {
            if (context is Activity)
                context.runOnUiThread {
                    try {
                        AlertHelper.showToast(context, t.localizedMessage, true, R.drawable.ic_warning)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showGenericError(context: Context) {
        try {
            if (context is Activity)
                context.runOnUiThread {
                    try {
                        AlertHelper.showToast(context, context.getString(R.string.generic_error), true, R.drawable.ic_warning)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}