package com.evenclose.versalist.app.ui.composables.dialog

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
fun PrivacyDialog(
) {

    val context = LocalContext.current

    Text(
        text = stringResource(id = R.string.privacy),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth(),
        style = MaterialTheme.typography.headlineSmall,
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
        text = "Privacy Policy",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        "https://www.mariorobertofortunato.com/versalist_privacy_policy.html".toUri()
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