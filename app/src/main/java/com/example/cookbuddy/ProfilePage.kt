package com.example.cookbuddy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.verticalScroll

@Composable
fun ProfilePage(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()) 
    ) {
        // Add some padding at the top
        Spacer(modifier = Modifier.height(36.dp))

        // Title "My Profile" in the center
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "My Profile",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Foto profile dan Change photo di Tengah
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar1),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Change photo",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        ProfileSectionTitle("Account")
        ProfileItem(iconId = R.drawable.ic_user, text = "Username")
        ProfileItem(iconId = R.drawable.ic_mail, text = "E-mail")
        ProfileItem(iconId = R.drawable.ic_password, text = "Password")

        Spacer(modifier = Modifier.height(24.dp))

        ProfileSectionTitle("Display")
        ProfileItem(iconId = R.drawable.ic_language, text = "Language")
        ProfileItem(iconId = R.drawable.ic_theme, text = "Theme")

        Spacer(modifier = Modifier.height(24.dp))

        ProfileSectionTitle("Other")
        ProfileItem(iconId = R.drawable.ic_logout, text = "Log Out", isLogout = true)

        
        Spacer(modifier = Modifier.height(80.dp)) 
    }
}


@Composable
fun ProfileItem(
    iconId: Int,
    text: String,
    isLogout: Boolean = false
) {

    val leftColor = if (isLogout) Color(0xFFFFF4EC) else Color(0xFFFFF4EC)
    val rightColor = if (isLogout) Color(0xFFFFF4EC) else Color(0xFFFFF4EC)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { },
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .background(rightColor)
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .background(leftColor)
                    .padding(16.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = text,
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            if (!isLogout) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_expand),
                    contentDescription = "Go to $text",
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun ProfileSectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 14.sp,
        color = Color.Gray,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}
