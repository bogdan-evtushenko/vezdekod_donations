package com.example.vkdonations

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vkdonations.components.donation
import com.example.vkdonations.models.Donation
import kotlinx.android.synthetic.main.activity_donation.*
import kotlinx.android.synthetic.main.view_donation_item.*
import java.text.SimpleDateFormat
import java.util.*

class DonationActivity : AppCompatActivity() {
    private val donation: Donation by lazy {
        intent?.extras?.donation ?: throw IllegalStateException()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation)

        tvTitle.text = donation.title
        tvSubtitle.text = "Author ${donation.author}"
        tvEndDate.text = SimpleDateFormat("d MMMM yyyy").format(Date(donation.endDate ?: Date().time))
        tvDescription.text = donation.description

        val resultSum = donation.amount ?: 100000
        val sum = ((1..100).random() * resultSum / 100)
        ValueAnimator.ofFloat(sum.toFloat(), resultSum.toFloat()).apply {
            duration = 3000
            addUpdateListener { animation ->
                spinner.progress = (animation.animatedValue as Float).toInt() / 100
                tvSum.text = getString(
                    R.string.rubles_sum,
                    (animation.animatedValue as Float).toInt(),
                    resultSum
                )
            }
        }.start()
        btnHelp.setOnClickListener { finish() }
    }

    companion object {
        fun newIntent(
            context: Context,
            donation: Donation
        ) = Intent(context, DonationActivity::class.java).apply {
            putExtras(Bundle().apply {
                this.donation = donation
            })
        }
    }
}