package com.evenclose.versalist.app.ui.composables.dialog.contents

import android.content.Intent
import androidx.core.net.toUri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.theme.primaryWhite

@Composable
fun AboutDialog(
) {

    val context = LocalContext.current

    Text(
        text = stringResource(id = R.string.about_us),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth(),
        style = MaterialTheme.typography.headlineSmall,
        color = primaryWhite,
        fontWeight = FontWeight.Bold,
    )
    Text(
        text = stringResource(id = R.string.about_us_text),
        textAlign = TextAlign.Center,
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
    Text(
        text = "Link",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        "https://www.mariorobertofortunato.com".toUri()
                    )
                )
            },
        style = MaterialTheme.typography.bodyLarge,
        color = primaryWhite,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline
    )
    HorizontalDivider(
        color = primaryWhite,
        thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(vertical = 16.dp)
    )

}