package com.jzarsuelo.android.sunshine.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForecastResponse(private val list: List<Forecast>)

data class  Forecast(
        @SerializedName("dt") @Expose private val dateTime: Long,
        private val main: Main,
        @SerializedName("weather") @Expose private val weathers: List<Weather>,
        @SerializedName("dt_txt") @Expose private val dateTimeText: String,
        private val city: City
)

data class Main(
        private val temp: Double,
        @SerializedName("temp_min") private val tempMin: Double,
        @SerializedName("temp_max") @Expose private val tempMax: Double
)

data class Weather(
        private val id: Int,
        private val main: String,
        private val description: String,
        private val icon: String
)

data class City(
        private val name: String,
        private val country: String
)