package com.soul.ussd.presentations.services

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soul.ussd.domain.base.BaseNetworkResult
import com.soul.ussd.domain.services.models.ServicesModel
import com.soul.ussd.domain.services.usecase.ServiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServicesViewModel @Inject constructor(
    private val _serviceUseCase: ServiceUseCase
) : ViewModel() {

    private val _servicesLiveData = MutableLiveData<List<ServicesModel>>()
    val servicesLiveData: LiveData<List<ServicesModel>> get() = _servicesLiveData

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> get() = _isLoadingLiveData

    fun getServicesResponse(
        lang: String,
        operator: Int,
        type: Int
    ) {
        viewModelScope.launch {
            _serviceUseCase.getServicesResponse(lang, operator, type).catch {
                //xatolik beriladi
                Log.d("DDDD", "getServicesResponse: blaaaaaaaaaaa $this")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { list ->
                            Log.d("DDDD", "getServicesResponse: $list")
                            _servicesLiveData.value = list
                        }
                    }
                    is BaseNetworkResult.Error -> {
                        //network error
                        Log.d("DDDD", "getServicesResponse: xatooooooo")
                    }
                    is BaseNetworkResult.Loading -> {
                        //loading
                        result.isLoading?.let {
                            _isLoadingLiveData.value = it
                        }
                    }
                }
            }
        }
    }

}