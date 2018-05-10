package com.jzarsuelo.android.sunshine.data.local

import com.jzarsuelo.android.sunshine.app.AppExecutors
import com.jzarsuelo.android.sunshine.data.City
import com.jzarsuelo.android.sunshine.data.ForecastDataSource
import com.jzarsuelo.android.sunshine.data.ForecastResponse
import com.jzarsuelo.android.sunshine.db.AppDatabase
import com.jzarsuelo.android.sunshine.db.entity.CityEntity

class ForecastLocalDataSource(
        val db: AppDatabase,
        val appExecutors: AppExecutors
) : ForecastDataSource {

    override fun requestData(city: String, days: Int, callback: ForecastDataSource.ForecastsCallback) {
        // Do nothing. This is handled by [ForecastRemoteDataSource]
    }

    override fun saveData(forecastResponse: ForecastResponse, callback: ForecastDataSource.ForecastLocalCallback) {
        appExecutors.diskIO.execute {
            val cityEntity = CityEntity(forecastResponse.city.name, forecastResponse.city.country)
            val cityDao = db.cityDao()
            cityDao.insert(cityEntity)

            appExecutors.mainThread.execute{
                callback.onSuccess()
            }
        }
    }

    override fun queryData(callback: ForecastDataSource.ForecastLocalCallback) {
        appExecutors.diskIO.execute{
            val cityDao = db.cityDao()
            val cityEntity = cityDao.query()

            appExecutors.mainThread.execute {
                callback.onCityLoaded(City(cityEntity.name, cityEntity.country))
            }
        }
    }

    companion object {
        private var instance: ForecastLocalDataSource? = null

        fun getInstance(
                db: AppDatabase,
                executors: AppExecutors
        ): ForecastDataSource {
            return instance ?: ForecastLocalDataSource(db, executors).apply { instance = this }
        }
    }
}