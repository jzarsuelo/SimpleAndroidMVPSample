package com.jzarsuelo.android.sunshine.data

interface BaseLocalCallback<in T> {
    fun onSuccess(t: T)
    fun onFailed()
}