package com.zuehlke.training.easycv.ui.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zuehlke.training.easycv.data.local.LocalRepository
import com.zuehlke.training.easycv.di.ActivityScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class EditProfileViewModel @Inject constructor(
    val localRepository: LocalRepository
) : ViewModel() {
    var id: Int? = null
    var name: String? = null
    var lastname: String? = null
    var birthdate: Long? = null
    var phone: String? = null
    var email: String? = null
    var description: String? = null
    var street: String? = null
    var zip: String? = null
    var city: String? = null
    var country: String? = null

    private val _profileLoaded = MutableLiveData<Boolean>()
    val profileLoaded: LiveData<Boolean> = _profileLoaded

    fun loadProfile() = viewModelScope.launch {
        val profile = localRepository.getProfilePlain()
        if (profile != null) {
            id = profile.profile_id
            name = profile.name
            lastname = profile.lastname
            birthdate = profile.birthdate
            phone = profile.phone
            email = profile.email
            description = profile.description
            street = profile.street
            zip = profile.zip
            city = profile.location
            country = profile.country
            _profileLoaded.value = true
        } else {
            _profileLoaded.value = false
        }
    }
}