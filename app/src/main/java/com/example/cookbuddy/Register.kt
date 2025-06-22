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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
import androidx.navigation.NavController

@Composable
fun Register(navController: NavController){
    var username by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var password2 by remember {
        mutableStateOf("")
    }
    val icon_user: Painter = painterResource(id = R.drawable.ic_user)
    val icon_email: Painter = painterResource(id = R.drawable.ic_mail)
    val icon_password: Painter = painterResource(id = R.drawable.ic_password)
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(
            modifier = Modifier.height(30.dp)
        )
        Column(
            modifier = Modifier.width(350.dp), // Lebar disamakan
            horizontalAlignment = Alignment.Start // Image dan Text sejajar kiri
        ) {
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.back_final),
                contentDescription = "Back",
                modifier = Modifier.size(24.dp).width(350.dp).clickable {navController.navigate(Screen.Landing.route)}
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Text(
                text = "Create a New Account",
                color = Color.Black,
//            modifier = Modifier.width(350.dp),
//            textAlign = TextAlign.Start,
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
        Spacer(
            modifier = Modifier.height(380.dp)
        )
        TextField(
            value = username,
            onValueChange = {username = it},
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
                    text = "Username",
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
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        TextField(
            value = email,
            onValueChange = {email = it},
            singleLine = true,
            leadingIcon = {
                Image(
                    painter = icon_email,
                    contentDescription = "E-mail icon",
                    modifier = Modifier.size(20.dp)
                )
            },
            placeholder = {
                Text(
                    text = "E-mail",
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
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        TextField(
            value = password,
            onValueChange = {password = it},
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
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        TextField(
            value = password2,
            onValueChange = {password2 = it},
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
                    text = "Re-enter password",
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
        Spacer(
            modifier = Modifier.height(48.dp)
        )
        Button(
            onClick = {navController.navigate(Screen.Main.route)},
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color(0xFF4F070D)
            ),
            modifier = Modifier.width(350.dp).height(45.dp)
        ) {
            Text(
                text = "Register",
                fontSize = 16.sp
            )
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account? ",
                color = Color(0xFF4F070D),
                fontSize = 16.sp
            )
            Text(
                text = "Log in!",
                color = Color(0xFF4F070D),
                modifier = Modifier.clickable {navController.navigate(Screen.Login.route)},
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}