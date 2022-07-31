package com.lejik.spacex.ui.fragments.launchdetailsinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.lejik.spacex.R

class LaunchDetailInfoFragment : Fragment() {
    private lateinit var backButton: Button
    private lateinit var textDetailInfo: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.launch_detail_info_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backButton = view.findViewById(R.id.backButton)
        textDetailInfo = view.findViewById(R.id.textDetailInfo)
        val navController = NavHostFragment.findNavController(this)
        backButton.setOnClickListener() {
            navController.navigate(R.id.action_launchDetailInfoFragment_to_launchGeneralInfoFragment)
        }
    }
}