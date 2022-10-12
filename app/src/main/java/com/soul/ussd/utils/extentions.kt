package com.soul.ussd.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

fun View.gone(): View {
    visibility = View.GONE
    return this
}

fun View.invisible(): View {
    visibility = View.INVISIBLE
    return this
}

fun View.visible(): View {
    visibility = View.VISIBLE
    return this
}