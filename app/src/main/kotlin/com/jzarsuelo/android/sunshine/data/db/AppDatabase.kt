package com.jzarsuelo.android.sunshine.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.jzarsuelo.android.sunshine.data.City

@Database(entities = [City::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

}