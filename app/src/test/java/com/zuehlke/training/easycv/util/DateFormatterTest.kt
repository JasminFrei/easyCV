package com.zuehlke.training.easycv.util

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class DateFormatterTest {

    @Test
    fun testNormalDateFormat() {
        assertThat(DateFormatter.formatNormalDate(1582899714230L), `is`("28.02.2020"))
        assertThat(DateFormatter.formatNormalDate(743896800000L), `is`("29.07.1993"))
    }
}