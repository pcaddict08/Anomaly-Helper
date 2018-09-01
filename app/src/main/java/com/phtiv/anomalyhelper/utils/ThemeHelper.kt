package com.phtiv.anomalyhelper.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.ActionBar
import android.view.LayoutInflater
import android.view.Window
import android.widget.RelativeLayout
import android.widget.TextView
import com.phtiv.anomalyhelper.R

object ThemeHelper {

    fun initActionBar(ab: ActionBar?, title: String) {
        if (ab != null) {
            setTitle(title, ab)
            ab.title = title
            ab.setLogo(R.mipmap.ic_launcher)
            ab.setDisplayUseLogoEnabled(true)
            ab.setDisplayShowHomeEnabled(true)
            if (!title.equals(ab.themedContext.getString(R.string.app_name), ignoreCase = true))
                ab.setDisplayHomeAsUpEnabled(true)
        }
    }

    fun setTitle(title: String, actionBar: ActionBar?) {
        try {
            if (actionBar != null)
                actionBar.title = title
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun getProgressDialog(context: Context, title: String): Dialog? {
        try {
            val alertDialog = Dialog(context)
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val dialog_progress = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null) as RelativeLayout
            val title_dialogprogress = dialog_progress.findViewById(R.id.title_dialogprogress) as TextView
            title_dialogprogress.setText(title)
            alertDialog.setContentView(dialog_progress)
            val window = alertDialog.window
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            return alertDialog
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }
}
