package com.example.cookbuddy

import android.widget.Space
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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

@Composable
fun RecipeDetailScreen(recipe: Recipe, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Gambar berada di atas, tidak ikut scroll
        Image(
            painter = painterResource(id = recipe.imageRes),
            contentDescription = recipe.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .align(Alignment.TopCenter)
        )

        // Tombol Back dan Favorite di atas gambar
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
                onClick = { /* TODO: Favorite logic */ },
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.7f), shape = CircleShape)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.Black
                )
            }
        }

        // Isi konten scrollable di bawah gambar
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 190.dp, bottom = 24.dp)
                .background(Color.White)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
        ) {
            item {
                Column(modifier = Modifier.padding(16.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Judul dan kategori
                        Column {
                            Text(recipe.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(recipe.category, color = Color.Gray, fontSize = 14.sp)

                            Spacer(modifier = Modifier.height(12.dp))
                        }

                        // Tombol Start Cooking
                        Button(
                            onClick = { /* TODO */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C0F0F)),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.height(45.dp).width(145.dp),
                            contentPadding = PaddingValues(horizontal = 4.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.play), contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Start Cooking", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                    }

                    // Waktu & porsi
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.time), contentDescription = null, tint = Color(0xFF4F070D), modifier = Modifier.size(16.dp))
                        Text("${recipe.time} mins", fontSize = 13.sp, color = Color(0xFF4F070D))
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(painter = painterResource(id = R.drawable.servings), contentDescription = null, tint = Color(0xFF4F070D), modifier = Modifier.size(16.dp))
                        Text("${recipe.servings} servings", fontSize = 13.sp, color = Color(0xFF4F070D))
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
                                lineHeight = 28.sp // atur tinggi baris sesuai kebutuhan
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Instructions
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        Text("Instructions", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        recipe.instructions.forEachIndexed { index, instruction ->
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Lingkaran dengan nomor
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .size(28.dp)
                                            .background(color = Color(0xFF4C0F0F), shape = CircleShape)
                                    ) {
                                        Text(
                                            text = "${index + 1}",
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(12.dp))

                                    // Teks instruksi
                                    Text(
                                        text = instruction,
                                        style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

