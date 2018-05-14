package com.jzarsuelo.android.sunshine.view

import android.content.Context
import android.preference.EditTextPreference
import android.util.AttributeSet

class SunshineEditTextPreference : EditTextPreference {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun getSummary() = text!!

}