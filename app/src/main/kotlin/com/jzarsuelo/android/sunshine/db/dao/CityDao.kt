package com.jzarsuelo.android.sunshine.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.jzarsuelo.android.sunshine.db.entity.CityEntity

@Dao
interface CityDao {

    @Query("SELECT * FROM city LIMIT 1")
    fun query() : CityEntity

    @Query("DELETE FROM city")
    fun delete()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cityEntity: CityEntity)

}