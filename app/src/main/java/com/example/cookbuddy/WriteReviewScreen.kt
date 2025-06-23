package com.example.cookbuddy

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteReviewScreen(recipeTitle: String, recipeId: Int, navController: NavController) {
    var reviewText by remember { mutableStateOf("") }
    var selectedRating by remember { mutableStateOf(0) } // Untuk menyimpan rating yang dipilih

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Back Button (sama seperti DoneScreen)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .background(Color.White, shape = CircleShape)
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
                text = recipeTitle,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 32.dp, end = 32.dp, top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Rate the Recipe",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Stars Row - Interaktif
            Row {
                for (i in 1..5) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "Star $i",
                        tint = if (i <= selectedRating) Color.Yellow else Color.Gray,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                            .clickable { selectedRating = i } // Set rating ketika diklik
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Upload Photo Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .border(1.dp, Color(0xFF4C0F0F), RoundedCornerShape(8.dp))
                    .clickable { /* No function, UI only */ },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_upload),
                        contentDescription = "Upload",
                        tint = Color(0xFF4C0F0F),
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Click here to upload the Photos",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Text Field
            Text(
                text = "Tell us more!",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            BasicTextField(
                value = reviewText,
                onValueChange = { reviewText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color(0xFFF3F4F6), RoundedCornerShape(8.dp))
                    .padding(16.dp),
                decorationBox = { innerTextField ->
                    if (reviewText.isEmpty()) {
                        Text(
                            text = "review here!",
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
            )
        }

        // Submit Button
        Button(
            onClick = {
                HistoryManager.markAsReviewed(recipeId)
                navController.navigate("detail/$recipeId") {
                    popUpTo(Screen.Main.route) { inclusive = false }
                    launchSingleTop = true
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp)
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4C0F0F),
                contentColor = Color.White
            )
        ) {
            Text("Submit", fontWeight = FontWeight.Bold)
        }
    }
}
