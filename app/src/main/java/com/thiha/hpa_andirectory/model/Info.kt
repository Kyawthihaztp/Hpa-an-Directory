package com.thiha.hpa_andirectory.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Info(
    val address: String,
    val created_at: String,
    val description: String,
    val id: String,
    val list_image: String,
    val list_name: String,
    val listing_id: String,
    val location: String,
    val phoneno: String,
    val photo: String,
    val title: String,
    val updated_at: String
):Parcelable