package com.example.cookbuddy

data class Recipe(
    val id: Int,
    val title: String,
    val imageRes: Int,
    val category: String,
    val time: Int,
    val servings: Int,
    val calories: Int,
    val fat: Int,
    val protein: Int,
    val carbs: Int,
    val likes: Int,
    val ingredients: List<String>,
    val instructions: List<String>,
    val instructionImages: List<Int?>, // Boleh null, artinya langkah ini tidak ada gambar
    val reviews: List<Review>
) {
    val instructionsWithImages: List<Pair<String, Int?>>
        get() = instructions.zip(instructionImages)
}
