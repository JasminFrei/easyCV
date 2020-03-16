package com.zuehlke.training.easycv.ui.editprofile

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zuehlke.training.easycv.CvApplication
import com.zuehlke.training.easycv.databinding.FragmentAddressBinding
import org.jetbrains.annotations.TestOnly
import javax.inject.Inject

class AddressFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by activityViewModels<EditProfileViewModel> { viewModelFactory }

    private lateinit var binding: FragmentAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as CvApplication).submoduleInjector.injectAddressFragment(
            this,
            requireActivity()
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.profileLoaded.observe(viewLifecycleOwner, Observer { loaded ->
            if (loaded) {
                binding.txtStreet.setText(viewModel.street)
                binding.txtZip.setText(viewModel.zip)
                binding.txtCity.setText(viewModel.city)
                binding.txtCountry.setText(viewModel.country)
            }
        })

        binding.btnNext.setOnClickListener {
            viewModel.street = binding.txtStreet.text.toString()
            viewModel.zip = binding.txtZip.text.toString()
            viewModel.city = binding.txtCity.text.toString()
            viewModel.country = binding.txtCountry.text.toString()
            //Todo: Input Validation
            viewModel.saveProfile().observe(viewLifecycleOwner, Observer { worked ->
                if (worked) {
                    requireActivity().setResult(Activity.RESULT_OK)
                    requireActivity().finish()
                } else {
                    Toast.makeText(requireActivity(), "Did not work!", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    @TestOnly
    fun loadDataIntoViewModel() {
        viewModel.loadProfile()
    }
}