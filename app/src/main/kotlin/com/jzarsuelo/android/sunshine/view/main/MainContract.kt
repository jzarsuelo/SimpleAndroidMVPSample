package com.jzarsuelo.android.sunshine.view.main

import com.jzarsuelo.android.sunshine.data.Forecast
import com.jzarsuelo.android.sunshine.view.BasePresenter
import com.jzarsuelo.android.sunshine.view.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {
        fun showData(data: List<Forecast>, city: String, country: String)
        fun showErrorApiKeyNotFound()
        fun showErrorCityNotFound()
        fun showErrorNoInternetConnection()
        fun showSnackBarNoInternetConnection()
    }

    interface Presenter : BasePresenter {
        fun onRequestData()
    }

}