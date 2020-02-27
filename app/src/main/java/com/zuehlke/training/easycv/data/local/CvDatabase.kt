package com.zuehlke.training.easycv.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Profile::class], version = 1, exportSchema = false)
abstract class CvDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao

}