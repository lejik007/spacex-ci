package com.lejik.spacex.ui.fragments.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lejik.spacex.R
import com.lejik.spacex.adapter.AdapterLaunches
import com.lejik.spacex.databinding.LaunchRecyclerFragmentBinding
import com.lejik.spacex.injection.Injection
import com.lejik.spacex.viewmodels.ViewModelLaunches
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LaunchRecyclerFragment : Fragment() {
    private var _binding: LaunchRecyclerFragmentBinding? = null
    private val binding: LaunchRecyclerFragmentBinding?
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LaunchRecyclerFragmentBinding.inflate(inflater)
        val viewModel by viewModels<ViewModelLaunches>(
            factoryProducer = { Injection.provideViewModelFactory(owner = this) }
        )
        val items = viewModel.items
        val adapterLaunches = AdapterLaunches()
        _binding!!.bindAdapter(adapterLaunches = adapterLaunches)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                items.collectLatest {
                    adapterLaunches.submitData(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapterLaunches.loadStateFlow.collect {
                    binding?.apply {
                        prependProgress.isVisible = it.source.prepend is LoadState.Loading
                        appendProgress.isVisible = it.source.prepend is LoadState.Loading
                    }
                }
            }
        }

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.escapeButton?.setOnClickListener {
            findNavController().navigate(R.id.action_launchRecyclerFragment_to_launchGeneralInfoFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun LaunchRecyclerFragmentBinding.bindAdapter(adapterLaunches: AdapterLaunches) {
        recyclerView.adapter = adapterLaunches
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        val decoration = DividerItemDecoration(
            recyclerView.context,
            DividerItemDecoration.VERTICAL
        )
        recyclerView.addItemDecoration(decoration)
    }
}