package com.thiha.hpa_andirectory.model

data class Item(
    val address: String,
    val created_at: String,
    val description: String,
    val id: Int,
    val listing_id: String,
    val location: String,
    val phoneno: String,
    val photo: String,
    val title: String,
    val updated_at: String
)