package com.zuehlke.training.easycv

import android.app.Application
import com.zuehlke.training.easycv.di.AppComponent
import com.zuehlke.training.easycv.di.DaggerAppComponent
import com.zuehlke.training.easycv.di.SubmoduleInjector

open class CvApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    val submoduleInjector: SubmoduleInjector by lazy {
        initializeSubmoduleInjector()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    open fun initializeSubmoduleInjector(): SubmoduleInjector {
        return SubmoduleInjector()
    }
}