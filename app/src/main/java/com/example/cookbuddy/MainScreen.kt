package com.example.cookbuddy

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {

    val navItemList = listOf(
        NavItem("Home", R.drawable.home),
        NavItem("History", R.drawable.history),
        NavItem("Favorite", R.drawable.favorite),
        NavItem("Profile", R.drawable.profile)
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Surface(
                shadowElevation = 20.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            ) {
                NavigationBar(
                    containerColor = Color.White
                ) {
                    navItemList.forEachIndexed { index, navItem ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = navItem.iconRes),
                                    contentDescription = navItem.label,
                                    modifier = Modifier.size(36.dp)
                                )
                            },
                            label = {
                                Text(text = navItem.label)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFF4F070D),
                                unselectedIconColor = Color.Gray,
                                selectedTextColor = Color(0xFF4F070D),
                                unselectedTextColor = Color.Gray,
                                indicatorColor = Color.White
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(
            modifier = Modifier.padding(innerPadding),
            selectedIndex = selectedIndex,
            navController = navController // ✅ Hapus favoriteRecipes
        )
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    navController: NavController // ✅ Hapus favoriteRecipes dari parameter
) {
    when (selectedIndex) {
        0 -> Homepage(navController = navController)
        1 -> HistoryPage()
        2 -> FavoritePage(navController = navController) // ✅ Panggil tanpa favoriteRecipes
        3 -> ProfilePage()
    }
}
