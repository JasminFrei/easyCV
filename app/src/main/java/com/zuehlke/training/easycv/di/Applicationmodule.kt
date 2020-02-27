package com.zuehlke.training.easycv.di

import android.content.Context
import androidx.room.Room
import com.zuehlke.training.easycv.data.local.CvDatabase
import dagger.Module
import dagger.Provides
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
}