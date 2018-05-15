package com.jzarsuelo.android.sunshine.ui.main

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.jzarsuelo.android.sunshine.R
import com.jzarsuelo.android.sunshine.app.Injection
import com.jzarsuelo.android.sunshine.data.Forecast
import com.jzarsuelo.android.sunshine.ui.setting.SettingActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)

        setSupportActionBar(mainToolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = ""
        supportActionBar?.setLogo(R.drawable.ic_logo)

    }

    override fun onStart() {
        super.onStart()

        presenter.onRequestData()
    }

    override var presenter: MainContract.Presenter = MainPresenter(this,
            Injection.forecastRepository(), Injection.preferenceRepository())

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> startActivity( SettingActivity.newIntent(this) )
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showData(data: List<Forecast>, city: String, country: String, unit: String) {
        noConnectionViewStub.visibility = View.GONE

        val adapter = ForecastAdapter(city, country, unit)
        adapter.add(data)

        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    override fun showErrorApiKeyNotFound() {
        Snackbar.make(recyclerView, getString(R.string.error_api_key_not_found),
                Snackbar.LENGTH_INDEFINITE).show()
    }

    override fun showErrorCityNotFound() {
        Snackbar.make(recyclerView, getString(R.string.error_city_not_found),
                Snackbar.LENGTH_INDEFINITE).show()
    }

    override fun showErrorNoInternetConnection() {
        progressBar.visibility = View.GONE
        noConnectionViewStub.inflate()
    }

    override fun showSnackBarNoInternetConnection() {
        Snackbar.make(recyclerView, R.string.error_snackbar_no_internet_connection, Snackbar.LENGTH_LONG).show()
    }
}
