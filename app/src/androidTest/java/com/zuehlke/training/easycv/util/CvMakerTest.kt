package com.zuehlke.training.easycv.util

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.zuehlke.training.easycv.cvmaker.CvMaker
import com.zuehlke.training.easycv.data.local.Profile
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class CvMakerTest {

    private lateinit var cvMaker: CvMaker
    private lateinit var profile: Profile

    @Before
    fun setUp() {
        cvMaker =
            CvMaker(InstrumentationRegistry.getInstrumentation().context)
        profile = Profile(
            42,
            "name",
            "lastname",
            42L,
            "street",
            "zip",
            "location",
            "country",
            "phone",
            "email",
            "blabla",
            null
        )
    }

    @Test
    fun testFillTemplate() {
        //val output = cvMaker.makeCv(profile, "plain/plainCv.tex")
        //Todo: It's not testable right now.
    }
}