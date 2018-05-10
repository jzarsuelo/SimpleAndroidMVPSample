package com.jzarsuelo.android.sunshine.db.entity

import android.arch.persistence.room.Entity

@Entity(tableName = "city", primaryKeys = ["name", "country"])
data class CityEntity(
        val name: String,
        val country: String
)