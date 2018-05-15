package com.jzarsuelo.android.sunshine.ui.main

import com.jzarsuelo.android.sunshine.data.Forecast
import com.jzarsuelo.android.sunshine.ui.BasePresenter
import com.jzarsuelo.android.sunshine.ui.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {
        fun showData(data: List<Forecast>, city: String, country: String, unit: String)
        fun showErrorApiKeyNotFound()
        fun showErrorCityNotFound()
        fun showErrorNoInternetConnection()
        fun showSnackBarNoInternetConnection()
    }

    interface Presenter : BasePresenter {
        fun onRequestData()
    }

}