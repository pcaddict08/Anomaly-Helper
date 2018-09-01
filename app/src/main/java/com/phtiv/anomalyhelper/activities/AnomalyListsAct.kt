package com.phtiv.anomalyhelper.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.Menu
import android.view.MenuItem
import com.phtiv.anomalyhelper.AnomalyAct
import com.phtiv.anomalyhelper.R
import com.phtiv.anomalyhelper.fragments.PastAnomalyFragmentFragment
import com.phtiv.anomalyhelper.fragments.PlaceholderFragment
import com.phtiv.anomalyhelper.fragments.UpcomingAnomalyFragment
import com.phtiv.anomalyhelper.models.AnomalyHistory
import com.phtiv.anomalyhelper.models.AnomalySeries
import com.phtiv.anomalyhelper.retrofit.RetrofitCalls
import com.phtiv.anomalyhelper.retrofit.RetrofitClientInstance
import com.phtiv.anomalyhelper.utils.AlertHelper
import kotlinx.android.synthetic.main.activity_anomaly_lists.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AnomalyListsAct : AnomalyAct() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    var service: RetrofitCalls? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anomaly_lists)
        toolbar.logo = getDrawable(R.mipmap.ic_launcher)
        setSupportActionBar(toolbar)

        showProgress("Getting Anomaly Series List...")
        service = RetrofitClientInstance.retrofitInstance?.create<RetrofitCalls>(RetrofitCalls::class.java)
        val call = service?.allSeries()
        call?.enqueue(object : Callback<List<AnomalySeries>> {
            override fun onResponse(call: Call<List<AnomalySeries>>, response: Response<List<AnomalySeries>>) {
                dismissProgress()
                gotSeriesList(response.body())
            }

            override fun onFailure(call: Call<List<AnomalySeries>>, t: Throwable) {
                dismissProgress()
                AlertHelper.showToast(this@AnomalyListsAct, t.localizedMessage, true, R.drawable.ic_warning)
            }
        })
        finishedLoadingEverything()
    }

    private fun gotSeriesList(seriesList: List<AnomalySeries>?) {
        if (seriesList != null) {
            //TODO greendao anomalyseries
            //AnomalySeriesDB.getInstance().insertOrReplaceAll(seriesList)
            AnomalySeries.insertOrReplace(seriesList)
            showProgress("Getting Anomaly History List...")
            val call = service?.allHistory()
            call?.enqueue(object : Callback<List<AnomalyHistory>> {
                override fun onResponse(call: Call<List<AnomalyHistory>>, response: Response<List<AnomalyHistory>>) {
                    dismissProgress()
                    gotHistoryList(response.body())
                }

                override fun onFailure(call: Call<List<AnomalyHistory>>, t: Throwable) {
                    dismissProgress()
                    AlertHelper.showToast(this@AnomalyListsAct, t.localizedMessage, true, R.drawable.ic_warning)
                }
            })
        } else
            AlertHelper.showToast(this@AnomalyListsAct, "Something went wrong...", true, R.drawable.ic_warning)
    }

    fun gotHistoryList(historyList: List<AnomalyHistory>?) {
        if (historyList != null) {
            AnomalyHistory.insertOrReplace(historyList)
            finishedLoadingEverything()
        } else
            AlertHelper.showToast(this@AnomalyListsAct, "Something went wrong...", true, R.drawable.ic_warning)
    }

    fun finishedLoadingEverything() {
        val allHistory = AnomalyHistory.getAll()
        val allSeries = AnomalySeries.getAll()
        val pastSeries = AnomalySeries.getPast()
        val futureSeries = AnomalySeries.getUpcoming()
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //TODO ADD REFRESH THING TO THIS
        menuInflater.inflate(R.menu.menu_anomaly_lists, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            if (position == 0) {
                return UpcomingAnomalyFragment.newInstance()
            } else if (position == 1) {
                return PastAnomalyFragmentFragment.newInstance()
            } else
                return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            return 2
        }
    }
}
