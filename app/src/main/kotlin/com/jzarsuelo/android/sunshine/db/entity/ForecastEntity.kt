package com.jzarsuelo.android.sunshine.db.entity

import android.arch.persistence.room.*
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

open class WeatherForecast (
        @Embedded val forecastEntity: ForecastEntity
) {
        constructor(forecastEntity: ForecastEntity, weatherEntityList: List<WeatherEntity>) : this(forecastEntity) {
                this.weatherEntityList = weatherEntityList
        }

        @Relation(parentColumn = "date_time", entityColumn = "date_time")
        var weatherEntityList: List<WeatherEntity> = arrayListOf()
}