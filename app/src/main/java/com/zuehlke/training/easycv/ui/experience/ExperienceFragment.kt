package com.zuehlke.training.easycv.ui.experience

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zuehlke.training.easycv.CvApplication
import com.zuehlke.training.easycv.databinding.FragmentExperienceBinding
import javax.inject.Inject

class ExperienceFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val experienceViewModel by viewModels<ExperienceViewModel> { viewModelFactory }

    private lateinit var binding: FragmentExperienceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExperienceBinding.inflate(inflater)
        experienceViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textDashboard.text = it
        })
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as CvApplication).appComponent.inject(this)
    }
}
