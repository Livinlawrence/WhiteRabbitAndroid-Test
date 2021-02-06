package com.livin.androidtest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.livin.androidtest.databinding.HomeFragmentBinding
import com.livin.androidtest.utils.DataUtil.manipulateHomeItems
import com.livin.androidtest.utils.Resource
import com.livin.androidtest.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeItemsFragment : Fragment() {

    private var binding: HomeFragmentBinding by autoCleared()
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeItemsAdapter: HomeItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        homeItemsAdapter = HomeItemsAdapter {

        }
        binding.charactersRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeItemsAdapter
        }
    }

    private fun setupObservers() {
        viewModel.homeItems.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    resource?.let { repoData ->
                        //Doing this logic here coz of the limited time, this should have been done at repo level
                        repoData.data?.let { homeItemsList ->
                            homeItemsAdapter.setItems(manipulateHomeItems(homeItemsList))
                        }
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }
}
