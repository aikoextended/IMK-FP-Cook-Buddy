package com.example.cookbuddy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.*
import com.example.cookbuddy.HistoryManager

@Composable
fun StepByStepScreen(recipe: Recipe, navController: NavController) {
    var selectedIndex by remember { mutableStateOf(-1) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            // Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp),
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

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = recipe.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            // Steps scrollable
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(recipe.instructionsWithImages) { index, step ->
                    val isSelected = selectedIndex == index

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedIndex = index },
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = if (isSelected)
                            BorderStroke(2.dp, Color(0xFFB31312))
                        else null
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Step ${index + 1}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF4C0F0F)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = step.first,
                                fontSize = 16.sp,
                                lineHeight = 22.sp
                            )

                            // Pastikan gambar tetap ditampilkan jika tersedia
                            if (step.second != null) {
                                Spacer(modifier = Modifier.height(12.dp))
                                Image(
                                    painter = painterResource(id = step.second!!),
                                    contentDescription = "Step Image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(180.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                )
                            }
                        }
                    }
                }
            }
        }

        // Bottom buttons
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /* TODO: Mic */ },
                modifier = Modifier
                    .size(52.dp)
                    .background(Color(0xFF4C0F0F), CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_mic),
                    contentDescription = "Mic",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            IconButton(
                onClick = { /* TODO: Speaker */ },
                modifier = Modifier
                    .size(52.dp)
                    .background(Color(0xFF4C0F0F), CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_speaker),
                    contentDescription = "Speaker",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            IconButton(
                onClick = {
                    HistoryManager.addOrUpdate(recipe, isFinished = true)
                    navController.navigate("done_screen/${recipe.title}/${recipe.id}")
                },
                modifier = Modifier
                    .size(52.dp)
                    .background(Color.White, CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_checklist),
                    contentDescription = "Finish",
                    tint = Color(0xFF4C0F0F),
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}
