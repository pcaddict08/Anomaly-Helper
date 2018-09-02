package com.phtiv.anomalyhelper.models

import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.phtiv.anomalyhelper.R
import com.phtiv.anomalyhelper.adapters.AnomalySeriesAdapter
import com.phtiv.anomalyhelper.retrofit.RetrofitCalls
import com.phtiv.anomalyhelper.retrofit.RetrofitClientInstance
import com.phtiv.anomalyhelper.utils.AlertHelper
import com.phtiv.anomalyhelper.utils.ProgressHelper
import kotlinx.android.synthetic.main.fragment_anomaly_list_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnomalySeries() : Parcelable {
    var ID: Int = 0
    var NAME: String = ""
    var ENL_SCORE: String = ""
    var RES_SCORE: String = ""
    var WINNER: String = ""

    constructor(parcel: Parcel) : this() {
        ID = parcel.readInt()
        NAME = parcel.readString()
        ENL_SCORE = parcel.readString()
        RES_SCORE = parcel.readString()
        WINNER = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ID)
        parcel.writeString(NAME)
        parcel.writeString(ENL_SCORE)
        parcel.writeString(RES_SCORE)
        parcel.writeString(WINNER)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnomalySeries> {

        fun handleSeriesRequest(view: View?, type: Type) {
            ProgressHelper.showProgress("Getting Anomaly Series List...", view?.findViewById(R.id.progress))
            val service = RetrofitClientInstance.retrofitInstance?.create<RetrofitCalls>(RetrofitCalls::class.java)
            var call = service?.upcomingSeries()
            if (type == Type.PAST)
                call = service?.pastSeries()
            call?.enqueue(object : Callback<List<AnomalySeries>> {
                override fun onResponse(call: Call<List<AnomalySeries>>, response: Response<List<AnomalySeries>>) {
                    AnomalySeries.setupRecyclerViewAdapter(response.body(), view)
                }

                override fun onFailure(call: Call<List<AnomalySeries>>, t: Throwable) {
                    ProgressHelper.dismissProgress(view?.findViewById(R.id.progress))
                    view?.context?.let { AlertHelper.showToast(it, t.localizedMessage, true, R.drawable.ic_warning) }
                }
            })
        }

        fun setupRecyclerViewAdapter(list: List<AnomalySeries>?, view: View?) {
            ProgressHelper.dismissProgress(view?.findViewById(R.id.progress))
            if (list != null) {
                view?.upcoming_recyclerview?.layoutManager = LinearLayoutManager(view?.context)
                view?.upcoming_recyclerview?.visibility = View.VISIBLE
                if (view?.upcoming_recyclerview?.adapter == null)
                    view?.upcoming_recyclerview?.adapter = view?.context?.let { AnomalySeriesAdapter(list, it) }
            }
        }

        const val KEY_SERIESLIST = "KEY_SERIESLIST"

        enum class Type {
            PAST,
            UPCOMING
        }

        override fun createFromParcel(parcel: Parcel): AnomalySeries {
            return AnomalySeries(parcel)
        }

        override fun newArray(size: Int): Array<AnomalySeries?> {
            return arrayOfNulls(size)
        }
    }

}
