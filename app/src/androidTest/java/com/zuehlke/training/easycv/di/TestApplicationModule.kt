package com.zuehlke.training.easycv.di

import android.content.Context
import androidx.room.Room
import com.zuehlke.training.easycv.data.local.CvDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TestApplicationModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideDatabase(context: Context): CvDatabase {
        return Room.inMemoryDatabaseBuilder(
            context.applicationContext,
            CvDatabase::class.java
        )
            .allowMainThreadQueries() //They also do this in the tutorial, it seems to be ok
            .build()
    }
}