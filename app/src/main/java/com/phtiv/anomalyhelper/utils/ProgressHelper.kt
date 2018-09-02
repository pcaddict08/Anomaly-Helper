package com.phtiv.anomalyhelper.utils

import android.view.View
import android.widget.TextView
import com.phtiv.anomalyhelper.R

class ProgressHelper {

    companion object {
        fun showProgress(message: String, progress: View?) {
            val title_dialogprogress = progress?.findViewById(R.id.title_dialogprogress) as TextView
            title_dialogprogress.setText(message)
            progress.visibility = View.VISIBLE
        }

        fun dismissProgress(progress: View?) {
            progress?.visibility = View.GONE
        }

    }
}