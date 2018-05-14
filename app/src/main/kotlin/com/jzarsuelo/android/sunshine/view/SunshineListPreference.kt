package com.jzarsuelo.android.sunshine.view

import android.content.Context
import android.preference.ListPreference
import android.util.AttributeSet
import com.jzarsuelo.android.sunshine.R

class SunshineListPreference : ListPreference {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun getSummary() : CharSequence {
        var summary = ""
        context.resources.apply {
            val index = getStringArray(R.array.pref_unit_values).indexOf(value)
            summary = getStringArray(R.array.pref_unit_entries)[index]
        }
        return summary
    }
}