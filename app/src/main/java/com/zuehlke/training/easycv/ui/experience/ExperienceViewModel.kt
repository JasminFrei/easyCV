package com.zuehlke.training.easycv.ui.experience

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExperienceViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is experience Fragment"
    }
    val text: LiveData<String> = _text
}