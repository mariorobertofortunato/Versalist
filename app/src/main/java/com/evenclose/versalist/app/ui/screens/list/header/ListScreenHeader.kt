package com.evenclose.versalist.app.ui.screens.list.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.evenclose.versalist.app.ui.theme.primaryWhite

@Composable
fun ListScreenHeader(
    onNavigateUp: () -> Unit,
    listName: String
) {

    var expanded by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        IconButton(
            onClick = {
                onNavigateUp()
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBackIosNew,
                contentDescription = "Arrow Back Icon",
                tint = primaryWhite
            )
        }

        Text(
            text = listName,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            color = primaryWhite,
            overflow = TextOverflow.Ellipsis
        )

        Box {
            IconButton(
                onClick = {
                    expanded = true
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "Menu Icon",
                    tint = primaryWhite
                )
            }
            ListHeaderDropDown(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            )

        }


    }

}