package com.jzarsuelo.android.sunshine.utils

import android.text.format.DateFormat
import com.jzarsuelo.android.sunshine.R
import com.jzarsuelo.android.sunshine.data.Weather

fun Long.getFormattedDate(format: String) : String =
        DateFormat.format(format, this * 1000).toString()

fun Weather.getIcon() : Int {
    return when (this.id) {
        in 200..232 -> R.drawable.ic_storm
        in 300..321 -> R.drawable.ic_light_rain
        in 500..504,
        in 520..531 -> R.drawable.ic_rain
        511,
        in 600..622 -> R.drawable.ic_snow
        in 701..761 -> R.drawable.ic_fog
        761 or 781 -> R.drawable.ic_storm
        800 -> R.drawable.ic_clear
        801 -> R.drawable.ic_light_clouds
        in 802..804 -> R.drawable.ic_cloudy
        else -> -1
    }
}
