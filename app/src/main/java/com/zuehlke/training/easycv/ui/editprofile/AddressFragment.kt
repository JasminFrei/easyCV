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
import com.zuehlke.training.easycv.CvApplication
import com.zuehlke.training.easycv.R
import kotlinx.android.synthetic.main.fragment_address.*
import org.jetbrains.annotations.TestOnly
import javax.inject.Inject

class AddressFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by activityViewModels<EditProfileViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_address, container, false)
        return root
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
                txtStreet.setText(viewModel.street)
                txtZip.setText(viewModel.zip)
                txtCity.setText(viewModel.city)
                txtCountry.setText(viewModel.country)
            }
        })

        btnNext.setOnClickListener {
            viewModel.street = txtStreet.text.toString()
            viewModel.zip = txtZip.text.toString()
            viewModel.city = txtCity.text.toString()
            viewModel.country = txtCountry.text.toString()
            //Todo: Input Validation
        }
    }

    @TestOnly
    fun loadDataIntoViewModel() {
        viewModel.loadProfile()
    }
}