package com.jzarsuelo.android.sunshine.api

import com.jzarsuelo.android.sunshine.BuildConfig
import com.jzarsuelo.android.sunshine.data.ForecastResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Contain methods use to execute HTTP request
 */
interface OpenWeatherMapService {

    @GET(ApiEndpoints.FORECAST_DAILY)
    fun getForecast(
            @Query("q") city: String,
            @Query("units") unit: String,
            @Query("cnt") days: Int
    ): ApiCall<ForecastResponse>

    companion object {

        val instance: OpenWeatherMapService by lazy {
            val builder = OkHttpClient.Builder()
            // interceptor for debugging
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                builder.addInterceptor(interceptor)
            }

            // interceptor for adding "appid" query param for each request to the Api
            val apiIdParamInterceptor = Interceptor {
                val request = it.request()
                val url = request.url().newBuilder()
                        .addQueryParameter("appid", BuildConfig.API_APP_ID)
                        .build()
                val newRequest = it.request().newBuilder().url(url).build()
                it.proceed(newRequest)
            }
            builder.addInterceptor(apiIdParamInterceptor)

            val apiUri = (BuildConfig.apiBaseUri + BuildConfig.apiVersion + "/")
            val okHttpClient = builder.build()
            val retrofit = Retrofit.Builder()
                    .baseUrl(apiUri)
                    .addCallAdapterFactory(ErrorHandlingCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()

            retrofit.create(OpenWeatherMapService::class.java)
        }
    }

}