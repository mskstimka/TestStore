package com.test.teststore.app

import android.app.Application
import com.test.teststore.app.di.AppComponent
import com.test.teststore.app.di.DaggerAppComponent


class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(app = this)
    }
}