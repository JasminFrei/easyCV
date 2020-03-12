package com.zuehlke.training.easycv.ui.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.AppBarLayout
import com.zuehlke.training.easycv.CvApplication
import com.zuehlke.training.easycv.R
import com.zuehlke.training.easycv.ui.BaseFragment
import com.zuehlke.training.easycv.ui.editprofile.EditProfileActivity
import com.zuehlke.training.easycv.util.DateFormatter
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.profile_content.*
import javax.inject.Inject

class ProfileFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val profileViewModel by viewModels<ProfileViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as CvApplication).appComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var isShow = true
        var scrollRange = -1
        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                cvProfile.visibility = View.INVISIBLE
                toolbar_layout.title = getString(R.string.title_profile)
                isShow = true
            } else if (isShow) {
                cvProfile.visibility = View.VISIBLE
                toolbar_layout.title = " "
                isShow = false
            }
        })

        profileViewModel.profile.observe(viewLifecycleOwner, Observer { profile ->
            if (profile == null) {
                profile_content.visibility = View.INVISIBLE
                lblNoData.visibility = View.VISIBLE
            } else {
                profile_content.visibility = View.VISIBLE
                lblNoData.visibility = View.GONE
                lblName.text = profile.name
                lblLastname.text = profile.lastname
                lblBirthdate.text = DateFormatter.formatNormalDate(profile.birthdate)
                lblEmail.text = profile.email
                lblPhone.text = profile.phone
                lblAddress.text = "${profile.street}\n${profile.zip} ${profile.location}"
                lblAbout.text = profile.description
            }
        })

        btnEditProfile.setOnClickListener {
            navigateToActivityForResult(
                Intent(requireActivity(), EditProfileActivity::class.java),
                ProfileFragmentDirections.actionNavigationProfileToEditProfileActivity(),
                42
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 42) {
            when (resultCode) {
                Activity.RESULT_OK -> Toast.makeText(
                    requireActivity(),
                    R.string.profile_data_updated,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}

