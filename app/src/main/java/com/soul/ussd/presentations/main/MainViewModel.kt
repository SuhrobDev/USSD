package com.soul.ussd.presentations.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soul.ussd.R
import com.soul.ussd.domain.main.models.TopMenuOperatorModel
import com.soul.ussd.utils.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val _shared: SharedPref
) : ViewModel() {
    var operatorCode: Int = 1
    var viewpagerPosition: Int = 1
    val onColorChange = MutableLiveData<Int>()
    private val _topMenuOperatorLiveData = MutableLiveData<List<TopMenuOperatorModel>>()
    val topMenuOperatorLiveData: LiveData<List<TopMenuOperatorModel>> get() = _topMenuOperatorLiveData

    fun getAllOperators() {
        viewModelScope.launch {
            val data = ArrayList<TopMenuOperatorModel>()
            when (_shared.getLang()) {
                "oz" -> {
                    data.add(
                        TopMenuOperatorModel(
                            "Uztelecom",
                            R.drawable.uztelecom_gradient,
                            R.drawable.uztelecom_img
                        )
                    )
                    data.add(
                        TopMenuOperatorModel(
                            "Mobiuz",
                            R.drawable.mobiuz_gradient,
                            R.drawable.mobiuz_img
                        )
                    )
                    data.add(
                        TopMenuOperatorModel(
                            "Beeline",
                            R.drawable.beeline_gradient,
                            R.drawable.beeline_img
                        )
                    )
                    data.add(
                        TopMenuOperatorModel(
                            "Ucell",
                            R.drawable.ucell_gradient,
                            R.drawable.ucell_img
                        )
                    )
                }
                "uz" -> {
                    data.add(
                        TopMenuOperatorModel(
                            "Uztelecom",
                            R.drawable.uztelecom_gradient,
                            R.drawable.uztelecom_img
                        )
                    )
                    data.add(
                        TopMenuOperatorModel(
                            "Mobiuz",
                            R.drawable.mobiuz_gradient,
                            R.drawable.mobiuz_img
                        )
                    )
                    data.add(
                        TopMenuOperatorModel(
                            "Beeline",
                            R.drawable.beeline_gradient,
                            R.drawable.beeline_img
                        )
                    )
                    data.add(
                        TopMenuOperatorModel(
                            "Ucell",
                            R.drawable.ucell_gradient,
                            R.drawable.ucell_img
                        )
                    )
                }
                "ru" -> {
                    data.add(
                        TopMenuOperatorModel(
                            "Uztelecom",
                            R.drawable.uztelecom_gradient,
                            R.drawable.uztelecom_img
                        )
                    )
                    data.add(
                        TopMenuOperatorModel(
                            "Mobiuz",
                            R.drawable.mobiuz_gradient,
                            R.drawable.mobiuz_img
                        )
                    )
                    data.add(
                        TopMenuOperatorModel(
                            "Beeline",
                            R.drawable.beeline_gradient,
                            R.drawable.beeline_img
                        )
                    )
                    data.add(
                        TopMenuOperatorModel(
                            "Ucell",
                            R.drawable.ucell_gradient,
                            R.drawable.ucell_img
                        )
                    )
                }
                "kr" -> {
                    data.add(
                        TopMenuOperatorModel(
                            "Uztelecom",
                            R.drawable.uztelecom_gradient,
                            R.drawable.uztelecom_img
                        )
                    )
                    data.add(
                        TopMenuOperatorModel(
                            "Mobiuz",
                            R.drawable.mobiuz_gradient,
                            R.drawable.mobiuz_img
                        )
                    )
                    data.add(
                        TopMenuOperatorModel(
                            "Beeline",
                            R.drawable.beeline_gradient,
                            R.drawable.beeline_img
                        )
                    )
                    data.add(
                        TopMenuOperatorModel(
                            "Ucell",
                            R.drawable.ucell_gradient,
                            R.drawable.ucell_img
                        )
                    )
                }
            }
            _topMenuOperatorLiveData.value = data
        }
    }
}
