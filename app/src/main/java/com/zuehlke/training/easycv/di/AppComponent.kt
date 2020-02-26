package com.zuehlke.training.easycv.di

import android.content.Context
import com.zuehlke.training.easycv.ui.education.EducationFragment
import com.zuehlke.training.easycv.ui.experience.ExperienceFragment
import com.zuehlke.training.easycv.ui.export.ExportFragment
import com.zuehlke.training.easycv.ui.profile.ProfileFragment
import com.zuehlke.training.easycv.ui.skills.SkillsFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(fragment: EducationFragment)
    fun inject(fragment: ExperienceFragment)
    fun inject(fragment: ExportFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: SkillsFragment)
}