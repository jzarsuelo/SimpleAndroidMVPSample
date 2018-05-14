package com.jzarsuelo.android.sunshine.data.local

import com.jzarsuelo.android.sunshine.data.PreferenceDataSource
import com.jzarsuelo.android.sunshine.data.Preferences

class PreferenceLocalDataSource : PreferenceDataSource {

    override var city: String
        get() = Preferences.city
        set(value) { Preferences.city = value }

    override var unit: String
        get() = Preferences.unit
        set(value) { Preferences.unit = value }

    companion object {
        val instance: PreferenceLocalDataSource by lazy {
            PreferenceLocalDataSource()
        }
    }
}