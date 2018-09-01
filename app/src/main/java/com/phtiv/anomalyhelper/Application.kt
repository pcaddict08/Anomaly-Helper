package com.phtiv.anomalyhelper

import android.app.Activity
import android.content.ComponentCallbacks2
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle


class Application : android.app.Application() {

    var currentActivity: Activity? = null

    override fun onCreate() {
        super.onCreate()
        val handler = MyActivityLifecycleCallbacks(this)
        registerActivityLifecycleCallbacks(handler)
        registerComponentCallbacks(handler)
    }

    fun clearLoading() {
        val currentAct = currentActivity
        if (currentAct != null) {
            if (currentAct is AnomalyAct)
                currentAct.dismissProgress()
        }
    }

    private class MyActivityLifecycleCallbacks internal constructor(internal var application: Application) : android.app.Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

        override fun onTrimMemory(i: Int) {
            if (i == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
                //application.cancelGPS();
                isInBackground = true
            }
        }

        override fun onActivityCreated(activity: Activity, bundle: Bundle) {
        }

        override fun onActivityDestroyed(activity: Activity) {
        }

        override fun onActivityPaused(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {
            application.currentActivity = activity
            if (isInBackground) {
                isInBackground = false
            }
        }

        override fun onActivitySaveInstanceState(activity: Activity,
                                                 outState: Bundle) {
        }

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivityStopped(activity: Activity) {
        }

        override fun onConfigurationChanged(configuration: Configuration) {}

        override fun onLowMemory() {

        }

        companion object {
            private var isInBackground = false
        }
    }

    companion object {

        val screenHeight: Int
            get() = Resources.getSystem().getDisplayMetrics().heightPixels

    }

}