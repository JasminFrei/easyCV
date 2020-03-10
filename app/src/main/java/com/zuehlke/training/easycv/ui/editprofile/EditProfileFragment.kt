package com.zuehlke.training.easycv.ui.editprofile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zuehlke.training.easycv.CvApplication
import com.zuehlke.training.easycv.R
import com.zuehlke.training.easycv.util.DateFormatter
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import javax.inject.Inject

class EditProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<EditProfileViewModel> { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_edit_profile, container, false)
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as CvApplication).appComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.profile.observe(viewLifecycleOwner, Observer { profile ->
            txtName.setText(profile?.name)
            txtLastname.setText(profile?.lastname)
            profile?.birthdate?.let { txtBirthdate.setText(DateFormatter.formatNormalDate(it)) }
            txtEmail.setText(profile?.email)
            txtPhone.setText(profile?.phone)
            txtAbout.setText(profile?.description)
        })

        btnSave.setOnClickListener {
            Toast.makeText(requireContext(), "Save profile", Toast.LENGTH_SHORT).show()
        }
    }
}