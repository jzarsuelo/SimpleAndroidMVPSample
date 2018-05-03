package com.jzarsuelo.android.sunshine.view.main

import com.jzarsuelo.android.sunshine.data.ForecastDataSource
import com.jzarsuelo.android.sunshine.data.ForecastRepository
import com.jzarsuelo.android.sunshine.data.ForecastResponse

class MainPresenter(
        private val view: MainContract.View,
        private val repository: ForecastRepository
) : MainContract.Presenter {

    private val NO_OF_DAYS = 14

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
            }

        })
    }

    override fun onStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}