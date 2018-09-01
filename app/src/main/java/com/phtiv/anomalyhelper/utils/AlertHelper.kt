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
                (context as Activity).runOnUiThread {
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
}