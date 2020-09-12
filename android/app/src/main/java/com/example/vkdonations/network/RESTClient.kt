package com.example.vkdonations.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val okHttpClient = OkHttpClient.Builder()
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("http://161.35.21.226:3000/api/")
    .addConverterFactory(MoshiConverterFactory.create())
    .client(okHttpClient)
    .build()

val restClient: APIServices = retrofit.create(APIServices::class.java)