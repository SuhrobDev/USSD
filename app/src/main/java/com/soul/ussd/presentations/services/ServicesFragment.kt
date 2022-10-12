package com.soul.ussd.presentations.services

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.soul.ussd.databinding.FragmentServicesBinding
import com.soul.ussd.presentations.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServicesFragment :
    BaseFragment<FragmentServicesBinding>(FragmentServicesBinding::inflate) {
    val viewModel: ServicesViewModel by viewModels()
    val adapter by lazy {
        ServicesAdapter()
    }

    override fun onViewCreate() {

        binding.apply {
            serviceList.layoutManager = GridLayoutManager(context, 2)
            serviceList.adapter = adapter
            refresh.setOnRefreshListener {
                viewModel.getServicesResponse("ru", 1, 2)
            }
        }


        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            binding.refresh.isRefreshing = it
        }
        viewModel.servicesLiveData.observe(viewLifecycleOwner) {
            it?.let {
                adapter.setNewData(it)
            }
        }
        viewModel.getServicesResponse("ru", 1, 2)
    }

}