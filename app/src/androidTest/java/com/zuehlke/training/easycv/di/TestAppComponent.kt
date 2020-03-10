package com.zuehlke.training.easycv.di

import android.content.Context
import com.zuehlke.training.easycv.ui.editprofile.BasicInformationFragmentTest
import com.zuehlke.training.easycv.ui.profile.ProfileFragmentTest
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestApplicationModule::class, TestViewModelModule::class])
interface TestAppComponent : AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(test: ProfileFragmentTest)
    fun inject(test: BasicInformationFragmentTest)
}