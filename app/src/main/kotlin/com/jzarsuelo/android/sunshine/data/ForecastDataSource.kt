package com.jzarsuelo.android.sunshine.data

interface ForecastDataSource {

    interface ForecastsCallback : BaseCallback<ForecastResponse>
    interface ForecastSaveLocalCallback : BaseLocalCallback<Unit>
    interface ForecastQueryCallback : BaseLocalCallback<ForecastResponse>

    fun requestData(city: String, unit: String, days: Int, callback: ForecastsCallback)
    fun saveData(forecastResponse: ForecastResponse, callback: ForecastSaveLocalCallback)
    fun queryData(callback: ForecastQueryCallback)
}