package com.example.vkdonations.network

import com.example.vkdonations.models.Donation
import com.squareup.moshi.Json

data class DonationGetResponse(
    @field:Json(name = "_id") val id: String,
    @field:Json(name = "user_id") val userId: Int,
    @field:Json(name = "data") val donation: Donation
)