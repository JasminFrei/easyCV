package com.zuehlke.training.easycv

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.CoreMatchers.containsString
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testTabs() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Profile Tab
        onView(withId(R.id.btnEditProfile)).check(matches(isDisplayed()))

        // Experience Tab
        onView(withId(R.id.navigation_experience)).perform(click())
        onView(withId(R.id.text_dashboard)).check(matches(withText(containsString("experience"))))

        // Education Tab
        onView(withId(R.id.navigation_education)).perform(click())
        onView(withId(R.id.text_dashboard)).check(matches(withText(containsString("education"))))

        // Skills Tab
        onView(withId(R.id.navigation_skills)).perform(click())
        onView(withId(R.id.text_notifications)).check(matches(withText(containsString("skills"))))

        // Export Tab
        onView(withId(R.id.navigation_export)).perform(click())
        onView(withId(R.id.text_dashboard)).check(matches(withText(containsString("export"))))

        // Profile Tab
        onView(withId(R.id.navigation_profile)).perform(click())
        onView(withId(R.id.btnEditProfile)).check(matches(isDisplayed()))

        activityScenario.close()
    }
}