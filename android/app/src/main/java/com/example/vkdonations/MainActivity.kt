package com.example.vkdonations

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vkdonations.components.asDeferred
import com.example.vkdonations.epoxy.BaseEpoxyController
import com.example.vkdonations.models.Donation
import com.example.vkdonations.network.restClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CancellationException

class MainActivity : AppCompatActivity() {

    private val epoxyController by lazy { EpoxyController() }
    private val progressBarDialog by lazy { ProgressBarDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDonations.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MyDonationsActivity::class.java
                )
            )
        }

        recyclerView.run {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = epoxyController.adapter
        }

        epoxyController.requestModelBuild()

        progressBarDialog.show()
        fetchDonations {
            progressBarDialog.dismiss()
            epoxyController.data = it
        }
    }

    private fun fetchDonations(onCompleteListener: (List<Donation>) -> Unit) = GlobalScope.launch {
        val response = try {
            restClient.fetchDonations(2131558458).asDeferred()
        } catch (e: CancellationException) {
            null
        } catch (t: Throwable) {
            println("Error: ${t.message}")
            null
        }

        println("Response Body: ${response?.body()}")

        withContext(Dispatchers.Main) {
            onCompleteListener(response?.body()!!.map { it.donation.apply { id = it.id } })
        }
    }

    class EpoxyController : BaseEpoxyController<Donation>() {

        override fun buildModels() {
            data?.forEach { donation ->
                DonationItemEpoxyModel(donation).addTo(this)
            }
        }
    }
}