package com.jakode.eventa.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val title: String,
    val description: String,
    val moreDetails: String,
    val starting: String,
    val month: String,
    val day: Int,
    val price: String,
    val image: Uri,
    val location: Uri
) : Parcelable