package com.example.vkdonations.network

import com.example.vkdonations.models.Donation
import com.squareup.moshi.Json

data class DonationRequest(
    @field:Json(name = "userId")  val userId: Int,
    @field:Json(name = "data") val data: Donation
)