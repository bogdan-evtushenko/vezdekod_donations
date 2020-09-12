package com.example.vkdonations

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vkdonations.components.InputField
import com.example.vkdonations.models.Donation
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_create_donation_with_goal_main.*
import kotlinx.android.synthetic.main.activity_create_regular_donation.*
import kotlinx.android.synthetic.main.activity_create_regular_donation.cvCover
import kotlinx.android.synthetic.main.activity_create_regular_donation.imageLayout
import kotlinx.android.synthetic.main.activity_create_regular_donation.ivCover
import kotlinx.android.synthetic.main.activity_create_regular_donation.tvAmount
import kotlinx.android.synthetic.main.activity_create_regular_donation.tvBankAccount
import kotlinx.android.synthetic.main.activity_create_regular_donation.tvDescription
import kotlinx.android.synthetic.main.activity_create_regular_donation.tvGoal
import kotlinx.android.synthetic.main.activity_create_regular_donation.tvTitleOfDonation
import kotlinx.android.synthetic.main.input_field.view.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import java.io.IOException
import java.util.*

class CreateRegularDonationActivity : AppCompatActivity() {

    private val donation = Donation(isRegular = true)

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
            donation.title = tvTitleOfDonation.editText.text.toString()
            donation.amount = tvAmount.editText.text.toString().toIntOrNull()
            donation.goal = tvGoal.editText.text.toString()
            donation.description = tvDescription.editText.text.toString()
            donation.author = tvAuthor.editText.text.toString()

            if (donation.imageUrl.isNullOrEmpty()) {
                showToast(R.string.wait_for_the_picture_to_load)
            } else if (donation.title.isNullOrEmpty() || donation.amount == null || donation.goal.isNullOrEmpty() || donation.description.isNullOrEmpty()) {
                showToast(R.string.fill_all_field_correctly)
            } else {
                startActivity(
                    CreatePostActivity.newIntent(this, donation)
                )
            }
        }

        imageLayout.setOnClickListener {
            chooseImage()
        }
    }

    private fun showToast(messageId: Int) {
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
    }

    private fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            REQUEST_PICK_IMAGE
        )
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val filePath = data.data
            try {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                cvCover.visibility = View.VISIBLE
                ivCover.setImageBitmap(bitmap)
                uploadImageToStorage(filePath!!)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun uploadImageToStorage(filePath: Uri) {
        val ref: StorageReference = FirebaseStorage.getInstance().reference
            .child("images/" + UUID.randomUUID().toString())

        val uploadTask = ref.putFile(filePath)

        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                donation.imageUrl = downloadUri?.toString()
            } else {
                showToast(R.string.try_to_upload_image_one_more_time)
            }
        }
    }

    companion object {
        const val REQUEST_PICK_IMAGE = 1
    }
}