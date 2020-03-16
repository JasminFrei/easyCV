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
import com.zuehlke.training.easycv.databinding.FragmentBasicInfoBinding
import com.zuehlke.training.easycv.util.DateFormatter
import javax.inject.Inject

class BasicInformationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by activityViewModels<EditProfileViewModel> { viewModelFactory }

    private lateinit var binding: FragmentBasicInfoBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasicInfoBinding.inflate(inflater)
        return binding.root
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
        viewModel.loadProfile()

        viewModel.profileLoaded.observe(viewLifecycleOwner, Observer { loaded ->
            if (loaded) {
                binding.txtName.setText(viewModel.name)
                binding.txtLastname.setText(viewModel.lastname)
                viewModel.birthdate?.let {
                    binding.txtBirthdate.setText(
                        DateFormatter.formatNormalDate(
                            it
                        )
                    )
                }
                binding.txtEmail.setText(viewModel.email)
                binding.txtPhone.setText(viewModel.phone)
                binding.txtAbout.setText(viewModel.description)
            }
        })

        binding.btnNext.setOnClickListener {
            viewModel.name = binding.txtName.text.toString()

            val res = viewModel.validateName()
            if (!res.valid) {
                binding.inputName.isErrorEnabled = true
                binding.inputName.error = getString(res.message!!, *res.args)
                return@setOnClickListener
            }
            //Todo: Validate other input
            viewModel.lastname = binding.txtLastname.text.toString()
            viewModel.birthdate = 0L //Todo: Change "Text"-Date into long
            viewModel.email = binding.txtEmail.text.toString()
            viewModel.phone = binding.txtPhone.text.toString()
            viewModel.description = binding.txtAbout.text.toString()
            findNavController().navigate(BasicInformationFragmentDirections.actionEditProfileFragmentToAdressFragment())
        }
    }
}