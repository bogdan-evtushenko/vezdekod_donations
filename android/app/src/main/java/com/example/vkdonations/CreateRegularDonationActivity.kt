package com.example.vkdonations

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vkdonations.components.InputField
import kotlinx.android.synthetic.main.activity_create_regular_donation.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class CreateRegularDonationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_regular_donation)

        initViews()
    }

    private fun initViews() {
        tvTitle.text = getString(R.string.regular_donation)
        ivBack.setOnClickListener { finish() }

        tvTitleOfDonation.configure(
            labelTextResId = R.string.title_of_donation,
            inputHintResId = R.string.title_of_donation,
            action = InputField.Action.Next
        )
        tvAmount.configure(
            labelTextResId = R.string.sum_in_rubles,
            inputHintResId = R.string.how_much_to_collect,
            action = InputField.Action.Next
        )
        tvGoal.configure(
            labelTextResId = R.string.goal,
            inputHintResId = R.string.for_example_for_human_treatment,
            action = InputField.Action.Next,
            type = InputField.Type.TEXT_MULTILINE
        )

        tvDescription.configure(
            labelTextResId = R.string.description,
            inputHintResId = R.string.description_regular_hint,
            action = InputField.Action.Next,
            type = InputField.Type.TEXT_MULTILINE
        )

        tvBankAccount.configure(
            labelTextResId = R.string.where_to_get_money,
            inputText = "Card: 0000 0000 0000 0002",
            action = InputField.Action.Next,
            type = InputField.Type.TEXT_MULTILINE
        )

        tvAuthor.configure(
            labelTextResId = R.string.author,
            inputText = "Matvej Pravosudov",
            action = InputField.Action.Next
        )

        btnCreateDonation.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CreatePostActivity::class.java
                )
            )
        }
    }
}