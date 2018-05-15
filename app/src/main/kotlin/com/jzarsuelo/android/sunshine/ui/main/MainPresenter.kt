package com.jzarsuelo.android.sunshine.ui.main

import com.jzarsuelo.android.sunshine.data.ForecastDataSource
import com.jzarsuelo.android.sunshine.data.ForecastRepository
import com.jzarsuelo.android.sunshine.data.ForecastResponse
import com.jzarsuelo.android.sunshine.data.PreferenceRepository

class MainPresenter(
        private val view: MainContract.View,
        private val forecastRepository: ForecastRepository,
        private val preferenceRepository: PreferenceRepository
) : MainContract.Presenter {

    private val NO_OF_DAYS = 14

    private val saveLocalCallback = object: ForecastDataSource.ForecastSaveLocalCallback {
        override fun onSuccess(t: Unit) {
        }


        override fun onFailed() {
        }

    }

    private val retrieveLocalCallback = object: ForecastDataSource.ForecastQueryCallback {
        override fun onSuccess(data: ForecastResponse) {
            view.showData(data.list, data.city.name, data.city.country, preferenceRepository.unit)
            view.showSnackBarNoInternetConnection()
        }

        override fun onFailed() {
            view.showErrorNoInternetConnection()
        }
    }

    override fun onRequestData() {
        forecastRepository.requestData(
                preferenceRepository.city,
                preferenceRepository.unit,
                NO_OF_DAYS,
                object: ForecastDataSource.ForecastsCallback {
                    override fun noInternetConnection() {
                        forecastRepository.queryData(retrieveLocalCallback)
                    }

                    override fun apiKeyNotFound() {
                        view.showErrorApiKeyNotFound()
                    }

                    override fun cityNotFound() {
                        view.showErrorCityNotFound()
                    }

                    override fun onSuccess(data: ForecastResponse) {
                        view.showData(data.list, data.city.name, data.city.country, preferenceRepository.unit)
                        forecastRepository.saveData(data, saveLocalCallback)
                    }

                }
        )
    }

    override fun onStart() {
    }
}