package com.example.vkdonations.network

import retrofit2.Call
import retrofit2.http.*

interface APIServices {
    @POST("android/new/")
    fun saveDonation(@Body body: DonationRequest): Call<DonationPostResponse>

    @GET("android/")
    fun fetchDonations(
        @Query("userId") userId: Int? = null
    ): Call<List<DonationGetResponse>>
}
