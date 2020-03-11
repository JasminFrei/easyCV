package com.zuehlke.training.easycv.data.local

import androidx.lifecycle.LiveData
import javax.inject.Inject

class LocalRepository @Inject constructor(private val database: CvDatabase) {

    fun getProfile(): LiveData<Profile?> {
        return database.profileDao().getProfile()
    }

    suspend fun getProfilePlain(): Profile? {
        return database.profileDao().getProfilePlain()
    }

    suspend fun saveProfile(profile: Profile) {
        database.profileDao().insert(profile)
    }
}