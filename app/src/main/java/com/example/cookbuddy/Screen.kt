package com.example.cookbuddy

sealed class Screen(val route: String) {
    object Landing : Screen("landing_page")
    object Register : Screen("register_page")
    object Login : Screen("login_page")
    object Home : Screen("home_page")
}