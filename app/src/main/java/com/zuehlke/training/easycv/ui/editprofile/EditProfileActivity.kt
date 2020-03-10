package com.zuehlke.training.easycv.ui.editprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zuehlke.training.easycv.R

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        setTitle(R.string.title_edit_profile)
    }

}