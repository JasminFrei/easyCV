package com.zuehlke.training.easycv.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zuehlke.training.easycv.data.local.LocalRepository
import com.zuehlke.training.easycv.data.local.Profile
import javax.inject.Inject

class ProfileViewModel @Inject constructor(localRepository: LocalRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }

    val text: LiveData<String> = _text
    val profile: LiveData<Profile?> = localRepository.getProfile()


}