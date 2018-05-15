package com.jzarsuelo.android.sunshine.data

class ForecastRepository(
        private val remoteDataSource: ForecastDataSource,
        private val localDataSource: ForecastDataSource
) : ForecastDataSource {

    override fun saveData(forecastResponse: ForecastResponse, callback: ForecastDataSource.ForecastSaveLocalCallback) {
        localDataSource.saveData(forecastResponse, callback)
    }

    override fun queryData(callback: ForecastDataSource.ForecastQueryCallback) {
        localDataSource.queryData(callback)
    }

    override fun requestData(city: String, unit: String, days: Int, callback: ForecastDataSource.ForecastsCallback) {
        remoteDataSource.requestData(city, unit, days, callback)
    }

    companion object {
        private var instance: ForecastRepository? = null

        fun getInstance(remoteDataSource: ForecastDataSource,
                        localDataSource: ForecastDataSource) : ForecastRepository {
            return instance ?: ForecastRepository(remoteDataSource, localDataSource).apply { instance = this }
        }
    }
}