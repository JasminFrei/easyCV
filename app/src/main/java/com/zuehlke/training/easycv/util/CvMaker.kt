package com.zuehlke.training.easycv.util

import android.content.Context
import com.zuehlke.training.easycv.data.local.Profile
import org.apache.commons.compress.archivers.tar.TarArchiveEntry
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream
import java.io.File
import javax.inject.Inject

class CvMaker @Inject constructor(val context: Context) {

    fun makeCv(profile: Profile, template: CvTemplate): File {
        val paramFile = createParamFile(profile)
        val tarball = createTarFile(paramFile, template)
        return tarball
    }

    private fun createParamFile(profile: Profile): File {
        val paramValue = mapOf<String, String>(
            Pair("cvName", profile.name),
            Pair("cvLastname", profile.lastname)
        )
        val output = StringBuilder()
        for (param in paramValue.entries) {
            output.append("\\def \\${param.key} {${param.value}}\n")
        }
        context.openFileOutput("param.tex", Context.MODE_PRIVATE).use {
            it.write(output.toString().toByteArray())
        }
        return File(context.filesDir, "param.tex")
    }

    private fun createTarFile(paramFile: File, template: CvTemplate): File {
        TarArchiveOutputStream(context.openFileOutput("archive.tar", Context.MODE_PRIVATE)).use {
            addFileToArchive(paramFile.name, paramFile.length(), paramFile.readBytes(), it)
            for (filename in (template.additionalFiles + listOf(template.mainFile))) {
                val path = template.getTemplatePath(filename)
                val inputStream = context.assets.open(path)
                val bytes = inputStream.readBytes()
                addFileToArchive(filename, bytes.size.toLong(), bytes, it)
            }
        }
        return File(context.filesDir, "archive.tar")
    }

    private fun addFileToArchive(
        name: String,
        length: Long,
        bytes: ByteArray,
        it: TarArchiveOutputStream
    ) {
        val entry = TarArchiveEntry(name)
        entry.size = length
        it.putArchiveEntry(entry)
        it.write(bytes)
        it.closeArchiveEntry()
    }

}