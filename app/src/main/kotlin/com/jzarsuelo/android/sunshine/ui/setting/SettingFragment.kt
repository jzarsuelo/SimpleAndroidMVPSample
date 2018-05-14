package com.jzarsuelo.android.sunshine.ui.setting


import android.os.Bundle
import android.preference.PreferenceFragment
import android.support.v4.app.Fragment
import com.jzarsuelo.android.sunshine.R


/**
 * A simple [Fragment] subclass.
 *
 */
class SettingFragment : PreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
    }

    companion object {
        fun newInstance() = SettingFragment()
    }

}
