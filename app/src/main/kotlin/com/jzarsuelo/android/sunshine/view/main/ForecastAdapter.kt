package com.jzarsuelo.android.sunshine.view.main

import android.content.Context
import android.content.res.TypedArray
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.request.RequestOptions
import com.jzarsuelo.android.sunshine.R
import com.jzarsuelo.android.sunshine.data.Forecast
import com.jzarsuelo.android.sunshine.utils.GlideApp
import com.jzarsuelo.android.sunshine.utils.getFormattedDate
import com.jzarsuelo.android.sunshine.utils.getIcon
import java.util.*


class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    private var data : List<Forecast> = ArrayList<Forecast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastAdapter.ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun add(data: List<Forecast>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var forecastImageView: ImageView? = null
        private var dayTextView: TextView? = null
        private var forecastTextView: TextView? = null
        private var maxTempTextView: TextView? = null
        private var minTempTextView: TextView? = null
        private var context: Context? = null

        init {
            forecastImageView = v.findViewById(R.id.forecastImageView)
            dayTextView = v.findViewById(R.id.dayTextView)
            forecastTextView = v.findViewById(R.id.forecastTextView)
            maxTempTextView = v.findViewById(R.id.maxTempTextView)
            minTempTextView = v.findViewById(R.id.minTempTextView)

            context = v.context
        }

        fun bind(forecast: Forecast) {
            context?.let {
                val icon = forecast.weathers[0].getIcon()

                val requestOptions = RequestOptions()
                        .placeholder(icon)

                GlideApp.with(it)
                        .load("")
                        .apply(requestOptions)
                        .into(forecastImageView!!)
            }

            dayTextView?.text = forecast.dateTime.getFormattedDate("EEEE")
            forecastTextView?.text = forecast.weathers[0].main
            maxTempTextView?.text = forecast.temperature.max.toInt().toString() + "°C"
            minTempTextView?.text = forecast.temperature.min.toInt().toString() + "°C"
        }
    }
}