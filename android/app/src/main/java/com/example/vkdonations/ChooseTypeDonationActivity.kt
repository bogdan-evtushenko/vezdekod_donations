package com.example.vkdonations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_choose_type_donation.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.tvTitle

class ChooseTypeDonationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_type_donation)

        tvTitle.text = getString(R.string.type_of_donation)

        cvDonationWithGoal.setOnClickListener {
            startActivity(Intent(this, CreateDonationWithGoalMainActivity::class.java))
        }
        ivBack.setOnClickListener { finish() }
        cvRegularDonation.setOnClickListener {
            startActivity(Intent(this, CreateRegularDonationActivity::class.java))
        }
    }
}