package com.jzarsuelo.android.sunshine.data.local

import com.jzarsuelo.android.sunshine.app.AppExecutors
import com.jzarsuelo.android.sunshine.data.ForecastDataSource
import com.jzarsuelo.android.sunshine.data.ForecastResponse
import com.jzarsuelo.android.sunshine.db.AppDatabase
import com.jzarsuelo.android.sunshine.db.entity.CityEntity
import com.jzarsuelo.android.sunshine.db.entity.ForecastEntity
import com.jzarsuelo.android.sunshine.db.entity.WeatherEntity
import com.jzarsuelo.android.sunshine.db.entity.WeatherForecast

class ForecastLocalDataSource(
        private val db: AppDatabase,
        private val appExecutors: AppExecutors
) : ForecastDataSource {

    override fun requestData(city: String, days: Int, callback: ForecastDataSource.ForecastsCallback) {
        // Do nothing. This is handled by [ForecastRemoteDataSource]
    }

    override fun saveData(forecastResponse: ForecastResponse, callback: ForecastDataSource.ForecastLocalCallback) {
        appExecutors.diskIO.execute {

            val cityEntity = CityEntity(forecastResponse.city.name, forecastResponse.city.country)
            val cityDao = db.cityDao()
            cityDao.delete()
            cityDao.insert(cityEntity)

            val weatherForecastDao = db.weatherForecastDao()
            weatherForecastDao.deleteForecast()

            val weatherForecastList = arrayListOf<WeatherForecast>()
            for (forecast in forecastResponse.list) {
                val forecastEntity = ForecastEntity(forecast.dateTime, forecast.temperature)
                val weatherEntityList = arrayListOf<WeatherEntity>()

                for (weather in forecast.weathers) {
                    weatherEntityList.add(
                            WeatherEntity(
                                    weather.id,
                                    weather.main,
                                    weather.description,
                                    weather.icon,
                                    forecast.dateTime
                            )
                    )
                }
                weatherForecastList.add(WeatherForecast(forecastEntity, weatherEntityList))
            }
            weatherForecastDao.insertWeatherForecast(weatherForecastList)

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