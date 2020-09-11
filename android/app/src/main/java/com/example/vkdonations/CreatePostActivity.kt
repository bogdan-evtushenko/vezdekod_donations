package com.example.vkdonations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vkdonations.epoxy.BaseEpoxyController
import com.example.vkdonations.models.Donation
import kotlinx.android.synthetic.main.activity_create_post.*

class CreatePostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        btnDonations.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MyDonationsActivity::class.java
                )
            )
        }
    }
}