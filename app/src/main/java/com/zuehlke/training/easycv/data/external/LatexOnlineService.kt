package com.zuehlke.training.easycv.data.external

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface LatexOnlineService {

    @Multipart
    @POST("data?force=false&command=xelatex")
    suspend fun createPdf(@Query("target") filename: String, @Part tarball: MultipartBody.Part): ResponseBody
}