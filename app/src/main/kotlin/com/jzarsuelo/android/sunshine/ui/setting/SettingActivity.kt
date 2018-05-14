package com.jzarsuelo.android.sunshine.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.jzarsuelo.android.sunshine.R
import kotlinx.android.synthetic.main.toolbar_main.*

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_setting)
        setSupportActionBar(mainToolbar)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.settings)
        }

        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, SettingFragment.newInstance())
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    companion object {
        fun newIntent(context: Context) : Intent = Intent(context, SettingActivity::class.java)
    }
}
