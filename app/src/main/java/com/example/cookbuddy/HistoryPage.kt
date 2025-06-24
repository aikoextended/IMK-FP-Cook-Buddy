package com.example.cookbuddy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
fun HistoryPage(modifier: Modifier = Modifier, navController: NavController) {
    val history = HistoryManager.historyList

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(36.dp))

        // Title "History" in the center
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "History",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (history.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No Cooking History Found", color = Color.Gray)
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
//                    .padding(bottom = 120.dp) // space di bawah agar tidak mentok
            ) {
                items(history) { recipe ->
                    HistoryCard(recipe = recipe, onClick = {
                        when (recipe.status) {
                            RecipeStatus.ONGOING -> {
                                val fullRecipe = allRecipes.find { it.id == recipe.id }
                                if (fullRecipe != null) {
                                    navController.navigate("step_by_step/${fullRecipe.id}")
                                }
                            }
                            RecipeStatus.FINISHED_NOT_REVIEWED -> {
                                navController.navigate("write_review/${recipe.title}/${recipe.id}")
                            }
                            RecipeStatus.FINISHED_REVIEWED -> {
                                navController.navigate("detail/${recipe.id}")
                            }
                        }
                    })
                }
                item {
                    Spacer(modifier = Modifier.height(110.dp)) // Atur tinggi sesuai kebutuhan
                }
            }
        }
    }
}



@Composable
fun HistoryCard(recipe: RecipeHistory, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 6.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${recipe.title} (${recipe.category})",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = recipe.date,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(id = recipe.imageResId),
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            when (recipe.status) {
                RecipeStatus.ONGOING -> {
                    Text(
                        text = "On Going",
                        color = Color(0xFF8B0000),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                RecipeStatus.FINISHED_REVIEWED -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Finished",
                            color = Color.Gray
                        )
                        Text(
                            text = "Reviewed",
                            color = Color.Gray
                        )
                    }
                }
                RecipeStatus.FINISHED_NOT_REVIEWED -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Finished",
                            color = Color.Gray
                        )
                        Text(
                            text = "Not Reviewed",
                            color = Color(0xFF8B0000),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
