package com.example.cookbuddy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@Composable
fun Homepage(navController: NavController) {
    val categories = listOf("Popular", "Western", "Drinks", "Dessert", "Cake")
    val selectedCategory = remember { mutableStateOf("Popular") }

    val categoryIconMap = mapOf(
        "Popular" to R.drawable.ic_popular,
        "Western" to R.drawable.ic_western,
        "Drinks" to R.drawable.ic_drinks,
        "Dessert" to R.drawable.ic_dessert,
        "Cake" to R.drawable.ic_cake
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Header Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Hi, User!", style = MaterialTheme.typography.bodyMedium)
            Icon(Icons.Default.Info, contentDescription = "Info")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "What would\nyou like to cook?",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Search bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF6F1EB), shape = RoundedCornerShape(12.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Search, contentDescription = "Search")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Search Recipe", color = Color.Gray)
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_mic),
                contentDescription = "Voice Search",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Category Filter with Icons
        LazyRow {
            items(categories) { category ->
                val isSelected = category == selectedCategory.value
                val iconRes = categoryIconMap[category] ?: R.drawable.ic_default

                Column(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) Color(0xFF4C0F0F) else Color(0xFFF6F1EB))
                        .clickable { selectedCategory.value = category }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = category,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(category, color = if (isSelected) Color.White else Color.Black, fontSize = MaterialTheme.typography.bodySmall.fontSize)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Popular Recipes", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(12.dp))

        // Recipe Card
        RecipeCard(
            title = "Egg Salad",
            category = "Western",
            calories = 333,
            fat = 30,
            protein = 13,
            carbs = 2,
            likes = 216_000,
            imageRes = R.drawable.egg_salad // letakkan gambar di res/drawable
        )
    }
}

@Composable
fun RecipeCard(
    title: String,
    category: String,
    calories: Int,
    fat: Int,
    protein: Int,
    carbs: Int,
    likes: Int,
    imageRes: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(title, fontWeight = FontWeight.Bold)
                Text(category, color = Color.Gray)

                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("$calories Calories")
                    Text("${fat}gr Fat")
                    Text("${protein}gr Protein")
                    Text("${carbs}gr Carbs")
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Favorite, contentDescription = "Likes", tint = Color.Red)
                    Text("${likes / 1000}K", modifier = Modifier.padding(start = 4.dp))
                }
            }
        }
    }
}
