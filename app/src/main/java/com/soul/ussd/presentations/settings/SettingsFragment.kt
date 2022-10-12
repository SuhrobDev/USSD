package com.soul.ussd.presentations.settings

import android.content.res.ColorStateList
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.soul.ussd.R
import com.soul.ussd.databinding.FragmentSettingsBinding
import com.soul.ussd.presentations.BaseFragment
import com.soul.ussd.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {
    private val viewModel: SettingsViewModel by viewModels()

    @Inject
    lateinit var shared: SharedPref
    override fun onViewCreate() {

        binding.apply {
            if (shared.getLang().equals("ru")) {
                changeLang.text = "Выбрать язык:"
            } else {
                changeLang.text = "Tilni tanlang:"
            }
            uzLatin.setOnClickListener {
                shared.setLang("oz")
                navController.popBackStack()
            }
            uzRussian.setOnClickListener {
                shared.setLang("ru")
                navController.popBackStack()
            }
        }
        getColors()
    }

    private fun getColors() {
        binding.apply {
            uzLatin.backgroundTintList = ColorStateList.valueOf(
                ResourcesCompat.getColor(
                    requireActivity().resources,
                    requireArguments().getInt("BASE_COLOR", R.color.colorUzmobile),
                    requireActivity().theme
                )
            )
            uzRussian.backgroundTintList = ColorStateList.valueOf(
                ResourcesCompat.getColor(
                    requireActivity().resources,
                    requireArguments().getInt("BASE_COLOR", R.color.colorUzmobile),
                    requireActivity().theme
                )
            )
        }

    }

}