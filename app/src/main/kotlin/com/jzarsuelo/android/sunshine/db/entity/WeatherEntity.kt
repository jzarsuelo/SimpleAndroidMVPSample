package com.jzarsuelo.android.sunshine.db.entity

import android.arch.persistence.room.*
import com.jzarsuelo.android.sunshine.data.Weather

/**
 * Entity class for [Weather]
 */
@Entity(
        tableName = "weather",
        foreignKeys = [(ForeignKey(
                entity = ForecastEntity::class,
                parentColumns = ["date_time"],
                childColumns = ["date_time"],
                onDelete = ForeignKey.CASCADE
        ))]
)
data class WeatherEntity(
        @PrimaryKey(autoGenerate = true) val uid: Long,
        val id: Int,
        val main: String,
        val description: String,
        val icon: String,
        @ColumnInfo(name = "date_time") val dateTime: Long
) {
        @Ignore
        constructor(id: Int, main: String, description: String, icon: String, dateTime: Long) : this (0, id, main, description, icon, dateTime)
}