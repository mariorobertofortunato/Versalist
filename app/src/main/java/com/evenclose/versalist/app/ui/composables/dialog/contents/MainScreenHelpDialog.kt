package com.evenclose.versalist.app.ui.composables.dialog.contents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.theme.primaryWhite

@Composable
fun MainScreenHelpDialog() {
    Text(
        text = stringResource(id = R.string.how_it_works),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth(),
        style = MaterialTheme.typography.headlineSmall,
        color = primaryWhite,
        fontWeight = FontWeight.Bold,
    )
    Text(
        text = stringResource(id = R.string.how_it_works_text),
        textAlign = TextAlign.Start,
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge,
        color = primaryWhite,
        fontWeight = FontWeight.Bold,
    )
    HorizontalDivider(
        color = primaryWhite,
        thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(top = 16.dp)
    )
    Text(
        text = stringResource(id = R.string.types_and_categories),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.headlineSmall,
        color = primaryWhite,
        fontWeight = FontWeight.Bold,
    )
    Text(
        text = stringResource(id = R.string.types_and_categories_text),
        textAlign = TextAlign.Start,
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge,
        color = primaryWhite,
        fontWeight = FontWeight.Bold,
    )
    HorizontalDivider(
        color = primaryWhite,
        thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(top = 16.dp)
    )
    Text(
        text = stringResource(id = R.string.navigating_your_lists),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.headlineSmall,
        color = primaryWhite,
        fontWeight = FontWeight.Bold,
    )
    Text(
        text = stringResource(id = R.string.navigating_your_lists_text),
        textAlign = TextAlign.Start,
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge,
        color = primaryWhite,
        fontWeight = FontWeight.Bold,
    )
    HorizontalDivider(
        color = primaryWhite,
        thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(vertical = 16.dp)
    )
}