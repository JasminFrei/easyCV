package com.zuehlke.training.easycv.ui.profile

import android.view.View
import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.appbar.AppBarLayout
import com.zuehlke.training.easycv.R
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matcher
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@RunWith(AndroidJUnit4::class)
class ProfileFragmentTest {
    @Test
    fun testText() {
        launchFragmentInContainer<ProfileFragment>(themeResId = R.style.AppTheme_NoActionBar)

        onView(withId(R.id.btnEditProfile)).check(matches(isDisplayed()))
    }

    @Test
    fun testActionBar() {
        launchFragmentInContainer<ProfileFragment>(themeResId = R.style.AppTheme_NoActionBar)
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext

        onView(withId(R.id.cvProfile)).check(matches(isDisplayed()))


        onView(withId(R.id.app_bar)).perform(collapseAppBarLayout())
        //Todo: I don't like this!
        Thread.sleep(15)
        onView(withId(R.id.cvProfile)).check(matches(not(isDisplayed())))
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar))))
            .check(matches(withText(targetContext.getString(R.string.title_profile))))
    }
}

fun collapseAppBarLayout(): ViewAction? {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return isAssignableFrom(AppBarLayout::class.java)
        }

        override fun getDescription(): String {
            return "Collapse App Bar Layout"
        }

        override fun perform(uiController: UiController, view: View) {
            val appBarLayout = view as AppBarLayout
            appBarLayout.setExpanded(false)
            uiController.loopMainThreadUntilIdle()
        }
    }
}