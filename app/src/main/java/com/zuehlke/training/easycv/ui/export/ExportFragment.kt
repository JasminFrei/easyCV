package com.zuehlke.training.easycv.ui.export

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zuehlke.training.easycv.CvApplication
import com.zuehlke.training.easycv.R
import javax.inject.Inject

class ExportFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val exportViewModel by viewModels<ExportViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_export, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        exportViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity!!.applicationContext as CvApplication).appComponent.inject(this)
    }
}