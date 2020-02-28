package com.zuehlke.training.easycv

import com.zuehlke.training.easycv.di.AppComponent
import com.zuehlke.training.easycv.di.DaggerTestAppComponent

class MyTestApplication : CvApplication() {

    override fun initializeComponent(): AppComponent {
        return DaggerTestAppComponent.factory().create(this)
    }
}