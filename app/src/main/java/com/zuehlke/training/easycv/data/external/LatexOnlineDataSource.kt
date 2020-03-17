package com.zuehlke.training.easycv.data.external

import android.content.Context
import android.util.Log
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject


class LatexOnlineDataSource @Inject constructor(val service: LatexOnlineService) {

    suspend fun createCvPdf(file: File, context: Context) {
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)

        val body = MultipartBody.Part.createFormData("tarball.tar", file.name, requestFile)

        try {
            val reply = service.createPdf("cv.tex", body)
            context.openFileOutput("cv.pdf", Context.MODE_PRIVATE).use {
                it.write(reply.bytes())
            }
        } catch (e: Exception) {
            Log.e("LatexOnlineDataSource", "Error while creating cv", e)
        }
    }
}