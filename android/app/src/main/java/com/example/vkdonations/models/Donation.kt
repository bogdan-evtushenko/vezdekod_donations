package com.example.vkdonations.models

import android.content.Context
import com.example.vkdonations.R
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

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

fun createSubtitleFromDonation(donation: Donation, context: Context): String {
    return donation.author + if (donation.isRegular != false) {
        " · ${context.getString(R.string.help_needed_monthly)}"
    } else {
        if (donation.endDate == null) {
            ""
        } else {
            " · ${context.getString(
                R.string.will_end_on,
                SimpleDateFormat("d MMMM yyyy").format(Date(donation.endDate!!))
            )}"
        }
    }
}