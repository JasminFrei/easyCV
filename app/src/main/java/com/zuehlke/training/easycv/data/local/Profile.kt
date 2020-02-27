package com.zuehlke.training.easycv.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
class Profile(
    @PrimaryKey(autoGenerate = true) val profile_id: Int,
    val name: String,
    val lastname: String,
    val birthdate: Long,
    val street: String,
    val zip: String,
    val location: String,
    val country: String?,
    val phone: String,
    val email: String,
    val description: String?,
    val imagePath: String?
)