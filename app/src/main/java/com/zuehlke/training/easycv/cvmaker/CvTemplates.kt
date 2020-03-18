package com.zuehlke.training.easycv.cvmaker

object CvTemplates {
    val plain =
        CvTemplate("plain", "plainCv.tex")
    val cv17 = CvTemplate(
        "cv_17",
        "main.tex",
        listOf("developercv.cls")
    )
}