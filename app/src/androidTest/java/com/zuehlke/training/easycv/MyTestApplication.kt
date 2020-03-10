package com.zuehlke.training.easycv

import com.zuehlke.training.easycv.di.AppComponent
import com.zuehlke.training.easycv.di.DaggerTestAppComponent
import com.zuehlke.training.easycv.di.SubmoduleInjector
import com.zuehlke.training.easycv.di.TestSubmoduleInjector

class MyTestApplication : CvApplication() {

    override fun initializeComponent(): AppComponent {
        return DaggerTestAppComponent.factory().create(this)
    }

    override fun initializeSubmoduleInjector(): SubmoduleInjector {
        return TestSubmoduleInjector(this)
    }
}