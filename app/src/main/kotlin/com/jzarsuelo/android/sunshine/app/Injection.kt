package com.jzarsuelo.android.sunshine.app

import com.jzarsuelo.android.sunshine.api.OpenWeatherMapService
import com.jzarsuelo.android.sunshine.data.ForecastRepository
import com.jzarsuelo.android.sunshine.data.remote.ForecastRemoteDataSource

/**
 * Use for injecting dependencies
 */
object Injection {

    private val openWeatherMapService = OpenWeatherMapService.instance

    fun forecastRepository(): ForecastRepository {
        return ForecastRepository.getInstance(
                ForecastRemoteDataSource.getInstance(openWeatherMapService)
        )
    }
}