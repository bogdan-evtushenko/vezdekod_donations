package com.example.vkdonations

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vkdonations.components.InputField
import com.example.vkdonations.components.donation
import com.example.vkdonations.models.Donation
import kotlinx.android.synthetic.main.activity_create_donation_with_goal_additional.*
import kotlinx.android.synthetic.main.input_field.view.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import java.text.SimpleDateFormat
import java.util.*

class CreateDonationWithGoalAdditionalActivity : AppCompatActivity() {

    private var timestamp: Long = Date().time
    private val donation: Donation by lazy {
        intent?.extras?.donation ?: throw IllegalStateException()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_donation_with_goal_additional)

        initViews()
    }

    private fun initViews() {
        tvTitle.text = getString(R.string.main)
        ivBack.setOnClickListener { finish() }

        tvAuthor.configure(
            labelTextResId = R.string.author,
            inputText = "Matvej Pravosudov",
            action = InputField.Action.Next
        )

        llDate.setOnClickListener { if (radioOnCertainDate.isChecked) selectDate() }

        btnCreateDonation.setOnClickListener {
            if (!radioOnCertainDate.isChecked && !radioWhenCompleted.isChecked) {
                showToast(R.string.choose_when_donation_will_end)
            } else if (radioOnCertainDate.isChecked && timestamp < Date().time) {
                showToast(R.string.choose_correct_date)
            } else {
                if (radioOnCertainDate.isChecked) {
                    donation.endDate = timestamp
                }
                donation.author = tvAuthor.editText.text.toString()
                println("Here tvAuthor : ${tvAuthor.editText.text}")
                startActivity(CreatePostActivity.newIntent(this, donation))
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun selectDate() {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        val dialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val newCalendar = Calendar.getInstance()
                newCalendar.timeInMillis = timestamp
                newCalendar.set(Calendar.YEAR, year)
                newCalendar.set(Calendar.MONTH, monthOfYear)
                newCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                if (newCalendar.timeInMillis > Date().time) {
                    timestamp = newCalendar.timeInMillis
                    tvDate.text = SimpleDateFormat("d MMMM yyyy").format(Date(timestamp))
                } else {
                    showToast(R.string.choose_correct_date)
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.show()
    }

    private fun showToast(messageId: Int) {
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newIntent(
            context: Context,
            donation: Donation
        ) = Intent(context, CreateDonationWithGoalAdditionalActivity::class.java).apply {
            putExtras(Bundle().apply {
                this.donation = donation
            })
        }
    }

}