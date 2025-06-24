package com.example.cookbuddy

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun FavoritePage(navController: NavController, modifier: Modifier = Modifier) {
    // Ambil data favorit dari FavoriteManager
    val favoriteRecipes = FavoriteManager.favoriteRecipes

    val searchQuery = remember { mutableStateOf("") }
    val selectedCategory = remember { mutableStateOf("All") }

    val filteredRecipes = favoriteRecipes.filter { recipe ->
        (selectedCategory.value == "All" || recipe.category == selectedCategory.value) &&
                (searchQuery.value.isEmpty() || recipe.title.contains(searchQuery.value, ignoreCase = true))
    }

     Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(36.dp))

         // Title "Favorite" di Tengah
         Box(
             modifier = Modifier
                 .fillMaxWidth(),
             contentAlignment = Alignment.Center
         ) {
             Text(
                 text = "Favorite",
                 style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
             )
         }

         Spacer(modifier = Modifier.height(24.dp))

        // Search Bar
         Box(
             modifier = Modifier
                 .fillMaxWidth()
                 .height(56.dp)
                 .background(Color(0xFFF6F1EB), shape = RoundedCornerShape(15.dp)) // Rounded 15dp
                 .padding(horizontal = 16.dp)
         ) {
             Row(
                 modifier = Modifier
                     .fillMaxSize(), // Isi seluruh tinggi kotak
                 verticalAlignment = Alignment.CenterVertically // Ini yang bikin pas di tengah
             ) {
                 Image(
                     painter = painterResource(id = R.drawable.ic_search),
                     contentDescription = "Search",
                     modifier = Modifier.size(24.dp)
                 )
                 Spacer(modifier = Modifier.width(8.dp))
                 BasicTextField(
                     value = searchQuery.value,
                     onValueChange = { searchQuery.value = it },
                     singleLine = true,
                     textStyle = LocalTextStyle.current.copy(color = Color.Black),
                     modifier = Modifier.fillMaxWidth(),
                     decorationBox = { innerTextField ->
                         if (searchQuery.value.isEmpty()) {
                             Text("Search Favorite", color = Color.Gray)
                         }
                         innerTextField()
                     }
                 )
             }
         }

         Spacer(modifier = Modifier.height(16.dp))

        // Category
        val categories = listOf("All", "Asian", "Western", "Drinks", "Dessert", "Etc")

        LazyRow {
            items(categories) { category ->
                val isSelected = category == selectedCategory.value
                Box(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) Color(0xFF4C0F0F) else Color(0xFFF6F1EB))
                        .clickable { selectedCategory.value = category }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = category,
                        color = if (isSelected) Color.White else Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (filteredRecipes.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No Favorites Found", color = Color.Gray)
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredRecipes) { recipe ->
                    RecipeCard(recipe = recipe, navController = navController)
                }
                item {
                    Spacer(modifier = Modifier.height(90.dp))
                }
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("detail/${recipe.id}") },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(id = recipe.imageRes),
                    contentDescription = recipe.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .align(Alignment.BottomStart)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xCC000000))
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                ) {
                    Text(recipe.title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                    Spacer(Modifier.height(4.dp))
                    Text(recipe.category, fontWeight = FontWeight.Light, fontSize = 14.sp, color = Color.White)
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    NutritionItem(value = "${recipe.calories}", label = "Calories")
                    NutritionItem(value = "${recipe.fat}gr", label = "Fat")
                    NutritionItem(value = "${recipe.protein}gr", label = "Protein")
                    NutritionItem(value = "${recipe.carbs}gr", label = "Carbs")
                }
            }
        }
    }
}
