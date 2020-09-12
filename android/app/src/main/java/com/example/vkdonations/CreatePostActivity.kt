package com.example.vkdonations

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vkdonations.components.donation
import com.example.vkdonations.models.Donation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_create_post.*
import java.text.SimpleDateFormat
import java.util.*

class CreatePostActivity : AppCompatActivity() {

    private val donation: Donation by lazy {
        intent?.extras?.donation ?: throw IllegalStateException()
    }

    private val progressBarDialog by lazy { ProgressBarDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)


        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        tvName.text = donation.author
        btnClose.setOnClickListener { finish() }
        Picasso.with(this).load(donation.imageUrl).into(image);
        tvTitle.text = donation.title
        println("Author : ${donation.author}")
        tvSubtitle.text = donation.author +
                if (donation.endDate == null) {
                    ""
                } else {
                    " · ${getString(
                        R.string.will_end_on,
                        SimpleDateFormat("d MMMM yyyy").format(Date(donation.endDate!!))
                    )}"
                }

        btnPost.setOnClickListener {
            donation.postDescription = editText.text.toString()
            progressBarDialog.show()
            uploadDonation {
                progressBarDialog.hide()
                startActivity(Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }
    }

    private fun uploadDonation(onCompleteListener: () -> Unit) {
        onCompleteListener()
    }

    companion object {
        fun newIntent(
            context: Context,
            donation: Donation
        ) = Intent(context, CreatePostActivity::class.java).apply {
            putExtras(Bundle().apply {
                this.donation = donation
            })
        }
    }
}