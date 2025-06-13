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
    val imageRes: Int,
    val time: Int,
    val servings: Int,
    val ingredients: List<String>,
    val instructions: List<String>,
    val reviews: List<Review> // Tambahkan ini
)
