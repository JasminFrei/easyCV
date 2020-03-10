package com.zuehlke.training.easycv.ui.editProfile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.zuehlke.training.easycv.data.local.LocalRepository
import com.zuehlke.training.easycv.data.local.Profile
import com.zuehlke.training.easycv.getOrAwaitValue
import com.zuehlke.training.easycv.ui.editprofile.EditProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EditProfileViewModelTest {

    private lateinit var profileViewModel: EditProfileViewModel
    private val liveData = MutableLiveData<Profile?>()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val repositoryMock = mock<LocalRepository> {
            on { getProfile() } doReturn liveData
        }
        profileViewModel = EditProfileViewModel(repositoryMock, Dispatchers.Unconfined)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadProfile_noData() {
        liveData.postValue(null)
        assertThat(profileViewModel.profile.getOrAwaitValue(), `is`(nullValue()))
    }

    @Test
    fun loadProfile_withData() {
        liveData.postValue(Profile(42, "", "", 0L, "", "", "", "", "", "", null, null))
        val profile = profileViewModel.profile.getOrAwaitValue()
        assertThat(profile, `is`(notNullValue()))
        assertThat(profile!!.profile_id, `is`(42))
    }
}