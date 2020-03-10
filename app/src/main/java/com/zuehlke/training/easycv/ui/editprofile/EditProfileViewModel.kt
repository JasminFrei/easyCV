package com.zuehlke.training.easycv.ui.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.zuehlke.training.easycv.data.local.LocalRepository
import com.zuehlke.training.easycv.data.local.Profile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(
    localRepository: LocalRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    val name: String? = null
    val lastname: String? = null
    val birthdate: Long? = null
    val phone: String? = null
    val email: String? = null
    val description: String? = null


    val profile: LiveData<Profile?> =
        liveData(context = viewModelScope.coroutineContext + dispatcher) {
            emitSource(
                localRepository.getProfile()
            )
        }
}