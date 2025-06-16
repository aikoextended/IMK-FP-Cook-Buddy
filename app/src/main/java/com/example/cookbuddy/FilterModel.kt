package com.example.cookbuddy

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FilterModal(
    onClose: () -> Unit,
    selectedSortOption: String?,
    onSortChange: (String) -> Unit,
    minCalories: Int,
    maxCalories: Int,
    onMinCaloriesChange: (Int) -> Unit,
    onMaxCaloriesChange: (Int) -> Unit,
    onClear: () -> Unit,
    onApply: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.3f))
            .clickable(enabled = false) {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(24.dp)
        ) {
            // Header
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Sort & Filter",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "Close",
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterEnd)
                        .clickable { onClose() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sort by
            Text("Sort by", fontWeight = FontWeight.Bold)
            val sortOptions = listOf("Most liked", "Least liked", "Highest rated", "Lowest rated")
            sortOptions.forEach { option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSortChange(option) }
                        .padding(vertical = 0.dp)
                ) {
                    RadioButton(
                        selected = selectedSortOption == option,
                        onClick = { onSortChange(option) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF4C0F0F)
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(option)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Filter by
            Text("Filter by", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Calorie")
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = if (minCalories == 0) "" else minCalories.toString(),
                    onValueChange = { onMinCaloriesChange(it.toIntOrNull() ?: 0) },
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Transparent, shape = RoundedCornerShape(10.dp)),
                    singleLine = true,
                    placeholder = { Text("0") },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFF4C0F0F),
                        focusedBorderColor = Color(0xFF4C0F0F)
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))
                Text("-")
                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = if (maxCalories == Int.MAX_VALUE) "" else maxCalories.toString(),
                    onValueChange = { onMaxCaloriesChange(it.toIntOrNull() ?: Int.MAX_VALUE) },
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Transparent, shape = RoundedCornerShape(10.dp)),
                    singleLine = true,
                    placeholder = { Text("MAX") },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFF4C0F0F),
                        focusedBorderColor = Color(0xFF4C0F0F)
                    )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Clear all",
                color = Color(0xFF4C0F0F),
                modifier = Modifier.clickable { onClear() }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onApply() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C0F0F)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Apply", color = Color.White)
            }
            Spacer(modifier = Modifier.height(90.dp))
        }
    }
}
