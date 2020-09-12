package com.example.vkdonations.components

import android.os.Bundle
import com.example.vkdonations.models.Donation

var Bundle.donation: Donation?
    get() = getSerializable("donation") as? Donation
    set(value) = putSerializable("donation", value)
