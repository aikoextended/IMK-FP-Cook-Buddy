package com.example.cookbuddy

data class Recipe(
    val id: Int,
    val title: String,
    val category: String,
    val calories: Int,
    val fat: Int,
    val protein: Int,
    val carbs: Int,
    val likes: Int,
    val imageRes: Int
)
