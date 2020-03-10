package com.zuehlke.training.easycv.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zuehlke.training.easycv.data.local.LocalRepository
import com.zuehlke.training.easycv.ui.editprofile.EditProfileViewModel
import com.zuehlke.training.easycv.ui.education.EducationViewModel
import com.zuehlke.training.easycv.ui.experience.ExperienceViewModel
import com.zuehlke.training.easycv.ui.export.ExportViewModel
import com.zuehlke.training.easycv.ui.profile.ProfileViewModel
import com.zuehlke.training.easycv.ui.skills.SkillsViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.Dispatchers
import javax.inject.Provider

@Module
class TestViewModelModule {
    @Provides
    fun getViewModelFactory(map: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
        return MyViewModelFactory(map)
    }

    @Provides
    @IntoMap
    @ViewModelKey(EducationViewModel::class)
    fun provideEducationViewModel(): ViewModel {
        return EducationViewModel()
    }

    @Provides
    @IntoMap
    @ViewModelKey(ExperienceViewModel::class)
    fun provideExperienceViewModel(): ViewModel {
        return ExperienceViewModel()
    }

    @Provides
    @IntoMap
    @ViewModelKey(ExportViewModel::class)
    fun provideExportViewModel(): ViewModel {
        return ExportViewModel()
    }

    @Provides
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun provideProfileViewModel(localRepository: LocalRepository): ViewModel {
        return ProfileViewModel(localRepository, Dispatchers.Unconfined)
    }

    @Provides
    @IntoMap
    @ViewModelKey(SkillsViewModel::class)
    fun provideSkillsViewModel(): ViewModel {
        return SkillsViewModel()
    }

    @Provides
    @IntoMap
    @ViewModelKey(EditProfileViewModel::class)
    fun provideEditProfileViewModel(localRepository: LocalRepository): ViewModel {
        return EditProfileViewModel(localRepository, Dispatchers.Unconfined)
    }

}