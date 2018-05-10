package com.jzarsuelo.android.sunshine.data.local

import com.jzarsuelo.android.sunshine.app.AppExecutors
import com.jzarsuelo.android.sunshine.data.*
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

    override fun saveData(forecastResponse: ForecastResponse, callback: ForecastDataSource.ForecastSaveLocalCallback) {
        appExecutors.diskIO.execute {

            val cityEntity = CityEntity(forecastResponse.city.name, forecastResponse.city.country)
            val cityDao = db.cityDao()
            cityDao.delete()
            cityDao.insert(cityEntity)

            val weatherForecastDao = db.weatherForecastDao()
            weatherForecastDao.deleteForecast()

            val weatherForecastList = processForecastResponse(forecastResponse)
            weatherForecastDao.insertWeatherForecast(weatherForecastList)
        }
    }

    override fun queryData(callback: ForecastDataSource.ForecastQueryCallback) {
        appExecutors.diskIO.execute{
            val cityDao = db.cityDao()
            val cityEntity = cityDao.query()
            if (cityEntity == null) {
                appExecutors.mainThread.execute {
                    callback.onFailed()
                }
                return@execute
            }
            val city = City(cityEntity.name, cityEntity.country)

            val weatherForecastDao = db.weatherForecastDao()
            val weatherForecastList = weatherForecastDao.query()
            val forecastList = processWeatherForecast(weatherForecastList)

            val forecastResponse = ForecastResponse(city, forecastList.size, forecastList)

            appExecutors.mainThread.execute {
                if (forecastList.isEmpty()) {
                    callback.onFailed()
                } else {
                    callback.onSuccess(forecastResponse)
                }
            }
        }
    }

    private fun processForecastResponse(forecastResponse: ForecastResponse): List<WeatherForecast> {
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
        return weatherForecastList
    }

    private fun processWeatherForecast(weatherForecastList: List<WeatherForecast>) : List<Forecast> {
        val forecastList = arrayListOf<Forecast>()
        for (weatherForecast in weatherForecastList) {
            val weatherList = arrayListOf<Weather>()
            for (weatherEntity in weatherForecast.weatherEntityList) {
                weatherList.add(
                        Weather(
                                weatherEntity.id,
                                weatherEntity.main,
                                weatherEntity.description,
                                weatherEntity.icon
                        )
                )
            }

            val forecastEntity = weatherForecast.forecastEntity
            val forecast = Forecast(forecastEntity.dateTime, forecastEntity.temperature, weatherList)
            forecastList.add(forecast)
        }
        return forecastList
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