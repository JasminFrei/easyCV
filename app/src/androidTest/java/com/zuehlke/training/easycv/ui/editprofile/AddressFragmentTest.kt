package com.zuehlke.training.easycv.ui.editprofile

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.zuehlke.training.easycv.R
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class AddressFragmentTest {

    @Test
    fun testInitialText() {
        launchFragmentInContainer<AddressFragment>(themeResId = R.style.AppTheme)

        onView(withText("Edit Address")).check(matches(isDisplayed()))
    }
}
