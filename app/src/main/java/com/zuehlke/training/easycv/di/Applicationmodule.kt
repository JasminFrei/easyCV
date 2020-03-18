package com.zuehlke.training.easycv.di

import android.content.Context
import androidx.room.Room
import com.zuehlke.training.easycv.data.external.LatexOnlineService
import com.zuehlke.training.easycv.data.local.CvDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object ApplicationModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideDatabase(context: Context): CvDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CvDatabase::class.java,
            "cv.db"
        ).build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://latexonline.cc/")
            .client(okHttpClient)
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideLatexOnlineService(retrofit: Retrofit): LatexOnlineService {
        return retrofit.create(LatexOnlineService::class.java)
    }
}