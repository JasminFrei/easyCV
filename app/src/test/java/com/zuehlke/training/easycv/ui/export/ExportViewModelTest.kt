package com.zuehlke.training.easycv.ui.export

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zuehlke.training.easycv.getOrAwaitValue
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExportViewModelTest {

    private lateinit var exportViewModel: ExportViewModel

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        //exportViewModel = ExportViewModel()
    }

    @Test
    fun getText() {
        assertThat(
            exportViewModel.text.getOrAwaitValue(),
            CoreMatchers.containsString("export")
        )
    }
}