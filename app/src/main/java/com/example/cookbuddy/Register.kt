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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun Register(navController: NavController) {
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
                    // Clear back stack so user can't go back to register
                    popUpTo(Screen.Register.route) { inclusive = true }
                }
            }
            is FirebaseAuthViewModel.AuthState.Error -> {
                snackbarHostState.showSnackbar(state.message)
                authViewModel.resetState()
            }
            else -> {} // Handle other states if needed
        }
    }
    
    // User input states
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    
    // Add this state to track password mismatch error
    var passwordMismatch by remember { mutableStateOf(false) }
    
    val icon_user = painterResource(id = R.drawable.ic_user)
    val icon_password = painterResource(id = R.drawable.ic_password)
    
    // Main layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                    .clickable { navController.navigate(Screen.Landing.route) }
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Text(
                text = "Create Account",
                color = Color.Black,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 40.sp
            )
        }
        
        Spacer(modifier = Modifier.height(40.dp))
        
        // Email field
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
                    text = "Email",
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
            )
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
                fontSize = 16.sp
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
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // Confirm Password field
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            singleLine = true,
            leadingIcon = {
                Image(
                    painter = icon_password,
                    contentDescription = "Confirm password icon",
                    modifier = Modifier.size(20.dp)
                )
            },
            placeholder = {
                Text(
                    text = "Confirm Password",
                    color = Color.Black.copy(alpha = 0.5f)
                )
            },
            textStyle = TextStyle(
                color = Color.Black.copy(alpha = 0.5f),
                fontSize = 16.sp
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
        
        Spacer(modifier = Modifier.height(40.dp))
        
        // Register button - uses Firebase auth
        Button(
            onClick = {
                if (email.isNotBlank() && password.isNotBlank()) {
                    if (password == confirmPassword) {
                        authViewModel.register(email, password)
                    } else {
                        // Just set the state, don't call LaunchedEffect here
                        authViewModel.resetState()
                        passwordMismatch = true
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color(0xFF4F070D)
            ),
            modifier = Modifier
                .width(350.dp)
                .height(45.dp)
        ) {
            if (authState.value is FirebaseAuthViewModel.AuthState.Loading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text(
                    text = "Register",
                    fontSize = 16.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Already have an account? ",
                color = Color(0xFF4F070D),
                fontSize = 16.sp
            )
            Text(
                text = "Log In!",
                color = Color(0xFF4F070D),
                modifier = Modifier.clickable { navController.navigate(Screen.Login.route) },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
    
    // Add SnackbarHost for error messages
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier.padding(16.dp)
    )
}