package com.jzarsuelo.android.sunshine.api

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.Executor

class ErrorHandlingCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<out Annotation>?, retrofit: Retrofit): CallAdapter<*, *>? {
        if (getRawType(returnType) != ApiCall::class.java) {
            return null
        }

        if (returnType !is ParameterizedType) {
            throw IllegalStateException("ApiCall must have generic type (e.g., MyCall<ResponseBody>)")
        }

        val responseType = getParameterUpperBound(0, returnType)
        val callbackExecutor = retrofit.callbackExecutor()
        return ErrorHandlingCallAdapter<Any>(responseType, callbackExecutor!!)
    }

    class ErrorHandlingCallAdapter<R>(
            private val responseType: Type,
            private val callbackExecutor: Executor
    ) : CallAdapter<R, ApiCall<R>> {

        override fun adapt(call: Call<R>): ApiCall<R> = ApiCallAdapter(call, callbackExecutor)

        override fun responseType(): Type = responseType
    }
}