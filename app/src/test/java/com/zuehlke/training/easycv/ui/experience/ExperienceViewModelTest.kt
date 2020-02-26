package com.zuehlke.training.easycv.ui.experience

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zuehlke.training.easycv.getOrAwaitValue
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExperienceViewModelTest {

    private lateinit var experienceViewModel: ExperienceViewModel

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        experienceViewModel = ExperienceViewModel()
    }

    @Test
    fun getText() {
        assertThat(experienceViewModel.text.getOrAwaitValue(), containsString("experience"))
    }
}