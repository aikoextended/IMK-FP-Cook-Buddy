package com.example.cookbuddy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun RecipeDetailScreen(recipe: Recipe, navController: NavController) {
    var isFavorite by remember { mutableStateOf(FavoriteManager.isFavorite(recipe)) }


    Box(modifier = Modifier.fillMaxSize()) {
        // Gambar di atas
        Image(
            painter = painterResource(id = recipe.imageRes),
            contentDescription = recipe.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .align(Alignment.TopCenter)
        )

        // Tombol Back dan Favorite
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.7f), shape = CircleShape)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            IconButton(
                onClick = {
                    FavoriteManager.toggleFavorite(recipe)
                    isFavorite = FavoriteManager.isFavorite(recipe)
                },
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.7f), shape = CircleShape)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else Color.Black
                )
            }
        }

        // Isi konten scrollable
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 190.dp, bottom = 24.dp)
                .background(Color.White)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
        ) {
            item {
                Column(modifier = Modifier.padding(16.dp)) {

                    // Judul dan Tombol
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(recipe.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(recipe.category, color = Color.Gray, fontSize = 14.sp)
                            Spacer(modifier = Modifier.height(12.dp))
                        }

                        Button(
                            onClick = {
                                navController.navigate("step_by_step/${recipe.id}")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C0F0F)),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .height(45.dp)
                                .width(145.dp),
                            contentPadding = PaddingValues(horizontal = 4.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.play),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                "Start Cooking",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }

                    // Waktu & Porsi
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.time),
                            contentDescription = null,
                            tint = Color(0xFF4F070D),
                            modifier = Modifier.size(16.dp)
                        )
                        Text("${recipe.time} mins", fontSize = 13.sp, color = Color(0xFF4F070D))
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.servings),
                            contentDescription = null,
                            tint = Color(0xFF4F070D),
                            modifier = Modifier.size(16.dp)
                        )
                        Text("${recipe.servings} servings", fontSize = 13.sp, color = Color(0xFF4F070D))
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Gizi
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
//                        Text("${recipe.time} mins", fontSize = 13.sp, color = Color(0xFF4F070D))
//                        Text("${recipe.servings} servings", fontSize = 13.sp, color = Color(0xFF4F070D))
                        Text("${recipe.calories} Calories", fontSize = MaterialTheme.typography.bodySmall.fontSize)
                        Text("${recipe.fat} gr Fat", fontSize = MaterialTheme.typography.bodySmall.fontSize)
                        Text("${recipe.protein} gr Protein", fontSize = MaterialTheme.typography.bodySmall.fontSize)
                        Text("${recipe.carbs} gr Carbs", fontSize = MaterialTheme.typography.bodySmall.fontSize)
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Ingredients
                    Text("Ingredients", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    recipe.ingredients.forEach { ingredient ->
                        Text(
                            text = "• $ingredient",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 15.sp,
                                lineHeight = 28.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Instructions
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text("Instructions", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        recipe.instructions.forEachIndexed { index, instruction ->
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color.White)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .size(28.dp)
                                            .background(
                                                color = Color(0xFF4C0F0F),
                                                shape = CircleShape
                                            )
                                    ) {
                                        Text(
                                            text = "${index + 1}",
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Text(
                                        text = instruction,
                                        style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Reviews
                    Text("Reviews", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(16.dp))

                    recipe.reviews.forEach { review ->

                        Card(
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                // Header: Avatar, Username, Rating
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(id = review.avatarRes),
                                        contentDescription = "Avatar",
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                    )

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column {
                                        Text(review.userName, fontWeight = FontWeight.Bold, fontSize = 15.sp)

                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            repeat(review.rating.toInt()) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.ic_star),
                                                    contentDescription = "Star",
                                                    tint = Color(0xFFFFD700),
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text("${review.rating}", fontSize = 12.sp, color = Color.Gray)
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                // Comment
                                Text(
                                    review.comment,
                                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp)
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                // Review Image
                                Image(
                                    painter = painterResource(id = review.reviewImageRes),
                                    contentDescription = "Review Image",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                // Review Time
                                Text(review.timeAgo, fontSize = 12.sp, color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    }
}
