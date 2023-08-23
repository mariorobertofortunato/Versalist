package com.evenclose.versalistpro.presentation.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.evenclose.versalistpro.presentation.navigation.Screens
import com.evenclose.versalistpro.presentation.ui.theme.white

@Composable
fun MainScreenHeader(
    navController: NavController,
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp
            )
    ) {
        Text(
            text = "Versalist Pro",
            //style = Typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = white,
        )
        IconButton(
            onClick = {
                //navController.navigate(Screens.UserSettingsScreen.route)
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = "Setting Icon",
                tint = white
            )
        }
    }
}