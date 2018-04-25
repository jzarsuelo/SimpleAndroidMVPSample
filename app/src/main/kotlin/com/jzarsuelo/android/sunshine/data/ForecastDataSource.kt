package com.jzarsuelo.android.sunshine.data

interface ForecastDataSource {

    interface ForecastsCallback : BaseCallback<ForecastResponse>

    fun requestData(city: String, callback: ForecastsCallback)
}