package com.jzarsuelo.android.sunshine.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.jzarsuelo.android.sunshine.data.City
import com.jzarsuelo.android.sunshine.db.dao.CityDao

@Database(entities = [City::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

}