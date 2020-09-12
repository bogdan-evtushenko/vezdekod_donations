package com.example.vkdonations.models

import java.io.Serializable

data class Donation(
    var id: String? = null,
    var imageUrl: String? = null,
    var title: String? = null,
    var amount: Int? = null,
    var goal: String? = null,
    var postDescription: String? = null,
    var description: String? = null,
    var author: String? = null,
    var startDate: Long? = null,
    var isRegular: Boolean? = null,
    var endDate: Long? = null
) : Serializable