package com.jzarsuelo.android.sunshine.data.remote

import com.jzarsuelo.android.sunshine.api.ApiCallbackAdapter
import com.jzarsuelo.android.sunshine.api.OpenWeatherMapService
import com.jzarsuelo.android.sunshine.data.ForecastDataSource
import com.jzarsuelo.android.sunshine.data.ForecastResponse
import retrofit2.Response
import java.io.IOException

class ForecastRemoteDataSource(
        private val apiService: OpenWeatherMapService
) : ForecastDataSource {

    override fun saveData(forecastResponse: ForecastResponse, callback: ForecastDataSource.ForecastLocalCallback) {
        // Do nothing. This is handled by [ForecastLocalDataSource]
    }

    override fun queryData(callback: ForecastDataSource.ForecastLocalCallback) {
        // Do nothing. This is handled by [ForecastLocalDataSource]
    }

    override fun requestData(city: String, days: Int, callback: ForecastDataSource.ForecastsCallback) {
        apiService.getForecast(city, days).enqueue(object: ApiCallbackAdapter<ForecastResponse>(){
            override fun unauthenticated(response: Response<*>) {
                callback.apiKeyNotFound()
            }

            override fun clientError(response: Response<*>) {
                callback.cityNotFound()
            }

            override fun success(response: Response<ForecastResponse>) {
                response.body()?.let {
                    callback.onSuccess(it)
                }
            }

            override fun networkError(e: IOException) {
                callback.noInternetConnection()
            }
        })

    }

    companion object {
        private var instance: ForecastRemoteDataSource? = null

        fun getInstance(apiService: OpenWeatherMapService): ForecastRemoteDataSource {
            return instance ?: ForecastRemoteDataSource(apiService).apply { instance = this }
        }
    }
}