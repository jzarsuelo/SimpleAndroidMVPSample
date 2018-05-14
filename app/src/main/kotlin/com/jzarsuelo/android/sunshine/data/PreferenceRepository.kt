package com.jzarsuelo.android.sunshine.data

class PreferenceRepository(
        private val localDataSource: PreferenceDataSource
) : PreferenceDataSource {

    override var city: String
        get() = localDataSource.city
        set(value) { localDataSource.city = value }

    override var unit: String
        get() = localDataSource.unit
        set(value) { localDataSource.unit = value }

    companion object {

        var instance: PreferenceRepository? = null

        fun getInstance(localDataSource: PreferenceDataSource) : PreferenceRepository {
            return instance ?: PreferenceRepository(localDataSource).apply {
                instance = this
            }
        }
    }
}