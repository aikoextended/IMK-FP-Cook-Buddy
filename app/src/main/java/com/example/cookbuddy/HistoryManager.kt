package com.example.cookbuddy

import androidx.compose.runtime.mutableStateListOf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object HistoryManager {
    val historyList = mutableStateListOf<RecipeHistory>()

    fun addOrUpdate(recipe: Recipe, isFinished: Boolean, isReviewed: Boolean = false) {
        historyList.removeAll { it.id == recipe.id }

        val status = when {
            isFinished && isReviewed -> RecipeStatus.FINISHED_REVIEWED
            isFinished -> RecipeStatus.FINISHED_NOT_REVIEWED
            else -> RecipeStatus.ONGOING
        }

        historyList.add(
            RecipeHistory(
                id = recipe.id,
                title = recipe.title,
                category = recipe.category,
                date = getCurrentDate(),
                imageResId = recipe.imageRes,
                status = status
            )
        )
    }

    fun markAsReviewed(recipeId: Int) {
        val index = historyList.indexOfFirst { it.id == recipeId }
        if (index != -1) {
            val old = historyList[index]
            historyList[index] = old.copy(status = RecipeStatus.FINISHED_REVIEWED)
        }
    }

    private fun getCurrentDate(): String {
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        return formatter.format(Date())
    }
}
