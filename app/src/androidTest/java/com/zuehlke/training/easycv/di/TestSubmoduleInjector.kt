package com.zuehlke.training.easycv.di

import android.app.Activity
import com.zuehlke.training.easycv.MyTestApplication
import com.zuehlke.training.easycv.ui.editprofile.BasicInformationFragment

class TestSubmoduleInjector(val application: MyTestApplication) : SubmoduleInjector() {

    override fun injectBasicInformationFragment(
        fragment: BasicInformationFragment,
        activity: Activity
    ) {
        application.appComponent.editProfileComponent().create().inject(fragment)
    }

}