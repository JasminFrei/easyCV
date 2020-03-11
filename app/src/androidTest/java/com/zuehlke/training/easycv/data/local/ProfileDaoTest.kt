package com.zuehlke.training.easycv.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ProfileDaoTest {
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var database: CvDatabase

    @Before
    fun initDb() {
        // Using an in-memory database so that the information stored here disappears when the
        // process is killed.
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            CvDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertNewProfileWithoutId() = runBlockingTest {
        val profile = Profile(
            null,
            "John",
            "Smith",
            42L,
            "street",
            "zip",
            "location",
            "CH",
            "000",
            "example@example.com",
            "Blabla",
            null
        )
        database.profileDao().insert(profile)

        val loadedProfile = database.profileDao().getProfile().getOrAwaitValue()

        assertThat(loadedProfile, notNullValue())
        assertThat(loadedProfile!!.profile_id, notNullValue())
        assertThat(loadedProfile.name, `is`("John"))
        assertThat(loadedProfile.lastname, `is`("Smith"))
        assertThat(loadedProfile.birthdate, `is`(42L))
        assertThat(loadedProfile.street, `is`("street"))
        assertThat(loadedProfile.zip, `is`("zip"))
        assertThat(loadedProfile.location, `is`("location"))
        assertThat(loadedProfile.country, `is`("CH"))
        assertThat(loadedProfile.phone, `is`("000"))
        assertThat(loadedProfile.email, `is`("example@example.com"))
        assertThat(loadedProfile.description, `is`("Blabla"))
        assertThat(loadedProfile.imagePath, `is`(nullValue()))
    }

    @Test
    fun insertProfileAndGetBackWithId() = runBlockingTest {
        val profile = Profile(
            42,
            "John",
            "Smith",
            42L,
            "street",
            "zip",
            "location",
            "CH",
            "000",
            "example@example.com",
            "Blabla",
            null
        )
        database.profileDao().insert(profile)

        val loadedProfile = database.profileDao().getProfile().getOrAwaitValue()

        assertThat(loadedProfile, notNullValue())
        assertThat(loadedProfile!!.profile_id, `is`(42))
        assertThat(loadedProfile.name, `is`("John"))
        assertThat(loadedProfile.lastname, `is`("Smith"))
        assertThat(loadedProfile.birthdate, `is`(42L))
        assertThat(loadedProfile.street, `is`("street"))
        assertThat(loadedProfile.zip, `is`("zip"))
        assertThat(loadedProfile.location, `is`("location"))
        assertThat(loadedProfile.country, `is`("CH"))
        assertThat(loadedProfile.phone, `is`("000"))
        assertThat(loadedProfile.email, `is`("example@example.com"))
        assertThat(loadedProfile.description, `is`("Blabla"))
        assertThat(loadedProfile.imagePath, `is`(nullValue()))
    }

    @Test
    fun updateNameInProfile() = runBlockingTest {
        val profile = Profile(
            42,
            "John",
            "Smith",
            42L,
            "street",
            "zip",
            "location",
            "CH",
            "000",
            "example@example.com",
            "Blabla",
            null
        )
        database.profileDao().insert(profile)

        val profileLiveData = database.profileDao().getProfile()
        val firstProfile = profileLiveData.getOrAwaitValue()
        assertThat(firstProfile, notNullValue())
        assertThat(firstProfile!!.name, `is`("John"))

        val newProfile = Profile(
            42,
            "Hans",
            "Smith",
            42L,
            "street",
            "zip",
            "location",
            "CH",
            "000",
            "example@example.com",
            "Blabla",
            null
        )
        database.profileDao().insert(newProfile)

        val secondProfile = profileLiveData.getOrAwaitValue()
        assertThat(secondProfile, notNullValue())
        assertThat(secondProfile!!.name, `is`("Hans"))
    }
}