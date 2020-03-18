package com.zuehlke.training.easycv.util

data class CvTemplate(
    val path: String,
    val mainFile: String,
    val additionalFiles: List<String> = listOf()
) {

    fun getTemplatePath(file: String): String {
        return "$path/$file"
    }

}