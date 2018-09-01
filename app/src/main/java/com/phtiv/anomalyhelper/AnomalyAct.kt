package com.phtiv.anomalyhelper

import android.annotation.SuppressLint
import android.app.Dialog
import android.support.v7.app.AlertDialog
import com.phtiv.anomalyhelper.utils.ThemeHelper
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem


@SuppressLint("Registered")
open class AnomalyAct : AppCompatActivity() {

    var alertdialog: AlertDialog? = null

    var progressDialog: Dialog? = null

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun showProgress(string: String) {
        runOnUiThread {
            progressDialog = ThemeHelper.getProgressDialog(this@AnomalyAct, string) //new ProgressDialog(this, R.style.AboutDialog);
            if (progressDialog != null) {
                progressDialog!!.show()
                progressDialog!!.setCancelable(false)
            }
        }
    }

    fun dismissProgress() {
        if (progressDialog != null)
            runOnUiThread { progressDialog!!.dismiss() }
    }
}