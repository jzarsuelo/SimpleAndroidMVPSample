package com.jzarsuelo.android.sunshine.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.jzarsuelo.android.sunshine.db.dao.CityDao
import com.jzarsuelo.android.sunshine.db.entity.CityEntity

@Database(entities = [CityEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

}