package com.lejik.spacex.ui.fragments.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lejik.spacex.R
import com.lejik.spacex.adapter.ItemAdapter
import com.lejik.spacex.data.DataSource
import com.lejik.spacex.databinding.LaunchRecyclerFragmentBinding

class LaunchRecyclerFragment : Fragment() {
    private var _binding: LaunchRecyclerFragmentBinding? = null
    private val binding: LaunchRecyclerFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LaunchRecyclerFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.escapeButton.setOnClickListener {
            findNavController().navigate(R.id.action_launchRecyclerFragment_to_launchGeneralInfoFragment)
        }
        val myDataSet = DataSource().loadData()
        binding.recyclerView.apply {
            adapter = ItemAdapter(requireContext(), myDataSet)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}