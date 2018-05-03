package com.jzarsuelo.android.sunshine.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Executor


/**
 * Adapts a [Call] to [ApiCall]
 */
class ApiCallAdapter<T>(
        private val call: Call<T>,
        private val callbackExecutor: Executor
) : ApiCall<T> {

    override fun cancel() {
        call.cancel()
    }

    override fun enqueue(callback: ApiCallback<T>) {
        call.enqueue(object : Callback<T>{
            override fun onFailure(call: Call<T>?, t: Throwable) {
                callbackExecutor.execute {
                    if (t is IOException) {
                        callback.networkError(t)
                    } else {
                        callback.unexpectedError(t)
                    }
                }
            }

            override fun onResponse(call: Call<T>?, response: Response<T>) {
                callbackExecutor.execute {

                    val code = response.code()
                    when (code) {
                        in 200..299 -> callback.success(response)
                        401 -> callback.unauthenticated(response)
                        in 400..499 -> callback.clientError(response)
                        in 500..599 -> callback.serverError(response)
                        else -> callback.unexpectedError(RuntimeException("Unexpected response $response"))
                    }
                }
            }

        })
    }

    override fun clone(): ApiCall<T> = ApiCallAdapter(call.clone(), callbackExecutor)

    override fun execute(): Response<T> = call.execute()

    override fun isExecuted(): Boolean = call.isExecuted

    override fun isCanceled(): Boolean = call.isCanceled
}