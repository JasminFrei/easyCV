package com.zuehlke.training.easycv.ui.education

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zuehlke.training.easycv.getOrAwaitValue
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EducationViewModelTest {

    private lateinit var educationViewModel: EducationViewModel

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        educationViewModel = EducationViewModel()
    }

    @Test
    fun getText() {
        assertThat(educationViewModel.text.getOrAwaitValue(), containsString("education"))
    }
}