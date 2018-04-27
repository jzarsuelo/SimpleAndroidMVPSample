package com.jzarsuelo.android.sunshine.view.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jzarsuelo.android.sunshine.R
import com.jzarsuelo.android.sunshine.app.Injection
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setLogo(R.mipmap.ic_launcher)
    }

    override var presenter: MainContract.Presenter = MainPresenter(this, Injection.forecastRepository())

    override fun showData(data: String) {
    }
}
