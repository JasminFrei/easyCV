package com.zuehlke.training.easycv.ui.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.zuehlke.training.easycv.data.local.LocalRepository
import com.zuehlke.training.easycv.data.local.Profile
import com.zuehlke.training.easycv.di.ActivityScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@ActivityScope
class EditProfileViewModel @Inject constructor(
    localRepository: LocalRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    var name: String? = null
    var lastname: String? = null
    var birthdate: Long? = null
    var phone: String? = null
    var email: String? = null
    var description: String? = null


    val profile: LiveData<Profile?> =
        liveData(context = viewModelScope.coroutineContext + dispatcher) {
            emitSource(
                localRepository.getProfile()
            )
        }
}