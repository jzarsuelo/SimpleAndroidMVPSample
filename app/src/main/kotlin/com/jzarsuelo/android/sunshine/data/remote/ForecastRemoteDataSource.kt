package com.jzarsuelo.android.sunshine.data.remote

import com.jzarsuelo.android.sunshine.api.OpenWeatherMapService
import com.jzarsuelo.android.sunshine.data.ForecastDataSource
import com.jzarsuelo.android.sunshine.data.ForecastResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastRemoteDataSource(
        private val apiService: OpenWeatherMapService
) : ForecastDataSource {

    override fun requestData(city: String, callback: ForecastDataSource.ForecastsCallback) {
        apiService.getForecast5days3hours(city).enqueue(object: Callback<ForecastResponse>{

            override fun onFailure(call: Call<ForecastResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<ForecastResponse>?, response: Response<ForecastResponse>) {
                response.body()?.let {
                    callback.onSuccess(it)
                }
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