package com.jzarsuelo.android.sunshine.view.main

import android.content.Context
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
import com.jzarsuelo.android.sunshine.utils.getBigIcon
import com.jzarsuelo.android.sunshine.utils.getFormattedDate
import com.jzarsuelo.android.sunshine.utils.getIcon
import java.util.*


class ForecastAdapter(private val city: String, private val country: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data : List<Forecast> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_today_forecast -> TodayViewHolder(view)
            else -> ViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int = when(position) {
        0 -> R.layout.item_today_forecast
        else -> R.layout.item_forecast
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TodayViewHolder)
            holder.bind(data[position], city, country)
        else if (holder is ViewHolder){
            holder.bind(data[position], position)
        }
    }

    fun add(data: List<Forecast>) {
        this.data = data
        notifyDataSetChanged()
    }

    class TodayViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var todayForecastImageView: ImageView? = null
        private var todayTextView: TextView? = null
        private var todayForecastTextView: TextView? = null
        private var todayMaxTempTextView: TextView? = null
        private var todayMinTempTextView: TextView? = null
        private var locationTextView: TextView? = null
        private var context: Context? = null

        init {
            todayForecastImageView = v.findViewById(R.id.todayForecastImageView)
            todayTextView = v.findViewById(R.id.todayTextView)
            todayForecastTextView = v.findViewById(R.id.todayForecastTextView)
            todayMaxTempTextView = v.findViewById(R.id.todayMaxTempTextView)
            todayMinTempTextView = v.findViewById(R.id.todayMinTempTextView)
            locationTextView = v.findViewById(R.id.locationTextView)

            context = v.context
        }

        fun bind(forecast: Forecast, city: String, country: String) {
            context?.let {
                val icon = forecast.weathers[0].getBigIcon()

                val requestOptions = RequestOptions()
                        .placeholder(icon)

                GlideApp.with(it)
                        .load("")
                        .apply(requestOptions)
                        .into(todayForecastImageView!!)

                val dateFormat = it.getString(R.string.date_format_month_date)
                todayTextView?.text = it.getString(R.string.today,
                        forecast.dateTime.getFormattedDate(dateFormat))
                todayForecastTextView?.text = forecast.weathers[0].main
                todayMaxTempTextView?.text = it.getString(R.string.degree_celcius,
                        forecast.temperature.max.toInt().toString())
                todayMinTempTextView?.text = it.getString(R.string.degree_celcius,
                        forecast.temperature.min.toInt().toString())
                locationTextView?.text = "$city, $country"
            }
        }
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

        fun bind(forecast: Forecast, position: Int) {
            context?.let {
                val icon = forecast.weathers[0].getIcon()

                val requestOptions = RequestOptions()
                        .placeholder(icon)

                GlideApp.with(it)
                        .load("")
                        .apply(requestOptions)
                        .into(forecastImageView!!)

                dayTextView?.text = when(position){
                    1 -> it.getString(R.string.tomorrow)
                    else -> {
                        val dateFormat = it.getString(R.string.date_format_day)
                        forecast.dateTime.getFormattedDate(dateFormat)
                    }
                }

                forecastTextView?.text = forecast.weathers[0].main
                maxTempTextView?.text = it.getString(R.string.degrees,
                        forecast.temperature.max.toInt().toString())
                minTempTextView?.text = it.getString(R.string.degrees,
                        forecast.temperature.min.toInt().toString())
            }
        }
    }
}