package com.lejik.spacex.ui.fragments.launchdetailsinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lejik.spacex.R
import com.lejik.spacex.databinding.LaunchDetailInfoFragmentBinding

class LaunchDetailInfoFragment : Fragment() {
    private var _binding: LaunchDetailInfoFragmentBinding? = null
    private val binding: LaunchDetailInfoFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LaunchDetailInfoFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        backButton = view.findViewById(R.id.backButton)
//        nextButton = view.findViewById(R.id.nextButton)
//        textDetailInfo = view.findViewById(R.id.textDetailInfo)

        binding.backButton.setOnClickListener() {
            findNavController().navigate(R.id.action_launchDetailInfoFragment_to_launchGeneralInfoFragment)
        }

        binding.nextButton.setOnClickListener() {
            findNavController().navigate(R.id.action_launchDetailInfoFragment_to_launchRecyclerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}