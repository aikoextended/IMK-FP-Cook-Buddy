package com.example.cookbuddy

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecipeDetailScreen(recipe: Recipe) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(recipe.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Image(
            painter = painterResource(id = recipe.imageRes),
            contentDescription = recipe.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text("Category: ${recipe.category}")
        Text("Calories: ${recipe.calories}")
        Text("Fat: ${recipe.fat}g")
        Text("Protein: ${recipe.protein}g")
        Text("Carbs: ${recipe.carbs}g")
    }
}
