package com.zuehlke.training.easycv.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.AppBarLayout
import com.zuehlke.training.easycv.CvApplication
import com.zuehlke.training.easycv.R
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.profile_content.*
import javax.inject.Inject

class ProfileFragment : Fragment() {

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
                lblBirthdate.text = profile.birthdate.toString() //Todo
                lblEmail.text = profile.email
                lblPhone.text = profile.phone
                lblAddress.text = "${profile.street}\n${profile.zip} ${profile.location}"
                lblAbout.text = profile.description
            }
        })
    }
}

