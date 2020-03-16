package com.zuehlke.training.easycv.ui.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.AppBarLayout
import com.zuehlke.training.easycv.CvApplication
import com.zuehlke.training.easycv.R
import com.zuehlke.training.easycv.databinding.FragmentProfileBinding
import com.zuehlke.training.easycv.ui.editprofile.EditProfileActivity
import com.zuehlke.training.easycv.ui.navigateToActivityForResult
import com.zuehlke.training.easycv.util.DateFormatter

import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val profileViewModel by viewModels<ProfileViewModel> { viewModelFactory }

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as CvApplication).appComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        var isShow = true
        var scrollRange = -1
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                binding.cvProfile.visibility = View.INVISIBLE
                binding.toolbarLayout.title = getString(R.string.title_profile)
                isShow = true
            } else if (isShow) {
                binding.cvProfile.visibility = View.VISIBLE
                binding.toolbarLayout.title = " "
                isShow = false
            }
        })

        profileViewModel.profile.observe(viewLifecycleOwner, Observer { profile ->
            val profileContentBinding = binding.profileContent
            if (profile == null) {
                profileContentBinding.profileContent.visibility = View.INVISIBLE
                binding.lblNoData.visibility = View.VISIBLE
            } else {
                profileContentBinding.profileContent.visibility = View.VISIBLE
                binding.lblNoData.visibility = View.GONE
                profileContentBinding.lblName.text = profile.name
                profileContentBinding.lblLastname.text = profile.lastname
                profileContentBinding.lblBirthdate.text =
                    DateFormatter.formatNormalDate(profile.birthdate)
                profileContentBinding.lblEmail.text = profile.email
                profileContentBinding.lblPhone.text = profile.phone
                profileContentBinding.lblAddress.text =
                    "${profile.street}\n${profile.zip} ${profile.location}"
                profileContentBinding.lblAbout.text = profile.description
            }
        })

        binding.btnEditProfile.setOnClickListener {
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

