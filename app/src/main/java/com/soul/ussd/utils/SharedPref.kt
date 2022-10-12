package com.soul.ussd.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.soul.ussd.utils.Constants.APP_PREFS_NAME

class SharedPref(context: Context) {

    private var preferences: SharedPreferences =
        context.getSharedPreferences(APP_PREFS_NAME, MODE_PRIVATE)

    private lateinit var editor: SharedPreferences.Editor

    fun setLang(lang: String) {
        editor = preferences.edit()
        editor.putString("LANG", lang)
        editor.apply()
    }

    fun getLang() = preferences.getString("LANG", "ru")

    fun isLangSelected(isSelected: Boolean) {
        editor = preferences.edit()
        editor.putBoolean("IS_SELECTED", isSelected)
        editor.apply()
    }

    fun getLangSelected() = preferences.getBoolean("IS_SELECTED", false)

}
