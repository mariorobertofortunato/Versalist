package com.evenclose.versalist.app.ui.composables.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.composables.VersalistFab
import com.evenclose.versalist.app.ui.theme.primaryWhite

@Composable
fun CtaRow(
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {

    Row(
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        VersalistFab(
            modifier = Modifier.weight(1f),
            onClick = {
                onCancel()
            }
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = "Cancel Icon",
                    tint = primaryWhite
                )
                Text(
                    text = stringResource(id = R.string.cancel),
                    color = primaryWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }

        VersalistFab(
            modifier = Modifier.weight(1f),
            onClick = {
                onConfirm()
            }
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = "Confirm Icon",
                    tint = primaryWhite
                )
                Text(
                    text = stringResource(id = R.string.confirm),
                    color = primaryWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
@Preview
fun CtaRowPreview() {
    CtaRow(
        onCancel = {},
        onConfirm = {}
    )
}