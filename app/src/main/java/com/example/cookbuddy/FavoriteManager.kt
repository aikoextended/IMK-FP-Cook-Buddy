package com.example.cookbuddy

import androidx.compose.runtime.mutableStateListOf

object FavoriteManager {
    val favoriteRecipes = mutableStateListOf<Recipe>()

    fun toggleFavorite(recipe: Recipe) {
        if (favoriteRecipes.any { it.id == recipe.id }) {
            favoriteRecipes.removeAll { it.id == recipe.id }
        } else {
            favoriteRecipes.add(recipe)
        }
    }

    fun isFavorite(recipe: Recipe): Boolean {
        return favoriteRecipes.any { it.id == recipe.id }
    }
}
