package com.zuehlke.training.easycv.ui.profile

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import com.zuehlke.training.easycv.MyTestApplication
import com.zuehlke.training.easycv.R
import com.zuehlke.training.easycv.data.local.CvDatabase
import com.zuehlke.training.easycv.data.local.Profile
import com.zuehlke.training.easycv.di.TestAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


@ExperimentalCoroutinesApi
@MediumTest
@RunWith(AndroidJUnit4::class)
class ProfileFragmentTest {
    @Inject
    lateinit var database: CvDatabase

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        //Also inject the repository in this test class to add fake data per test
        ((InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
                as MyTestApplication).appComponent as TestAppComponent).inject(this)
    }

    @After
    fun closeDB() = database.close()

    @Test
    fun testContent_NoData() {
        launchFragmentInContainer<ProfileFragment>(themeResId = R.style.AppTheme_NoActionBar)

        onView(withId(R.id.lblNoData)).check(matches(isDisplayed()))
        onView(withId(R.id.profile_content)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testContent_WithData() {
        runBlockingTest {
            database.profileDao().insert(
                Profile(
                    42,
                    "John",
                    "Smith",
                    42L,
                    "street",
                    "zip",
                    "location",
                    "",
                    "000",
                    "example@example.com",
                    "Blabla",
                    null
                )
            )
            launchFragmentInContainer<ProfileFragment>(themeResId = R.style.AppTheme_NoActionBar)

            onView(withId(R.id.lblNoData)).check(matches(not(isDisplayed())))
            onView(withId(R.id.profile_content)).check(matches(isDisplayed()))
            onView(withId(R.id.lblName)).check(matches(withText("John")))
            onView(withId(R.id.lblLastname)).check(matches(withText("Smith")))
            onView(withId(R.id.lblBirthdate)).check(matches(withText("42")))
            onView(withId(R.id.lblEmail)).check(matches(withText("example@example.com")))
            onView(withId(R.id.lblAddress)).check(matches(withText("street\nzip location")))
            onView(withId(R.id.lblPhone)).check(matches(withText("000")))
            onView(withId(R.id.lblAbout)).check(matches(withText("Blabla")))
        }
    }

    @Test
    fun testActionBar() {
        launchFragmentInContainer<ProfileFragment>(themeResId = R.style.AppTheme_NoActionBar)

        onView(withId(R.id.cvProfile)).check(matches(isDisplayed()))

        onView(withId(R.id.app_bar)).perform(collapseAppBarLayout())
        //Todo: I don't like this!
        Thread.sleep(15)
        onView(withId(R.id.cvProfile)).check(matches(not(isDisplayed())))
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