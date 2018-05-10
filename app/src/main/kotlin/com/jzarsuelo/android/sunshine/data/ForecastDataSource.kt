package com.jzarsuelo.android.sunshine.data

interface ForecastDataSource {

    interface ForecastsCallback : BaseCallback<ForecastResponse>
    interface ForecastLocalCallback : BaseLocalCallback {
        fun onCityLoaded(city: City)
    }

    fun requestData(city: String, days: Int, callback: ForecastsCallback)
    fun saveData(forecastResponse: ForecastResponse, callback: ForecastLocalCallback)
    fun queryData(callback: ForecastLocalCallback)
}