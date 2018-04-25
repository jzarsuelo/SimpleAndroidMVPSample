package com.jzarsuelo.android.sunshine.view.main

import com.jzarsuelo.android.sunshine.view.BasePresenter
import com.jzarsuelo.android.sunshine.view.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {
        fun showData(data: String)
    }

    interface Presenter : BasePresenter {
        fun onRequestData()
    }

}