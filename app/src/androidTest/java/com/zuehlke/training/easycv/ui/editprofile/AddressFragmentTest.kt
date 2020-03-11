package com.zuehlke.training.easycv.ui.editprofile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.ViewModelProvider
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
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@MediumTest
@RunWith(AndroidJUnit4::class)
class AddressFragmentTest {

    @Inject
    lateinit var database: CvDatabase

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


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
        launchFragmentInContainer<AddressFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.txtStreet)).check(matches(withText("")))
        onView(withId(R.id.txtZip)).check(matches(withText("")))
        onView(withId(R.id.txtCity)).check(matches(withText("")))
        onView(withId(R.id.txtCountry)).check(matches(withText("")))
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
                    "country",
                    "000",
                    "example@example.com",
                    "Blabla",
                    null
                )
            )
            val scenario = launchFragmentInContainer<AddressFragment>(themeResId = R.style.AppTheme)
            scenario.onFragment {
                it.loadDataIntoViewModel()
            }

            onView(withId(R.id.txtStreet)).check(matches(withText("street")))
            onView(withId(R.id.txtZip)).check(matches(withText("zip")))
            onView(withId(R.id.txtCity)).check(matches(withText("location")))
            onView(withId(R.id.txtCountry)).check(matches(withText("country")))
        }
    }
}
