package com.soul.ussd.presentations.ussd_codes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soul.ussd.domain.base.BaseNetworkResult
import com.soul.ussd.domain.ussd_codes.models.UssdCodesModel
import com.soul.ussd.domain.ussd_codes.usecase.UssdCodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UssdCodesViewModel @Inject constructor(
    private val _ussdCodesUseCase: UssdCodesUseCase
) : ViewModel() {

    private val _ussdCodesLiveData = MutableLiveData<List<UssdCodesModel>>()
    val ussdCodesLiveData: LiveData<List<UssdCodesModel>> get() = _ussdCodesLiveData

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> get() = _isLoadingLiveData

    fun getUssdCodesResponse(
        lang: String,
        operator: Int,
        type: Int
    ) {
        viewModelScope.launch {
            _ussdCodesUseCase.getServicesResponse(lang, operator, type).catch {
                //xatolik beriladi
                Log.d("DDDD", "getServicesResponse: catch $this")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { list ->
                            Log.d("DDDD", "getServicesResponse: $list")
                            _ussdCodesLiveData.value = list
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