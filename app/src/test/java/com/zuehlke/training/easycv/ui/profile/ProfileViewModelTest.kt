package com.zuehlke.training.easycv.ui.profile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zuehlke.training.easycv.getOrAwaitValue
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProfileViewModelTest {

    private lateinit var profileViewModel: ProfileViewModel

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        profileViewModel = ProfileViewModel()
    }

    @Test
    fun getText() {
        assertThat(
            profileViewModel.text.getOrAwaitValue(),
            CoreMatchers.containsString("profile")
        )
    }
}