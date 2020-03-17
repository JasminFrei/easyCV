package com.zuehlke.training.easycv.util

import android.content.Context
import com.zuehlke.training.easycv.data.local.Profile
import org.apache.commons.compress.archivers.tar.TarArchiveEntry
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream
import java.io.File
import javax.inject.Inject

class CvMaker @Inject constructor(val context: Context) {

    fun makeCv(profile: Profile, templatePath: String = "plain/plainCv.tex"): File {
        val cvTemplate = readTemplate(templatePath)
        val cv = fillTemplate(cvTemplate, profile)

        val cvFile = writeCvToFile(cv, "cv.tex")

        val tarball = createTarFile(cvFile)
        return tarball
    }

    private fun readTemplate(templatePath: String): String {
        val assetManager = context.assets
        val inputStream = assetManager.open(templatePath)
        val cvTemplate = inputStream.bufferedReader().use { it.readText() }
        return cvTemplate
    }

    private fun fillTemplate(template: String, profile: Profile): String {
        return template.format(profile.name, profile.lastname)
    }

    private fun writeCvToFile(cv: String, filename: String): File {
        context.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(cv.toByteArray())
        }
        return File(context.filesDir, filename)
    }

    private fun createTarFile(cvFile: File): File {
        TarArchiveOutputStream(context.openFileOutput("archive.tar", Context.MODE_PRIVATE)).use {
            val entry = TarArchiveEntry(cvFile.name)
            entry.size = cvFile.length()
            it.putArchiveEntry(entry);
            it.write(cvFile.readBytes());
            it.closeArchiveEntry();
        }
        return File(context.filesDir, "archive.tar")
    }

}