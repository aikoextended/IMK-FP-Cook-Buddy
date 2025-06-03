package com.example.cookbuddy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@Composable
fun Homepage(navController: NavController) {
    val categories = listOf("Popular", "Western", "Drinks", "Dessert", "Cake", "etc")
    val selectedCategory = remember { mutableStateOf("Popular") }

    val categoryIconMap = mapOf(
        "Popular" to R.drawable.ic_popular,
        "Western" to R.drawable.ic_western,
        "Drinks" to R.drawable.ic_drinks,
        "Dessert" to R.drawable.ic_dessert,
        "Cake" to R.drawable.ic_cake,
        "etc" to R.drawable.ic_etc
    )

    val filteredRecipes = remember(selectedCategory.value) {
        allRecipes.filter { it.category == selectedCategory.value || selectedCategory.value == "Popular" && it.category == "Popular" }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Hi, User!", style = MaterialTheme.typography.bodyMedium)
            Image(painter = painterResource(id = R.drawable.ic_info), contentDescription = "Help", modifier = Modifier.size(32.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "What would\nyou like to cook?",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF6F1EB), shape = RoundedCornerShape(12.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.ic_search), contentDescription = "Search", modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Search Recipe", color = Color.Gray)
            Spacer(modifier = Modifier.weight(1f))
            Image(painter = painterResource(id = R.drawable.ic_mic), contentDescription = "Voice Search", modifier = Modifier.size(24.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

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
                    Image(painter = painterResource(id = iconRes), contentDescription = category, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(category, color = if (isSelected) Color.White else Color.Black, fontSize = MaterialTheme.typography.bodySmall.fontSize)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("${selectedCategory.value} Recipes", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredRecipes) { recipe ->
                RecipeCard(
                    title = recipe.title,
                    category = recipe.category,
                    calories = recipe.calories,
                    fat = recipe.fat,
                    protein = recipe.protein,
                    carbs = recipe.carbs,
                    likes = recipe.likes,
                    imageRes = recipe.imageRes
                )
            }
        }

                Spacer(modifier = Modifier.height(32.dp))
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
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column {
            Box {
                // Gambar resep
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                )

                // Overlay gradient dari bawah ke atas agar teks terlihat jelas
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .align(Alignment.BottomStart)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color(0xCC000000) // semi-transparent hitam
                                ),
                                startY = 0f,
                                endY = Float.POSITIVE_INFINITY
                            )
                        )
                )

                // Teks judul dan kategori
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                ) {
                    Text(title, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(category, color = Color.White)
                }

                // Badge jumlah like
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                        .background(Color(0xFFFF5722), shape = RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Likes",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${likes / 1000}K",
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            // Informasi nutrisi
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("$calories Calories", fontSize = MaterialTheme.typography.bodySmall.fontSize)
                    Text("${fat}gr Fat", fontSize = MaterialTheme.typography.bodySmall.fontSize)
                    Text("${protein}gr Protein", fontSize = MaterialTheme.typography.bodySmall.fontSize)
                    Text("${carbs}gr Carbs", fontSize = MaterialTheme.typography.bodySmall.fontSize)
                }
            }
        }
    }
}
