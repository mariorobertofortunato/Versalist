package com.evenclose.versalistpro.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evenclose.versalistpro.presentation.ui.theme.onLight

@Composable
fun EmptyListPlaceholder(type: String) {
    // TODO sistemare hardcoded strings

    val text = when (type) {
        "mainScreenPlaceholder" -> {
            "You have no lists yet, add one (from the button below)"
        }
        "listScreenPlaceholder" -> {
            "This list has no items, start adding one"
        }
        else -> {
            "ERROR"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Icon(
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterHorizontally),
            imageVector = Icons.Outlined.Warning,
            contentDescription = "Warning icon",
            tint = onLight
        )
        Text(
            text = text,
            textAlign = TextAlign.Center,
            // TODO typography style, this was title medium
            //  style = ,
            color = onLight,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}


@Preview
@Composable
fun EmptyListPlaceholderPreview() {
    EmptyListPlaceholder("roomsPlaceHolder")
}