package com.jzarsuelo.android.sunshine.app

import android.arch.persistence.room.Room
import com.jzarsuelo.android.sunshine.api.OpenWeatherMapService
import com.jzarsuelo.android.sunshine.data.ForecastRepository
import com.jzarsuelo.android.sunshine.data.local.ForecastLocalDataSource
import com.jzarsuelo.android.sunshine.db.AppDatabase
import com.jzarsuelo.android.sunshine.data.remote.ForecastRemoteDataSource

/**
 * Use for injecting dependencies
 */
object Injection {

    private val openWeatherMapService = OpenWeatherMapService.instance
    private val db = Room.databaseBuilder(SunshineApp.getAppContext(), AppDatabase::class.java, "sunshine.db").build()
    private val appExecutors = AppExecutors()

    fun forecastRepository(): ForecastRepository {
        return ForecastRepository.getInstance(
                ForecastRemoteDataSource.getInstance(openWeatherMapService),
                ForecastLocalDataSource.getInstance(db, appExecutors)
        )
    }
}