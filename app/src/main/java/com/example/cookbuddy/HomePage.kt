package com.example.cookbuddy

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import java.util.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

val com.example.cookbuddy.Recipe.averageRating: Double
    get() = if (reviews.isNotEmpty()) reviews.map { it.rating }.average() else 0.0

@Composable
fun Homepage(navController: NavController,  onFilteringStateChange: (Boolean) -> Unit) {
    val categories = listOf("All", "Asian", "Western", "Drinks", "Dessert", "etc")
    val selectedCategory = remember { mutableStateOf("All") }
    val searchQuery = remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    val context = LocalContext.current

    var isFilterVisible by remember { mutableStateOf(false) }
    var selectedSortOption by remember { mutableStateOf<String?>(null) } // Awalnya null
    var tempSelectedSortOption by remember { mutableStateOf<String?>(null) } // Untuk preview sementara

    var selectedMinCalories by remember { mutableStateOf(0) }
    var selectedMaxCalories by remember { mutableStateOf(Int.MAX_VALUE) }

    var tempMinCalories by remember { mutableStateOf(0) }
    var tempMaxCalories by remember { mutableStateOf(Int.MAX_VALUE) }



    // Function to start speech recognition - MOVED HERE
    val startSpeechRecognition: (ActivityResultLauncher<Intent>) -> Unit = { launcher ->
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to search recipes")
        }
        launcher.launch(intent)
    }

    // Launcher for speech recognition
    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val data = result.data
            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            results?.getOrNull(0)?.let { recognizedText ->
                searchQuery.value = recognizedText
                isSearching = recognizedText.isNotEmpty()
            }
        }
    }

    // Permission request launcher
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startSpeechRecognition(speechRecognizerLauncher)
        }
    }

    val categoryIconMap = mapOf(
        "All" to ImageVector.vectorResource(R.drawable.ic_all),
        "Asian" to ImageVector.vectorResource(R.drawable.ic_asian),
        "Western" to ImageVector.vectorResource(R.drawable.ic_western),
        "Drinks" to ImageVector.vectorResource(R.drawable.ic_drinks),
        "Dessert" to ImageVector.vectorResource(R.drawable.ic_dessert),
        "etc" to ImageVector.vectorResource(R.drawable.ic_etc)
    )

    val filteredRecipes = remember(
        selectedCategory.value,
        searchQuery.value,
        selectedSortOption,
        selectedMinCalories,
        selectedMaxCalories
    ) {
        val filtered = when {
            searchQuery.value.isNotEmpty() -> {
                allRecipes.filter { recipe ->
                    recipe.title.contains(searchQuery.value, ignoreCase = true) ||
                            recipe.category.contains(searchQuery.value, ignoreCase = true)
                }
            }
            selectedCategory.value == "All" -> allRecipes
            else -> allRecipes.filter { it.category == selectedCategory.value }
        }

        val calorieFiltered = filtered.filter { it.calories in selectedMinCalories..selectedMaxCalories }

        when (selectedSortOption) {
            "Most liked" -> calorieFiltered.sortedByDescending { it.likes }
            "Least liked" -> calorieFiltered.sortedBy { it.likes }
            "Highest rated" -> calorieFiltered.sortedByDescending { it.averageRating }
            "Lowest rated" -> calorieFiltered.sortedBy { it.averageRating }
            else -> calorieFiltered
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Search Box
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .background(Color(0xFFF6F1EB), shape = RoundedCornerShape(15.dp)) // Rounded 15dp
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

                    BasicTextField(
                        value = searchQuery.value,
                        onValueChange = {
                            searchQuery.value = it
                            isSearching = it.isNotEmpty()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 2.dp),
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
                        Icon(
                            painter = painterResource(id = R.drawable.ic_mic),
                            contentDescription = "Voice Search",
                            tint = Color(0xFF4C0F0F),
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    when {
                                        ContextCompat.checkSelfPermission(
                                            context,
                                            Manifest.permission.RECORD_AUDIO
                                        ) == PackageManager.PERMISSION_GRANTED -> {
                                            startSpeechRecognition(speechRecognizerLauncher)
                                        }
                                        else -> {
                                            requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                                        }
                                    }
                                }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Filter Button (Rectangle Rounded 15dp)
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        if (selectedSortOption != null) Color(0xFF4C0F0F) else Color(0xFFF6F1EB),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .clickable {
                        tempSelectedSortOption = selectedSortOption
                        isFilterVisible = true
                        onFilteringStateChange(true) // Sembunyikan navbar
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "Filter",
                    modifier = Modifier.size(24.dp),
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                        if (selectedSortOption != null) Color.White else Color(0xFF4C0F0F)
                    )
                )
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
                            .padding(end = 12.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) Color(0xFF4C0F0F) else Color(0xFFF6F1EB))
                            .clickable {
                                selectedCategory.value = category
                                searchQuery.value = ""
                            }
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                            .width(50.dp),
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

    if (isFilterVisible) {
        FilterModal(
            onClose = {
                isFilterVisible = false
                onFilteringStateChange(false)
            },
            selectedSortOption = tempSelectedSortOption,
            onSortChange = { tempSelectedSortOption = it },
            minCalories = tempMinCalories,
            maxCalories = tempMaxCalories,
            onMinCaloriesChange = { tempMinCalories = it },
            onMaxCaloriesChange = { tempMaxCalories = it },
            onClear = {
                tempSelectedSortOption = null
                tempMinCalories = 0
                tempMaxCalories = Int.MAX_VALUE
            },
            onApply = {
                selectedSortOption = tempSelectedSortOption
                selectedMinCalories = tempMinCalories
                selectedMaxCalories = tempMaxCalories
                isFilterVisible = false
                onFilteringStateChange(false)
            }
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
                    Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                    Spacer(Modifier.height(4.dp))
                    Text(category, fontWeight = FontWeight.Light, fontSize = 14.sp, color = Color.White)
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
                    NutritionItem(value = "$calories", label = "Calories")
                    NutritionItem(value = "${fat}gr", label = "Fat")
                    NutritionItem(value = "${protein}gr", label = "Protein")
                    NutritionItem(value = "${carbs}gr", label = "Carbs")
                }
            }
        }
    }
}

@Composable
fun NutritionItem(value: String, label: String) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = value,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = label,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            color = Color.Gray
        )
    }
}
