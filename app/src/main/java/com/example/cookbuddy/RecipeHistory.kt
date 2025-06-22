package com.example.cookbuddy

data class RecipeHistory(
    val id: Int,
    val title: String,
    val category: String,
    val date: String,
    val imageResId: Int,
    val status: RecipeStatus
)

enum class RecipeStatus {
    ONGOING,
    FINISHED_NOT_REVIEWED,
    FINISHED_REVIEWED
}
