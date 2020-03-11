package com.zuehlke.training.easycv.ui.editprofile

import android.util.Log
import androidx.lifecycle.*
import com.zuehlke.training.easycv.data.local.LocalRepository
import com.zuehlke.training.easycv.data.local.Profile
import com.zuehlke.training.easycv.di.ActivityScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class EditProfileViewModel @Inject constructor(
    val localRepository: LocalRepository,
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
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

    fun saveProfile() = liveData(context = viewModelScope.coroutineContext + dispatcher) {
        //Todo: validateData
        val profile = Profile(
            id,
            name!!,
            lastname!!,
            birthdate!!,
            street!!,
            zip!!,
            city!!,
            country,
            phone!!,
            email!!,
            description,
            null
        )
        try {
            localRepository.saveProfile(profile)
            emit(true)
        } catch (e: Exception) {
            Log.e("EditProfileViewModel", "blub", e)
            emit(false)
        }

    }
}