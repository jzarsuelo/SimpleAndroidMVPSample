package com.jzarsuelo.android.sunshine.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.jzarsuelo.android.sunshine.db.dao.CityDao
import com.jzarsuelo.android.sunshine.db.dao.WeatherForecastDao
import com.jzarsuelo.android.sunshine.db.entity.CityEntity
import com.jzarsuelo.android.sunshine.db.entity.ForecastEntity
import com.jzarsuelo.android.sunshine.db.entity.WeatherEntity

@Database(entities = [CityEntity::class, ForecastEntity::class, WeatherEntity::class],
        version = 1,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun weatherForecastDao(): WeatherForecastDao

}