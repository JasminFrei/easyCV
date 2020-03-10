package com.zuehlke.training.easycv.ui.editprofile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.zuehlke.training.easycv.MyTestApplication
import com.zuehlke.training.easycv.R
import com.zuehlke.training.easycv.data.local.CvDatabase
import com.zuehlke.training.easycv.data.local.Profile
import com.zuehlke.training.easycv.di.TestAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@ExperimentalCoroutinesApi
@MediumTest
@RunWith(AndroidJUnit4::class)
class BasicInformationFragmentTest {

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
        database.clearAllTables()
    }


    @Test
    fun testContent_NoData() {
        launchFragmentInContainer<BasicInformationFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.txtName)).check(matches(withText("")))
        onView(withId(R.id.txtLastname)).check(matches(withText("")))
        onView(withId(R.id.txtBirthdate)).check(matches(withText("")))
        onView(withId(R.id.txtPhone)).check(matches(withText("")))
        onView(withId(R.id.txtEmail)).check(matches(withText("")))
        onView(withId(R.id.txtAbout)).check(matches(withText("")))
    }

    @Test
    fun testContent_WithData() {
        runBlockingTest {
            database.profileDao().insert(
                Profile(
                    42,
                    "John",
                    "Smith",
                    953938800000,
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
            launchFragmentInContainer<BasicInformationFragment>(themeResId = R.style.AppTheme)

            onView(withId(R.id.txtName)).check(matches(withText("John")))
            onView(withId(R.id.txtLastname)).check(matches(withText("Smith")))
            onView(withId(R.id.txtBirthdate)).check(matches(withText("25.03.2000")))
            onView(withId(R.id.txtEmail)).check(matches(withText("example@example.com")))
            onView(withId(R.id.txtPhone)).check(matches(withText("000")))
            onView(withId(R.id.txtAbout)).check(matches(withText("Blabla")))
        }
    }
}