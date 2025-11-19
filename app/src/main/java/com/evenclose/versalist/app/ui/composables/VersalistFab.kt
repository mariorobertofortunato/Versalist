package com.evenclose.versalist.app.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evenclose.versalist.app.ui.theme.primaryBlack_Dark
import com.evenclose.versalist.app.ui.theme.primaryWhite

@Composable
fun VersalistFab(
    text: String,
    onClick: () -> Unit,
) {

    FloatingActionButton(
        containerColor = primaryBlack_Dark,
        contentColor = primaryWhite,
        shape = CircleShape,
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .imePadding()
            .padding(16.dp)
            .border(1.dp, primaryWhite, CircleShape)
    ) {
        Text(
            text = text,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = primaryWhite,
        )
    }
}

@Composable
fun VersalistFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {

    FloatingActionButton(
        containerColor = primaryBlack_Dark,
        contentColor = primaryWhite,
        shape = CircleShape,
        onClick = {
            onClick()
        },
        modifier = modifier
            .fillMaxWidth()
            .imePadding()
            .padding(16.dp)
            .border(1.dp, primaryWhite, CircleShape)
    ) {
        content()
    }
}