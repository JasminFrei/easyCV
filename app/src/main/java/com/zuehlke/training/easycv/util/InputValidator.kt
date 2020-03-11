package com.zuehlke.training.easycv.util

import com.zuehlke.training.easycv.R


object InputValidator {

    fun validateText(
        text: String?,
        minLength: Int = 0,
        maxLenght: Int = Int.MAX_VALUE
    ): InputValidationResult {
        if (minLength < 0 || maxLenght < 0 || minLength > maxLenght) throw IllegalArgumentException()

        if (text?.length ?: 0 < minLength) {
            return InputValidationResult(false, R.string.min_length, arrayOf(minLength.toString()))
        }
        if (text?.length ?: 0 > maxLenght) {
            return InputValidationResult(false, R.string.max_length, arrayOf(maxLenght.toString()))
        }
        return InputValidationResult(true)
    }
}