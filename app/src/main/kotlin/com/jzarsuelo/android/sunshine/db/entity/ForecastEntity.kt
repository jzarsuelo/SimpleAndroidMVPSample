package com.jzarsuelo.android.sunshine.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.jzarsuelo.android.sunshine.data.Temperature
import com.jzarsuelo.android.sunshine.data.Forecast

/**
 * Entity class for [Forecast]
 */
@Entity(tableName = "forecast")
data class ForecastEntity(
        @PrimaryKey @ColumnInfo(name = "date_time") val dateTime: Long,
        @Embedded val temperature: Temperature
)

data class WeatherForecast (
        @Embedded val forecastEntity: ForecastEntity,
        val weatherEntityList: List<WeatherEntity>
)