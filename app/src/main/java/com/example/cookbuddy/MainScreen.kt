package com.example.cookbuddy

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
                shadowElevation = 20.dp, // Tambah bayangan di atas navbar
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp), // Sudut atas melengkung
//                color = Color.White // Background navbar
            ){
                NavigationBar(
                    containerColor = Color.White
                ) {
                    navItemList.forEachIndexed { index, navItem ->
                        NavigationBarItem(
                            selected = selectedIndex == index ,
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
                            label =  {
                                Text(text = navItem.label)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFF4F070D),    // Warna ikon terpilih
                                unselectedIconColor = Color.Gray, // Warna ikon tidak terpilih
                                selectedTextColor = Color(0xFF4F070D),    // Warna teks terpilih
                                unselectedTextColor = Color.Gray, // Warna teks tidak terpilih
                                indicatorColor = Color.White  // Warna background item terpilih
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex)
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex : Int) {
    when(selectedIndex){
        0 -> Homepage()
        1 -> HistoryPage()
        2 -> FavoritePage()
        3 -> ProfilePage()
    }
}