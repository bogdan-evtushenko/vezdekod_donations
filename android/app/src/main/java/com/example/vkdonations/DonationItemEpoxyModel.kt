package com.example.vkdonations

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import com.example.vkdonations.epoxy.BaseEpoxyModel
import com.example.vkdonations.models.Donation
import com.example.vkdonations.models.createSubtitleFromDonation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_create_post.*
import kotlinx.android.synthetic.main.card_donation.view.*
import kotlinx.android.synthetic.main.view_donation_item.view.*
import kotlinx.android.synthetic.main.view_donations_stub.view.*
import java.text.SimpleDateFormat
import java.util.*

class DonationItemEpoxyModel(private val donation: Donation) :
    BaseEpoxyModel(R.layout.view_donation_item) {
    init {
        id("DonationItemEpoxyModel", donation.toString())
    }

    @SuppressLint("SetTextI18n")
    override fun bind(view: View): Unit = with(view) {
        super.bind(view)
        tvName.text = donation.author
        tvDate.text =
            SimpleDateFormat("d MMMM yyyy").format(Date(donation.startDate ?: Date().time))
        tvPostTitle.text = donation.postDescription
        Picasso.with(context).load(donation.imageUrl).into(image)
        tvTitle.text = donation.title
        tvSubtitle.text = createSubtitleFromDonation(donation, context)
        val progress = (1..100).random()
        spinner.progress = progress

        tvSum.text =
            "${(donation.amount?.div(100)?.times(progress)).toString()} out of ${donation.amount}"
    }
}