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
import android.widget.RelativeLayout
import android.widget.TextView
import com.phtiv.anomalyhelper.fancyutils.PresenterUtils
import android.widget.LinearLayout


class AnomalySeries() : Parcelable {
    var ID: Int = 0
    var NAME: String = ""
    var ENL_SCORE: String = ""
    var RES_SCORE: String = ""
    var WINNER: String = ""
    var DATES: String = ""
    var ICON_URL: String = ""

    constructor(parcel: Parcel) : this() {
        ID = parcel.readInt()
        NAME = parcel.readString()
        ENL_SCORE = parcel.readString()
        RES_SCORE = parcel.readString()
        WINNER = parcel.readString()
        DATES = parcel.readString()
        ICON_URL = parcel.readString()
    }


    fun getIconDrawable(context: Context): Drawable? {
        if (WINNER.equals("RES", true))
            return ContextCompat.getDrawable(context, R.drawable.ic_medal_blue)
        else if (WINNER.equals("ENL", true))
            return ContextCompat.getDrawable(context, R.drawable.ic_medal_green)
        else
            return ContextCompat.getDrawable(context, R.drawable.ic_medal)
    }

    fun getTitleString(context: Context, type: TITLETYPE, isSimple: Boolean): String {
        var title = context.getString(R.string.app_name)
        try {
            if (!isSimple) {
                var typeSection = ""
                when (type) {
                    TITLETYPE.EVENTLIST -> {
                        typeSection = " - Events"
                    }
                }
                title = NAME + " " + typeSection
            } else
                when (type) {
                    TITLETYPE.EVENTLIST -> {
                        title = "Event Details"
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return title
    }

    enum class TITLETYPE {
        EVENTLIST
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ID)
        parcel.writeString(NAME)
        parcel.writeString(ENL_SCORE)
        parcel.writeString(RES_SCORE)
        parcel.writeString(WINNER)
        parcel.writeString(DATES)
        parcel.writeString(ICON_URL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnomalySeries> {
        override fun createFromParcel(parcel: Parcel): AnomalySeries {
            return AnomalySeries(parcel)
        }

        override fun newArray(size: Int): Array<AnomalySeries?> {
            return arrayOfNulls(size)
        }

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

        fun formatScoreBar(enlLayout: RelativeLayout?, enlScore: TextView?, resLayout: RelativeLayout?, resScore: TextView?, enlScoreValue: String, resScoreValue: String, winner: String) {
            var resPerc = 1.0f
            var enlPerc = 1.0f
            if (enlScoreValue.toFloatOrNull() != null && resScoreValue.toFloatOrNull() != null) {
                val enlFloat = enlScoreValue.toFloat()
                val resFloat = resScoreValue.toFloat()
                val totalFloat = enlFloat + resFloat
                enlPerc = enlFloat / totalFloat
                resPerc = resFloat / totalFloat
            }

            resLayout?.layoutParams = LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT, enlPerc);
            enlLayout?.layoutParams = LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT, resPerc);
            PresenterUtils.setText(enlScore, enlScoreValue)
            PresenterUtils.setText(resScore, resScoreValue)
            if (winner.equals("ENL", true)) {
                PresenterUtils.setBackgroundColor(enlLayout, R.color.barenl)
                PresenterUtils.setBackgroundColor(resLayout, R.color.barfore)
                PresenterUtils.setTextColor(resScore, R.color.barres)
                PresenterUtils.setTextColor(enlScore, R.color.white)
            } else if (winner.equals("RES", true)) {
                PresenterUtils.setBackgroundColor(enlLayout, R.color.barfore)
                PresenterUtils.setBackgroundColor(resLayout, R.color.barres)
                PresenterUtils.setTextColor(enlScore, R.color.barenl)
                PresenterUtils.setTextColor(resScore, R.color.white)
            } else {
                PresenterUtils.setBackgroundColor(enlLayout, R.color.barfore)
                PresenterUtils.setBackgroundColor(resLayout, R.color.barfore)
                PresenterUtils.setTextColor(enlScore, R.color.barenl)
                PresenterUtils.setTextColor(resScore, R.color.barres)
            }
        }

        fun formatScoreBar(series: AnomalySeries, enlLayout: RelativeLayout?, enlScore: TextView?, resLayout: RelativeLayout?, resScore: TextView?) {
            formatScoreBar(enlLayout, enlScore, resLayout, resScore, series.ENL_SCORE, series.RES_SCORE, series.WINNER)
        }

        fun formatScoreBar(event: AnomalyEvent, enlLayout: RelativeLayout?, enlScore: TextView?, resLayout: RelativeLayout?, resScore: TextView?) {
            formatScoreBar(enlLayout, enlScore, resLayout, resScore, event.ENL_SCORE, event.RES_SCORE, event.WINNER)
        }

        const val KEY_SERIESLIST = "KEY_SERIESLIST"

        enum class Type {
            PAST,
            UPCOMING
        }
    }

}
