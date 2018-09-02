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
import kotlinx.android.synthetic.main.activity_anomaly_lists.*


class AnomalyListsAct : AnomalyAct() {

    private lateinit var mSectionsPagerAdapter: Any

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anomaly_lists)
        toolbar.logo = getDrawable(R.mipmap.ic_launcher)
        setSupportActionBar(toolbar)
        setupAdapter()
    }

    fun setupAdapter() {
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter as SectionsPagerAdapter
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
