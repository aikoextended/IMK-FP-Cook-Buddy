package com.example.cookbuddy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@Composable
fun Homepage(navController: NavController) {
    val categories = listOf("All", "Asian", "Western", "Drinks", "Dessert", "etc")
    val selectedCategory = remember { mutableStateOf("All") }
    val searchQuery = remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }

    val categoryIconMap = mapOf(
        "All" to ImageVector.vectorResource(R.drawable.ic_all),
        "Asian" to ImageVector.vectorResource(R.drawable.ic_asian),
        "Western" to ImageVector.vectorResource(R.drawable.ic_western),
        "Drinks" to ImageVector.vectorResource(R.drawable.ic_drinks),
        "Dessert" to ImageVector.vectorResource(R.drawable.ic_dessert),
        "etc" to ImageVector.vectorResource(R.drawable.ic_etc)
    )

    val filteredRecipes = remember(selectedCategory.value, searchQuery.value) {
        when {
            searchQuery.value.isNotEmpty() -> {
                allRecipes.filter { recipe ->
                    recipe.title.contains(searchQuery.value, ignoreCase = true) ||
                            recipe.category.contains(searchQuery.value, ignoreCase = true)
                }
            }
            selectedCategory.value == "All" -> allRecipes
            else -> allRecipes.filter { it.category == selectedCategory.value }
        }
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
            Image(
                painter = painterResource(id = R.drawable.ic_info),
                contentDescription = "Help",
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "What would\nyou like to cook?",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Search Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(0xFFF6F1EB), shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // TextField tanpa border
                BasicTextField(
                    value = searchQuery.value,
                    onValueChange = {
                        searchQuery.value = it
                        isSearching = it.isNotEmpty()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 2.dp), // Penyesuaian kecil agar teks rata tengah
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    ),
                    decorationBox = { innerTextField ->
                        if (searchQuery.value.isEmpty()) {
                            Text(
                                "Search Recipe",
                                color = Color.Gray
                            )
                        }
                        innerTextField()
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                if (isSearching) {
                    IconButton(
                        onClick = {
                            searchQuery.value = ""
                            isSearching = false
                            selectedCategory.value = "All"
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "Clear",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Gray
                        )
                    }
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_mic),
                        contentDescription = "Voice Search",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(20.dp))

        // Kategori hanya ditampilkan jika tidak sedang mencari
        if (!isSearching) {
            LazyRow {
                items(categories) { category ->
                    val isSelected = category == selectedCategory.value
                    val icon = categoryIconMap[category] ?: Icons.Default.Info

                    Column(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) Color(0xFF4C0F0F) else Color(0xFFF6F1EB))
                            .clickable {
                                selectedCategory.value = category
                                searchQuery.value = ""
                            }
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                            .width(44.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = category,
                            modifier = Modifier.size(24.dp),
                            tint = if (isSelected) Color(0xFFF6F1EB) else Color(0xFF6B6B6B)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            category,
                            color = if (isSelected) Color(0xFFF6F1EB) else Color(0xFF6B6B6B),
                            fontSize = MaterialTheme.typography.bodySmall.fontSize
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(24.dp))
        }

        Text(
            if (isSearching) "Search Results" else "${selectedCategory.value} Recipes",
            style = MaterialTheme.typography.titleMedium
        )

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
                    imageRes = recipe.imageRes,
                    onClick = {
                        navController.navigate("detail/${recipe.id}")
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(90.dp)) // Atur tinggi sesuai kebutuhan
            }
        }
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
    imageRes: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
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
