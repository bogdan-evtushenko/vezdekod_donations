package com.example.vkdonations.components

import kotlinx.coroutines.CompletableDeferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

suspend fun <T> Call<T>.asDeferred(): Response<T> {
    val deferred = CompletableDeferred<Response<T>>()

    deferred.invokeOnCompletion {
        if (deferred.isCancelled) {
            try {
                cancel()
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>?) {
            when (response) {
                null -> deferred.completeExceptionally(IllegalStateException("response is null"))
                else -> deferred.complete(response)
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable?) {
            deferred.completeExceptionally(t ?: IllegalStateException())
        }
    })

    return deferred.await()
}