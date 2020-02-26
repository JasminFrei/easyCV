package com.zuehlke.training.easycv.ui.skills

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zuehlke.training.easycv.getOrAwaitValue
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SkillsViewModelTest {

    private lateinit var skillsViewModel: SkillsViewModel

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        skillsViewModel = SkillsViewModel()
    }

    @Test
    fun getText() {
        assertThat(
            skillsViewModel.text.getOrAwaitValue(),
            CoreMatchers.containsString("skills")
        )
    }
}