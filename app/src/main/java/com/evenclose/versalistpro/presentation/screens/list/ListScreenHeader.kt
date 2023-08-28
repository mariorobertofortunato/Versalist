package com.evenclose.versalistpro.presentation.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.evenclose.versalistpro.presentation.ui.theme.white

@Composable
fun ListScreenHeader(
    navController: NavController,
    listName: String
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                //horizontal = 12.dp,
                vertical = 8.dp
            )
    ) {
        IconButton(
            onClick = {
                navController.navigateUp()
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Arrow Back Icon",
                tint = white
            )
        }
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
        ) {
            Text(
                text = listName,
                fontSize =  32.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                color = white,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(
            onClick = {
                //navController.navigate(Screens.UserSettingsScreen.route)
                // TODO make a dropdown lkkike in the main list screen
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

@Preview
@Composable
fun ListScreenHeaderPreview(){
    //ListScreenHeader(listName = "Lista con un nome molto molto lungo")
}