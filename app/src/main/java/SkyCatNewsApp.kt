package uk.ryanwong.skycatnews

import android.app.Application
import timber.log.Timber

class SkyCatNewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // TODO: If Firebase Crashlytics is available, replace with a CrashReportingTree here
            Timber.plant(Timber.DebugTree())
        }
    }
}
