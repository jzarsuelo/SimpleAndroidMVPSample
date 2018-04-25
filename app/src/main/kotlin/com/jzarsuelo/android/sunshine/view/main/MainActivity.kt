package com.jzarsuelo.android.sunshine.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jzarsuelo.android.sunshine.R
import com.jzarsuelo.android.sunshine.app.Injection
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestDataButton.setOnClickListener { presenter.onRequestData() }
    }

    override var presenter: MainContract.Presenter = MainPresenter(this, Injection.forecastRepository())

    override fun showData(data: String) {
        helloWorldTextView.text = data
    }
}
