package com.jzarsuelo.android.sunshine.data

import com.chibatching.kotpref.KotprefModel
import com.jzarsuelo.android.sunshine.BuildConfig
import com.jzarsuelo.android.sunshine.R

object Preferences : KotprefModel() {

    override val kotprefName: String = BuildConfig.APPLICATION_ID + "_preferences"

    var city by stringPref(key = R.string.pref_key_city)

    var unit by stringPref(key = R.string.pref_key_unit)
}