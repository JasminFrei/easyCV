package com.zuehlke.training.easycv

import android.app.Application
import com.zuehlke.training.easycv.di.AppComponent
import com.zuehlke.training.easycv.di.DaggerAppComponent

open class CvApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}