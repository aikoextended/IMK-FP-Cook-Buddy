package com.example.cookbuddy

data class Review(
    val userName: String,
    val avatarRes: Int,
    val rating: Double,
    val comment: String,
    val timeAgo: String
)
