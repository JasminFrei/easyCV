package com.zuehlke.training.easycv.ui.editprofile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zuehlke.training.easycv.CvApplication
import com.zuehlke.training.easycv.R
import com.zuehlke.training.easycv.util.DateFormatter
import kotlinx.android.synthetic.main.fragment_basic_info.*
import javax.inject.Inject

class BasicInformationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by activityViewModels<EditProfileViewModel> { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_basic_info, container, false)
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as CvApplication).submoduleInjector.injectBasicInformationFragment(
            this,
            requireActivity()
        )
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

        btnNext.setOnClickListener {
            viewModel.name = txtName.text.toString()
            viewModel.lastname = txtLastname.text.toString()
            viewModel.birthdate = 0L //Todo: Change "Text"-Date into long
            viewModel.email = txtEmail.text.toString()
            viewModel.phone = txtPhone.text.toString()
            viewModel.description = txtAbout.toString()
            //Todo: Validate input
            findNavController().navigate(BasicInformationFragmentDirections.actionEditProfileFragmentToAdressFragment())
        }
    }
}