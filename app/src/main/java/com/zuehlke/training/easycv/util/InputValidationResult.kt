package com.zuehlke.training.easycv.util

import androidx.annotation.StringRes

data class InputValidationResult(
    val valid: Boolean, @StringRes val message: Int? = null,
    val args: Array<String> = arrayOf()
)