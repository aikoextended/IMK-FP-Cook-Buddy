package com.example.cookbuddy

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Landing(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF4F070D)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logofix),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)
        )
        Spacer(
            modifier = Modifier.height(25.dp)
        )
        Text(
            text = "Say Hello to",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(
            modifier = Modifier.height(2.dp)
        )
        Text(
            text = "Cook Buddy",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(
            modifier = Modifier.height(18.dp)
        )
        Text(
            text = "Your personal cooking assistant with voice recognition for easy and hands-free cooking.",
            color = Color.White,
            modifier = Modifier.width(300.dp),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
        Spacer(
            modifier = Modifier.height(160.dp)
        )
        Button(
            onClick = {navController.navigate("register_page")},
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFF4F070D),
                containerColor = Color.White
            ),
            modifier = Modifier.width(350.dp).height(45.dp)
        ) {
            Text(
                text = "Let's Get Started",
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
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                text = "Log in!",
                color = Color.White,
                modifier = Modifier.clickable {navController.navigate("login_page")},
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}