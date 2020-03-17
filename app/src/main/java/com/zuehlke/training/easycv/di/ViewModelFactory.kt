package com.zuehlke.training.easycv.di

/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zuehlke.training.easycv.data.external.LatexOnlineDataSource
import com.zuehlke.training.easycv.data.local.LocalRepository
import com.zuehlke.training.easycv.ui.editprofile.EditProfileViewModel
import com.zuehlke.training.easycv.ui.education.EducationViewModel
import com.zuehlke.training.easycv.ui.experience.ExperienceViewModel
import com.zuehlke.training.easycv.ui.export.ExportViewModel
import com.zuehlke.training.easycv.ui.profile.ProfileViewModel
import com.zuehlke.training.easycv.ui.skills.SkillsViewModel
import com.zuehlke.training.easycv.util.CvMaker
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

/**
 * ViewModelFactory which uses Dagger to create the instances.
 */
class MyViewModelFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("Unknown model class: $modelClass")
        }
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}

@Module
open class ViewModelModule {
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
    fun provideExportViewModel(
        cvMaker: CvMaker,
        latexOnlineDataSource: LatexOnlineDataSource,
        localRepository: LocalRepository
    ): ViewModel {
        return ExportViewModel(cvMaker, latexOnlineDataSource, localRepository)
    }

    @Provides
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun provideProfileViewModel(localRepository: LocalRepository): ViewModel {
        return ProfileViewModel(localRepository)
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
        return EditProfileViewModel(localRepository)
    }
}

@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
