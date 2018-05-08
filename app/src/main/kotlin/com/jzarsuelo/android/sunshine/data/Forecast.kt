package com.jzarsuelo.android.sunshine.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForecastResponse(
        val city: City,
        val cnt: Int,
        val list: List<Forecast>
)

data class  Forecast(
        @SerializedName("dt") @Expose val dateTime: Long,
        @SerializedName("temp") @Expose val temperature: Temperature,
        @SerializedName("weather") @Expose val weathers: List<Weather>
)

data class Temperature(
        val day: Double,
        val min: Double,
        val max: Double,
        val night: Double
)

data class Weather(
        val id: Int,
        val main: String,
        val description: String,
        val icon: String
)

@Entity(tableName = "city")
data class City(
        @PrimaryKey(autoGenerate = true) val uid: Long,
        val name: String,
        val country: String
) {
    @Ignore constructor(name: String, country: String) : this(0, name, country)
}