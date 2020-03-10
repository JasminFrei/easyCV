package com.zuehlke.training.easycv.ui.editprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.zuehlke.training.easycv.CvApplication
import com.zuehlke.training.easycv.R
import com.zuehlke.training.easycv.di.EditProfileComponent


class EditProfileActivity : AppCompatActivity() {
    lateinit var editProfileComponent: EditProfileComponent

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        editProfileComponent =
            (application as CvApplication).appComponent.editProfileComponent().create()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration.Builder()
            .setFallbackOnNavigateUpListener {
                // Trigger the Activity's navigate up functionality
                super.onSupportNavigateUp()
            }.build()
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navigateUp(navController, appBarConfiguration);
    }

}