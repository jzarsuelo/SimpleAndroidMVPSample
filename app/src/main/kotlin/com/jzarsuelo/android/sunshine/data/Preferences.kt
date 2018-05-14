package com.jzarsuelo.android.sunshine.data

import com.chibatching.kotpref.KotprefModel

object Preferences : KotprefModel() {

    var city by stringPref()

    var unit by stringPref()
}