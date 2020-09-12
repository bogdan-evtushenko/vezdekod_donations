package com.example.vkdonations.network

import com.squareup.moshi.Json

data class DonationPostResponse(
    @field:Json(name = "success") val success: Boolean,
    @field:Json(name = "id") val id: String
)