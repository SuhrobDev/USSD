package com.soul.ussd.presentations.ussd_codes

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.soul.ussd.databinding.FragmentUssdCodesBinding
import dagger.hilt.android.AndroidEntryPoint
import com.soul.ussd.presentations.BaseFragment

@AndroidEntryPoint
class UssdCodesFragment :
    BaseFragment<FragmentUssdCodesBinding>(FragmentUssdCodesBinding::inflate) {
    val viewModel: UssdCodesViewModel by viewModels()
    val adapter by lazy {
        UssdCodesAdapter()
    }

    override fun onViewCreate() {

        binding.apply {
            serviceList.layoutManager = GridLayoutManager(context, 2)
            serviceList.adapter = adapter
            refresh.setOnRefreshListener {
                viewModel.getUssdCodesResponse("ru", 1, 1)
            }
        }

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            binding.refresh.isRefreshing = it
        }
        viewModel.ussdCodesLiveData.observe(viewLifecycleOwner) {
            it?.let {
                adapter.setNewData(it)
            }
        }

        viewModel.getUssdCodesResponse("ru", 1, 1)
    }

}