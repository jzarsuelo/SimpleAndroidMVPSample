package com.jzarsuelo.android.sunshine.api

import retrofit2.Response
import java.io.IOException

abstract class ApiCallbackAdapter<T> : ApiCallback<T> {

    override fun serverError(response: Response<*>) {
    }

    override fun networkError(e: IOException) {
        // TODO handle generic no connection
    }

    override fun unexpectedError(t: Throwable) {
        // TODO something went wrong
    }
}