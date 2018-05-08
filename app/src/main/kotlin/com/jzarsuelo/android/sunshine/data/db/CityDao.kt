package com.jzarsuelo.android.sunshine.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.jzarsuelo.android.sunshine.data.City

@Dao
interface CityDao {

    @Query("SELECT * FROM city LIMIT 1")
    fun getCity() : City

    @Insert
    fun insert(city: City)

}