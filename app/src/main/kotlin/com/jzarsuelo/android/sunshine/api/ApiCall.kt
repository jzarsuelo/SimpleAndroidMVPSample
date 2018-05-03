package com.jzarsuelo.android.sunshine.api

import retrofit2.Response


interface ApiCall<T> {
    fun cancel()
    fun enqueue(callback: ApiCallback<T>)
    fun clone(): ApiCall<T>
    fun execute(): Response<T>
    fun isExecuted(): Boolean
    fun isCanceled(): Boolean
}