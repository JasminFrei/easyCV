package com.zuehlke.training.easycv.ui.export

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zuehlke.training.easycv.data.external.LatexOnlineDataSource
import com.zuehlke.training.easycv.data.local.LocalRepository
import com.zuehlke.training.easycv.data.local.Profile
import com.zuehlke.training.easycv.util.CvMaker
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExportViewModel @Inject constructor(
    private val cvMaker: CvMaker,
    private val latexOnlineDataSource: LatexOnlineDataSource,
    private val localRepository: LocalRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is export Fragment"
    }
    private val _profile = MutableLiveData<Profile>()

    val text: LiveData<String> = _text

    init {
        viewModelScope.launch(dispatcher) {
            val profile = localRepository.getProfilePlain()
            _profile.postValue(profile)
        }
    }

    fun exportCv(context: Context) {
        val profile = _profile.value
        if (profile != null) {
            val tarball = cvMaker.makeCv(profile)
            viewModelScope.launch(dispatcher) {
                latexOnlineDataSource.createCvPdf(tarball, context)
            }
        }
    }
}