package com.minneydev.pokedex.util

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import java.util.*

interface CurrentActivityListener {
    var currentActivity: Activity?
}

/**
 * This Class and the [ActivityRetriever] were just in the videos. Not sure where to implement it
 * to be honest
 */
class DefaultCurrentActivityListener : Application.ActivityLifecycleCallbacks, CurrentActivityListener {
    override var currentActivity: Activity? = null
    lateinit var context: Context
    protected var currentActivityStack: MutableList<Activity> = ArrayList()

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        currentActivity = activity
        context = activity
        currentActivityStack.add(activity)
    }

    /**
     * Check if the activity of the given class is running
     * @param activityClass
     * @return true if running
     */
    fun isActivityRunning(activityClass: Class<out Activity>): Boolean {
        for (activity in currentActivityStack) {
            if (activity.javaClass == activityClass) {
                return true
            }
        }
        return false
    }

    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activity == currentActivity) {
            currentActivity = null
        }
        currentActivityStack.remove(activity)
    }

}