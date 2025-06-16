package com.example.cookbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cookbuddy.ui.theme.CookBuddyTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import androidx.navigation.NavType
import androidx.navigation.navArgument


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.Main.route, builder = {
                composable(Screen.Landing.route){
                    Landing(navController)
                }
                composable(Screen.Register.route){
                    Register(navController)
                }
                composable(Screen.Login.route){
                    Login(navController)
                }
//                composable(Screen.Home.route){
//                    Homepage(navController)
//                }
                composable(Screen.Main.route){
                    MainScreen(navController)
                }
                composable("detail/{recipeId}") { backStackEntry ->
                    val recipeId = backStackEntry.arguments?.getString("recipeId")
                    val recipe = allRecipes.find { it.id.toString() == recipeId }
                    if (recipe != null) {
                        RecipeDetailScreen(recipe, navController)
                    }
                }
                composable(
                    "step_by_step/{recipeId}",
                    arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: return@composable
                    val recipe = allRecipes.find { it.id == recipeId } ?: return@composable
                    StepByStepScreen(recipe = recipe, navController = navController)
                }
                composable(
                    "done_screen/{recipeTitle}",
                    arguments = listOf(navArgument("recipeTitle") { type = NavType.StringType })
                ) { backStackEntry ->
                    val recipeTitle = backStackEntry.arguments?.getString("recipeTitle") ?: ""
                    DoneScreen(recipeTitle = recipeTitle, navController = navController)
                }
            } )
        }
    }
}




