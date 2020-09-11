package com.example.vkdonations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vkdonations.epoxy.BaseEpoxyController
import com.example.vkdonations.models.Donation
import kotlinx.android.synthetic.main.activity_my_donations.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class MyDonationsActivity : AppCompatActivity() {

    private val epoxyController by lazy { EpoxyController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_donations)

        if (true) {
            btnCreateDonation.visibility = View.GONE
        } else {
            btnCreateDonation.visibility = View.VISIBLE
        }

        recyclerView.run {
            layoutManager =
                LinearLayoutManager(this@MyDonationsActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = epoxyController.adapter
        }

        epoxyController.requestModelBuild()
        ivBack.setOnClickListener { finish() }
    }


    class EpoxyController : BaseEpoxyController<Donation>() {

        override fun buildModels() {
            DonationsStubEpoxyModel().addIf(data.isNullOrEmpty(), this)
        }
    }
}