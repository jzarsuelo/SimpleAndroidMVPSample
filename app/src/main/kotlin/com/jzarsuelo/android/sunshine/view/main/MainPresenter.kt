package com.jzarsuelo.android.sunshine.view.main

import com.jzarsuelo.android.sunshine.data.City
import com.jzarsuelo.android.sunshine.data.ForecastDataSource
import com.jzarsuelo.android.sunshine.data.ForecastRepository
import com.jzarsuelo.android.sunshine.data.ForecastResponse

class MainPresenter(
        private val view: MainContract.View,
        private val repository: ForecastRepository
) : MainContract.Presenter {

    private val NO_OF_DAYS = 14

    private val saveLocalCallback = object: ForecastDataSource.ForecastLocalCallback {
        override fun onCityLoaded(city: City) {
        }

        override fun onSuccess() {
        }

        override fun onFailed() {
        }

    }

    private val retrieveLocalCallback = object: ForecastDataSource.ForecastLocalCallback {
        override fun onCityLoaded(city: City) {
        }

        override fun onSuccess() {
        }

        override fun onFailed() {
        }

    }

    override fun onRequestData() {
        repository.requestData("melbourne", NO_OF_DAYS, object: ForecastDataSource.ForecastsCallback {
            override fun noInternetConnection() {
                view.showErrorNoInternetConnection()
            }

            override fun apiKeyNotFound() {
                view.showErrorApiKeyNotFound()
            }

            override fun cityNotFound() {
                view.showErrorCityNotFound()
            }

            override fun onSuccess(data: ForecastResponse) {
                view.showData(data.list, data.city.name, data.city.country)
                repository.saveData(data, saveLocalCallback)
            }

        })
    }

    override fun onStart() {
    }
}