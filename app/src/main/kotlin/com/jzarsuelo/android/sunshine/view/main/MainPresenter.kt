package com.jzarsuelo.android.sunshine.view.main

import com.jzarsuelo.android.sunshine.data.ForecastDataSource
import com.jzarsuelo.android.sunshine.data.ForecastRepository
import com.jzarsuelo.android.sunshine.data.ForecastResponse

class MainPresenter(
        private val view: MainContract.View,
        private val repository: ForecastRepository
) : MainContract.Presenter {

    override fun onRequestData() {
        repository.requestData("melbourne", 14, object: ForecastDataSource.ForecastsCallback{
            override fun onSuccess(data: ForecastResponse) {
                view.showData(data.list, data.city.name, data.city.country)
            }

            override fun onFailure(message: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    override fun onStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}