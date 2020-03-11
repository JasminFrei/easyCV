package com.zuehlke.training.easycv.util

import com.zuehlke.training.easycv.R
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Assert.assertThat
import org.junit.Test

class InputValidatorTest {

    @Test
    fun testDefault_normalInput() {
        val result = InputValidator.validateText("Test")
        assertThat(result.valid, `is`(true))
        assertThat(result.message, nullValue())
        assertThat(result.args.size, `is`(0))
    }

    @Test
    fun testOk_emptyInput() {
        val result = InputValidator.validateText("")
        assertThat(result.valid, `is`(true))
        assertThat(result.message, nullValue())
        assertThat(result.args.size, `is`(0))
    }

    @Test
    fun testDefault_nullInput() {
        val result = InputValidator.validateText(null)
        assertThat(result.valid, `is`(true))
        assertThat(result.message, nullValue())
        assertThat(result.args.size, `is`(0))
    }

    @Test
    fun testMinLength_emptyInput_Default() {
        val result = InputValidator.validateText("", 3)
        assertThat(result.valid, `is`(false))
        assertThat(result.message, `is`(R.string.min_length))
        assertThat(result.args.size, `is`(1))
        assertThat(result.args[0], `is`("3"))
    }

    @Test
    fun testMinLength_shortInput_Min5() {
        val result = InputValidator.validateText("123", 5)
        assertThat(result.valid, `is`(false))
        assertThat(result.message, `is`(R.string.min_length))
        assertThat(result.args.size, `is`(1))
        assertThat(result.args[0], `is`("5"))
    }

    @Test
    fun testMinLenght_normalInput_Min3() {
        val result = InputValidator.validateText("Lorem ipsum", 3)
        assertThat(result.valid, `is`(true))
        assertThat(result.message, nullValue())
        assertThat(result.args.size, `is`(0))
    }

    @Test
    fun testMinLength_nullInput() {
        val result = InputValidator.validateText(null, 3)
        assertThat(result.valid, `is`(false))
        assertThat(result.message, `is`(R.string.min_length))
        assertThat(result.args.size, `is`(1))
        assertThat(result.args[0], `is`("3"))
    }

    @Test
    fun testMaxLength_emptyInput_Default() {
        val noInput = InputValidator.validateText("", maxLenght = 10)
        assertThat(noInput.valid, `is`(true))
        assertThat(noInput.message, nullValue())
        assertThat(noInput.args.size, `is`(0))
    }

    @Test
    fun testMaxLength_longInput_Max5() {
        val result = InputValidator.validateText("This input is too long", maxLenght = 5)
        assertThat(result.valid, `is`(false))
        assertThat(result.message, `is`(R.string.max_length))
        assertThat(result.args.size, `is`(1))
        assertThat(result.args[0], `is`("5"))
    }

    @Test
    fun testMaxLenght_normalInput_Max20() {
        val result = InputValidator.validateText("Lorem ipsum", maxLenght = 20)
        assertThat(result.valid, `is`(true))
        assertThat(result.message, nullValue())
        assertThat(result.args.size, `is`(0))
    }

    @Test
    fun testMinMaxLength_normalInput_Min3Max20() {
        val result = InputValidator.validateText("Lorem ipsum", 3, 20)
        assertThat(result.valid, `is`(true))
        assertThat(result.message, nullValue())
        assertThat(result.args.size, `is`(0))
    }

    @Test
    fun testMinMaxLength_shortInput_Min5Max20() {
        val result = InputValidator.validateText("123", 5, 20)
        assertThat(result.valid, `is`(false))
        assertThat(result.message, `is`(R.string.min_length))
        assertThat(result.args.size, `is`(1))
        assertThat(result.args[0], `is`("5"))
    }

    @Test
    fun testMinMaxLength_longInput_Min5Max10() {
        val result = InputValidator.validateText("This input is too long", 5, 10)
        assertThat(result.valid, `is`(false))
        assertThat(result.message, `is`(R.string.max_length))
        assertThat(result.args.size, `is`(1))
        assertThat(result.args[0], `is`("10"))
    }

    @Test
    fun testMinMaxLength_nullInput() {
        val result = InputValidator.validateText(null, 5, 10)
        assertThat(result.valid, `is`(false))
        assertThat(result.message, `is`(R.string.min_length))
        assertThat(result.args.size, `is`(1))
        assertThat(result.args[0], `is`("5"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testMinGreaterThanMax() {
        InputValidator.validateText("BlaBla", 10, 2)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testMinusMin() {
        InputValidator.validateText("BlaBla", -10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testMinusMax() {
        InputValidator.validateText("BlaBla", maxLenght = -10)
    }
}