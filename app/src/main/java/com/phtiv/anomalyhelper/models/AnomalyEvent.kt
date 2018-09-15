package com.phtiv.anomalyhelper.models

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.gson.annotations.SerializedName
import com.phtiv.anomalyhelper.R
import com.phtiv.anomalyhelper.adapters.AnomalyEventsAdapter
import com.phtiv.anomalyhelper.adapters.AnomalySeriesAdapter
import com.phtiv.anomalyhelper.utils.ProgressHelper
import kotlinx.android.synthetic.main.fragment_anomaly_list_layout.view.*

class AnomalyEvent(
        @SerializedName("ID") var ID: Int = 0,
        @SerializedName("DATE") var DATE: String = "",
        @SerializedName("LOCATION") var LOCATION: AnomalyLocation = AnomalyLocation(),
        @SerializedName("ANOMALY_NAME") var ANOMALY_NAME: String = "",
        @SerializedName("ANOMALY_ID") var ANOMALY_ID: Int = 0,
        @SerializedName("RES_SCORE") var RES_SCORE: String = "",
        @SerializedName("ENL_SCORE") var ENL_SCORE: String = "",
        @SerializedName("WINNER") var WINNER: String = "",
        @SerializedName("TYPE") var TYPE: String = ""
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readParcelable(AnomalyLocation::class.java.classLoader),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    fun getIconDrawable(context: Context): Drawable? {
        if (WINNER.equals("RES", true))
            return ContextCompat.getDrawable(context, R.drawable.ic_medal_blue)
        else if (WINNER.equals("ENL", true))
            return ContextCompat.getDrawable(context, R.drawable.ic_medal_green)
        else
            return ContextCompat.getDrawable(context, R.drawable.ic_medal)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ID)
        parcel.writeString(DATE)
        parcel.writeParcelable(LOCATION, flags)
        parcel.writeString(ANOMALY_NAME)
        parcel.writeInt(ANOMALY_ID)
        parcel.writeString(RES_SCORE)
        parcel.writeString(ENL_SCORE)
        parcel.writeString(WINNER)
        parcel.writeString(TYPE)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnomalyEvent> {
        override fun createFromParcel(parcel: Parcel): AnomalyEvent {
            return AnomalyEvent(parcel)
        }

        override fun newArray(size: Int): Array<AnomalyEvent?> {
            return arrayOfNulls(size)
        }

        fun setupRecyclerViewAdapter(list: List<AnomalyEvent>?, view: View?) {
            ProgressHelper.dismissProgress(view?.findViewById(R.id.progress))
            if (list != null) {
                view?.recyclerview?.layoutManager = LinearLayoutManager(view?.context)
                view?.recyclerview?.visibility = View.VISIBLE
                if (view?.recyclerview?.adapter == null)
                    view?.recyclerview?.adapter = view?.context?.let { AnomalyEventsAdapter(list, it) }
            }
        }
    }
}