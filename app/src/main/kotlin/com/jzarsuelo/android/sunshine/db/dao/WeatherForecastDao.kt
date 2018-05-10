package com.jzarsuelo.android.sunshine.db.dao

import android.arch.persistence.room.*
import com.jzarsuelo.android.sunshine.db.entity.ForecastEntity
import com.jzarsuelo.android.sunshine.db.entity.WeatherEntity
import com.jzarsuelo.android.sunshine.db.entity.WeatherForecast

@Dao
abstract class WeatherForecastDao {

    @Query("SELECT * FROM forecast")
    abstract fun query(): List<WeatherForecast>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertForecast(forecastEntity: ForecastEntity)

    @Query("DELETE FROM forecast")
    abstract fun deleteForecast()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWeatherList(weatherEntityList: List<WeatherEntity>)

    @Transaction
    open fun insertWeatherForecast(weatherForecastList: List<WeatherForecast>) {
        for (weatherForecast in weatherForecastList) {
            insertForecast(weatherForecast.forecastEntity)
            insertWeatherList(weatherForecast.weatherEntityList)
        }
    }
}