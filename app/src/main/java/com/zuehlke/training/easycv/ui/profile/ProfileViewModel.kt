package com.zuehlke.training.easycv.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zuehlke.training.easycv.data.local.LocalRepository
import com.zuehlke.training.easycv.data.local.Profile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    localRepository: LocalRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }

    val text: LiveData<String> = _text
    val profile: LiveData<Profile?> =
        liveData(dispatcher) { emitSource(localRepository.getProfile()) }


}