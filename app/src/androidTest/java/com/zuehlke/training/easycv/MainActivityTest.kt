package com.zuehlke.training.easycv

import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.*
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testTabs() {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Profile Tab
        onView(withId(R.id.text_home)).check(matches(withText(containsString("profile"))))
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.action_bar))))
            .check(matches(withText(targetContext.getString(R.string.title_profile))))

        // Experience Tab
        onView(withId(R.id.navigation_experience)).perform(click())
        onView(withId(R.id.text_dashboard)).check(matches(withText(containsString("experience"))))
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.action_bar))))
            .check(matches(withText(targetContext.getString(R.string.title_experience))))

        // Education Tab
        onView(withId(R.id.navigation_education)).perform(click())
        onView(withId(R.id.text_dashboard)).check(matches(withText(containsString("education"))))
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.action_bar))))
            .check(matches(withText(targetContext.getString(R.string.title_education))))

        // Skills Tab
        onView(withId(R.id.navigation_skills)).perform(click())
        onView(withId(R.id.text_notifications)).check(matches(withText(containsString("skills"))))
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.action_bar))))
            .check(matches(withText(targetContext.getString(R.string.title_skills))))

        // Export Tab
        onView(withId(R.id.navigation_export)).perform(click())
        onView(withId(R.id.text_dashboard)).check(matches(withText(containsString("export"))))
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.action_bar))))
            .check(matches(withText(targetContext.getString(R.string.title_export))))

        // Profile Tab
        onView(withId(R.id.navigation_profile)).perform(click())
        onView(withId(R.id.text_home)).check(matches(withText(containsString("profile"))))
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.action_bar))))
            .check(matches(withText(targetContext.getString(R.string.title_profile))))

        activityScenario.close()
    }
}