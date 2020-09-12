package com.example.vkdonations

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vkdonations.components.asDeferred
import com.example.vkdonations.components.donation
import com.example.vkdonations.models.Donation
import com.example.vkdonations.models.createSubtitleFromDonation
import com.example.vkdonations.network.DonationRequest
import com.example.vkdonations.network.restClient
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_create_post.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.CancellationException

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
        tvSubtitle.text = createSubtitleFromDonation(donation, this)

        btnPost.setOnClickListener {
            donation.postDescription = editText.text.toString()
            donation.startDate = Date().time
            progressBarDialog.show()
            uploadDonation {
                progressBarDialog.dismiss()
                startActivity(Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }
    }

    private fun uploadDonation(onCompleteListener: () -> Unit) = GlobalScope.launch {
        val response = try {
            restClient.saveDonation(
                DonationRequest(
                    R.string.default_web_client_id.hashCode(),
                    donation
                )
            ).asDeferred()
        } catch (e: CancellationException) {
            null
        } catch (t: Throwable) {
            println("Error: ${t.message}")
            null
        }

        println("Response Body: ${response?.body()}")

        withContext(Dispatchers.Main) {
            onCompleteListener()
        }
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