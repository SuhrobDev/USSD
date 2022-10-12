package com.soul.ussd.presentations.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.soul.ussd.R
import com.soul.ussd.databinding.MainFragmentBinding
import com.soul.ussd.presentations.BaseFragment
import com.soul.ussd.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<MainFragmentBinding>(MainFragmentBinding::inflate) {
    private val viewModel: MainViewModel by viewModels(
        ownerProducer = { requireActivity() }
    )
    private val adapter by lazy {
        TopMenuOperatorAdapter()
    }

    val bundle by lazy {
        Bundle()
    }

    @Inject
    lateinit var shared: SharedPref

    override fun onViewCreate() {

        setAllViews()
        setAllDataToViews()
        viewModel.topMenuOperatorLiveData.observe(viewLifecycleOwner) {
            adapter.setNewData(it)
        }
        binding.services.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_servicesFragment)
        }
        binding.ussdCodes.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_ussdCodesFragment)
        }
        binding.settings.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_settingsFragment, bundle)
        }
    }

    private fun setAllDataToViews() {
        viewModel.getAllOperators()
    }

    private fun checkPermissions(): Boolean {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            requireContext(), Manifest.permission.CALL_PHONE
        )
    }

    private fun getPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(
                Manifest.permission.CALL_PHONE
            ),
            1003
        )
    }

    private fun setAllViews() {
        binding.apply {
            operatorViewPager.adapter = adapter
            operatorViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    selectColor(position)
                    viewModel.viewpagerPosition = position
                    loadMainMenu(position)
                    when (position + 1) {
                        1 -> viewModel.operatorCode = 4
                        2 -> viewModel.operatorCode = 1
                        3 -> viewModel.operatorCode = 2
                        4 -> viewModel.operatorCode = 3
                    }
                }
            })
            currentlyBalance.setOnClickListener {
                var code = ""
                when (viewModel.viewpagerPosition) {
                    1 -> code = "*100#"
                    0 -> code = "*107#"
                    3 -> code = "*100#"
                    2 -> code = "*102#"
                }
                if (checkPermissions()) {
                    val intent = Intent(Intent.ACTION_CALL)
                    intent.data = Uri.parse("tel:" + Uri.encode(code))
                    startActivity(intent)
                } else {
                    getPermission()
                }
            }
            lastBalance.setOnClickListener {
                var code = ""
                when (viewModel.viewpagerPosition) {
                    0 -> code = "*100*2#"
                    1 -> code = "*102#"
                    2 -> code = "*103#"
                    3 -> code = "*107#"
                }
                if (checkPermissions()) {
                    val intent = Intent(Intent.ACTION_CALL)
                    intent.data = Uri.parse("tel:" + Uri.encode(code))
                    startActivity(intent)
                } else {
                    getPermission()
                }
            }
        }
        currentLanguage()
    }

    fun selectColor(position: Int) {
        when (position) {
            1 -> {
                getColors(R.color.colorMobiuz)
            }
            0 -> {
                getColors(R.color.colorUzmobile)
            }
            3 -> {
                getColors(R.color.colorUcell)
            }
            2 -> {
                getColors(R.color.colorBeline)
            }
            4 -> {
                getColors(R.color.colorPerfectum)
            }
        }
    }

    private fun getColors(color: Int) {
        bundle.putInt("BASE_COLOR", color)
        viewModel.onColorChange.value = color
        requireActivity().window.statusBarColor = ResourcesCompat.getColor(
            resources,
            color,
            requireActivity().theme
        )
        requireActivity().window.navigationBarColor = ResourcesCompat.getColor(
            resources,
            color,
            requireActivity().theme
        )
        binding.mainMenuBack.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                color,
                requireActivity().theme
            )
        )
        binding.internetPacketsIcon.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                color,
                requireActivity().theme
            )
        )
        binding.ussdCodesIcon.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                color,
                requireActivity().theme
            )
        )
        binding.tariffPlanIcon.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                color,
                requireActivity().theme
            )
        )
        binding.servicesIcon.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                color,
                requireActivity().theme
            )
        )

        binding.minutesIcon.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                color,
                requireActivity().theme
            )
        )
        binding.smsPacketsIcon.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                color,
                requireActivity().theme
            )
        )

        binding.smsPacketsIcon.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                color,
                requireActivity().theme
            )
        )
        binding.frame1.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                color,
                requireActivity().theme
            )
        )
        binding.frame2.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                color,
                requireActivity().theme
            )
        )
    }

    fun loadMainMenu(ind: Int) {
        (binding.mainMenu.getChildAt(0) as ImageView).setImageResource(R.drawable.uztelecom_gray)
        (binding.mainMenu.getChildAt(1) as ImageView).setImageResource(R.drawable.mobiuz_gray)
        (binding.mainMenu.getChildAt(2) as ImageView).setImageResource(R.drawable.beeline_gray)
        (binding.mainMenu.getChildAt(3) as ImageView).setImageResource(R.drawable.ucell_gray)

        when (ind) {
            0 -> {
                (binding.mainMenu.getChildAt(ind) as ImageView).setImageResource(R.drawable.uztelecom_click)
            }

            1 -> {
                (binding.mainMenu.getChildAt(ind) as ImageView).setImageResource(R.drawable.mobiuz_click)
            }

            2 -> {
                (binding.mainMenu.getChildAt(ind) as ImageView).setImageResource(R.drawable.beeline_click)
            }

            3 -> {
                (binding.mainMenu.getChildAt(ind) as ImageView).setImageResource(R.drawable.ucell_click)
            }
        }
        for (i in 0 until binding.mainMenu.childCount) {
            binding.mainMenu.getChildAt(i).setOnClickListener {
                for (j in 0 until binding.mainMenu.childCount) {
                    //hammasi oddiyga utkaziladi
                    when (j) {
                        0 -> {
                            (binding.mainMenu.getChildAt(j) as ImageView).setImageResource(R.drawable.uztelecom_gray)
                        }

                        1 -> {
                            (binding.mainMenu.getChildAt(j) as ImageView).setImageResource(R.drawable.mobiuz_gray)
                        }

                        2 -> {
                            (binding.mainMenu.getChildAt(j) as ImageView).setImageResource(R.drawable.beeline_gray)
                        }

                        3 -> {
                            (binding.mainMenu.getChildAt(j) as ImageView).setImageResource(R.drawable.ucell_gray)
                        }
                    }
                }
                when (i) {
                    0 -> {
                        (binding.mainMenu.getChildAt(i) as ImageView).setImageResource(R.drawable.uztelecom_click)
                    }

                    1 -> {
                        (binding.mainMenu.getChildAt(i) as ImageView).setImageResource(R.drawable.mobiuz_click)
                    }

                    2 -> {
                        (binding.mainMenu.getChildAt(i) as ImageView).setImageResource(R.drawable.beeline_click)
                    }

                    3 -> {
                        (binding.mainMenu.getChildAt(i) as ImageView).setImageResource(R.drawable.ucell_click)
                    }
                }
                //bosilgani buyaladi
                selectPage(i)
            }
        }
    }

    private fun selectPage(i: Int) {
        binding.operatorViewPager.setCurrentItem(i, true)
    }


    private fun currentLanguage() {
        binding.apply {
            when (shared.getLang()) {
                "oz" -> {
                    internetPacketsText.text = "Internet paketlar"
                    ussdCodesText.text = "USSD kodlar"
                    tariffPlanText.text = "Tariflar"
                    servicesText.text = "Xizmatlar"
                    minutesText.text = "Daqiqa to'plamlari"
                    smsPacketsText.text = "SMS to'plamlari"
                    currentlyBalanceText.text = "Umumiy balans"
                    lastBalanceText.text = "Internet balansi"
                }
                "ru" -> {
                    internetPacketsText.text = "Интернет-пакеты"
                    ussdCodesText.text = "USSD-коды"
                    tariffPlanText.text = "Тарифы"
                    servicesText.text = "Услуги"
                    minutesText.text = "Пакеты минут"
                    smsPacketsText.text = "SMS-пакеты"
                    currentlyBalanceText.text = "Общий баланс"
                    lastBalanceText.text = "Баланс трафика"
                }
            }
        }
    }
}