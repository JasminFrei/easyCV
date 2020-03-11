package com.zuehlke.training.easycv.ui.editprofile

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
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
    fun testContent_NoData() {
        launchFragmentInContainer<AddressFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.txtStreet)).check(matches(withText("")))
        onView(withId(R.id.txtZip)).check(matches(withText("")))
        onView(withId(R.id.txtCity)).check(matches(withText("")))
        onView(withId(R.id.txtCountry)).check(matches(withText("")))
    }
}
