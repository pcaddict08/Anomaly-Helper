package com.phtiv.anomalyhelper.models

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import android.widget.AdapterView.OnItemClickListener


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

    fun getIconDrawable(context: Context): Drawable? {
        if (WINNER.equals("RES", true))
            return ContextCompat.getDrawable(context, R.drawable.ic_medal_blue)
        else if (WINNER.equals("ENL", true))
            return ContextCompat.getDrawable(context, R.drawable.ic_medal_green)
        else
            return ContextCompat.getDrawable(context, R.drawable.ic_medal)
    }

    fun getTitleString(context: Context, type: TITLETYPE): String {
        var title = context.getString(R.string.app_name)
        try{
            var typeSection = ""
            when (type) {
                TITLETYPE.EVENTLIST -> {
                    typeSection = " - Events"
                }
            }
            title = NAME + " " + typeSection
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return title
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
                view?.recyclerview?.layoutManager = LinearLayoutManager(view?.context)
                view?.recyclerview?.visibility = View.VISIBLE
                if (view?.recyclerview?.adapter == null)
                    view?.recyclerview?.adapter = view?.context?.let { AnomalySeriesAdapter(list, it) }
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

    enum class TITLETYPE{
        EVENTLIST
    }

}
