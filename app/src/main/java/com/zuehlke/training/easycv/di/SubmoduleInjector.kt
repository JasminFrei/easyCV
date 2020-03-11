package com.zuehlke.training.easycv.di

import android.app.Activity
import com.zuehlke.training.easycv.ui.editprofile.AddressFragment
import com.zuehlke.training.easycv.ui.editprofile.BasicInformationFragment
import com.zuehlke.training.easycv.ui.editprofile.EditProfileActivity

/*
    SubmoduleInjector is needed to ensure testability of a fragment that injects its dependencies
    from an activity instead the context
 */
open class SubmoduleInjector {

    open fun injectBasicInformationFragment(
        fragment: BasicInformationFragment,
        activity: Activity
    ) {
        if (activity is EditProfileActivity) {
            activity.editProfileComponent.inject(fragment)
        }
    }

    open fun injectAddressFragment(fragment: AddressFragment, activity: Activity) {
        if (activity is EditProfileActivity) {
            activity.editProfileComponent.inject(fragment)
        }
    }
}