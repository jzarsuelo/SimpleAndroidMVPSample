package com.jzarsuelo.android.sunshine.data

/**
 * Base callback for the application after executing the HTTP request.
 * This is different to [retrofit2.Callback]
 */
interface BaseCallback<in T> {

    fun onSuccess(data: T)

    fun onFailure(message: String)
}