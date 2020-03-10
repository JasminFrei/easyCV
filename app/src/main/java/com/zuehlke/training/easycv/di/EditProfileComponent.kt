package com.zuehlke.training.easycv.di

import com.zuehlke.training.easycv.ui.editprofile.AddressFragment
import com.zuehlke.training.easycv.ui.editprofile.BasicInformationFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface EditProfileComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): EditProfileComponent
    }

    fun inject(fragment: AddressFragment)
    fun inject(fragment: BasicInformationFragment)
}