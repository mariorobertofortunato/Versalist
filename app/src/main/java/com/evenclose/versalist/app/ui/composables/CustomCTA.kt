package com.evenclose.versalist.app.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
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
fun CustomCTA(
    text: String,
    onClick: () -> Unit
) {

    FloatingActionButton(
        containerColor = primaryBlack_Dark,
        contentColor = primaryWhite,
        shape = CircleShape,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, primaryWhite, CircleShape),
        onClick = {
            onClick()
        }
    ) {
        Text(
            text = text,
            color = primaryWhite,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}