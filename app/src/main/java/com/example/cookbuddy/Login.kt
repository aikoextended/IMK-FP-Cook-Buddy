package com.example.cookbuddy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun Login(navController: NavController) {
    // Get the Firebase auth view model
    val authViewModel: FirebaseAuthViewModel = viewModel()
    val authState = authViewModel.authState.collectAsState()
    
    // For showing error messages
    val snackbarHostState = remember { SnackbarHostState() }
    
    // Handle authentication state changes
    LaunchedEffect(authState.value) {
        when (val state = authState.value) {
            is FirebaseAuthViewModel.AuthState.Authenticated -> {
                navController.navigate(Screen.Main.route) {
                    // Clear back stack so user can't go back to login
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }
            is FirebaseAuthViewModel.AuthState.Error -> {
                snackbarHostState.showSnackbar(state.message)
                authViewModel.resetState()
            }
            else -> {} // Handle other states if needed
        }
    }
    
    // User input states - renamed username to email for Firebase
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    
    val icon_user: Painter = painterResource(id = R.drawable.ic_user)
    val icon_password: Painter = painterResource(id = R.drawable.ic_password)
    
    // Main layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(30.dp))
        
        Column(
            modifier = Modifier.width(350.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            
            Image(
                painter = painterResource(id = R.drawable.back_final),
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .width(350.dp)
                    .clickable { navController.navigate(Screen.Landing.route) }
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Text(
                text = "Welcome Back",
                color = Color.Black,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 40.sp
            )
        }
    }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(380.dp))
        
        // Email field (was username)
        TextField(
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            leadingIcon = {
                Image(
                    painter = icon_user,
                    contentDescription = "User icon",
                    modifier = Modifier.size(20.dp)
                )
            },
            placeholder = {
                Text(
                    text = "Email", // Changed from Username to Email
                    color = Color.Black.copy(alpha = 0.5f)
                )
            },
            textStyle = TextStyle(
                color = Color.Black.copy(alpha = 0.5f),
                fontSize = 16.sp
            ),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.width(350.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0x66FCE5CD),
                unfocusedContainerColor = Color(0x66FCE5CD),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // Password field
        TextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            leadingIcon = {
                Image(
                    painter = icon_password,
                    contentDescription = "Password icon",
                    modifier = Modifier.size(20.dp)
                )
            },
            placeholder = {
                Text(
                    text = "Password",
                    color = Color.Black.copy(alpha = 0.5f)
                )
            },
            textStyle = TextStyle(
                color = Color.Black.copy(alpha = 0.5f),
                fontSize = 18.sp
            ),
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.width(350.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0x66FCE5CD),
                unfocusedContainerColor = Color(0x66FCE5CD),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        
        Spacer(modifier = Modifier.height(200.dp))
        
        // Login button - updated to use Firebase auth
        Button(
            onClick = {
                if (email.isNotBlank() && password.isNotBlank()) {
                    authViewModel.login(email, password)
                }
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color(0xFF4F070D)
            ),
            modifier = Modifier.width(350.dp).height(45.dp)
        ) {
            if (authState.value is FirebaseAuthViewModel.AuthState.Loading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text(
                    text = "Log In",
                    fontSize = 16.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Don't have an account? ",
                color = Color(0xFF4F070D),
                fontSize = 16.sp
            )
            Text(
                text = "Register!",
                color = Color(0xFF4F070D),
                modifier = Modifier.clickable { navController.navigate(Screen.Register.route) },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
    
    Text(
        text = "Forgot password?",
        color = Color(0xFF4F070D),
        modifier = Modifier
            .offset(x = 265.dp, y = 530.dp)
            .clickable { /* TODO: Implement forgot password */ }
    )
    
    // Add SnackbarHost for error messages
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier.padding(16.dp)
    )
}