package com.example.vkdonations

import android.content.Intent
import android.view.View
import com.example.vkdonations.epoxy.BaseEpoxyModel
import kotlinx.android.synthetic.main.view_donations_stub.view.*

class DonationsStubEpoxyModel : BaseEpoxyModel(R.layout.view_donations_stub) {
    init {
        id("DonationsStubEpoxyModel")
    }

    override fun bind(view: View): Unit = with(view) {
        super.bind(view)
        btnCreateDonation.setOnClickListener {
            context.startActivity(Intent(context, ChooseTypeDonationActivity::class.java))
        }
    }
}