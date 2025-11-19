package com.evenclose.versalist.app.ui.composables.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evenclose.versalist.R
import com.evenclose.versalist.app.compositions.LocalCompositionMainScreen
import com.evenclose.versalist.app.contracts.MainScreenEvent.DeleteMainListItem
import com.evenclose.versalist.app.contracts.MainScreenEvent.HidePopup
import com.evenclose.versalist.app.ui.theme.primaryBlack_Dark
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.data.model.InnerListItem
import com.evenclose.versalist.data.model.MainListItem

@Composable
fun DeleteItemDialog(
    mainListItem: MainListItem? = null,
    innerListItem: InnerListItem? = null
) {

    val eventTunnel = LocalCompositionMainScreen.current
    val itemName = mainListItem?.name ?: innerListItem?.name

    Text(
        text = stringResource(id = R.string.are_you_sure),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.headlineSmall,
        color = primaryWhite,
        fontWeight = FontWeight.Bold,
    )

    Text(
        text = itemName + " " + stringResource(id = R.string.will_be_permanently_deleted),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge,
        color = primaryWhite,
        fontWeight = FontWeight.Bold,
    )
    Spacer(
        modifier = Modifier.height(12.dp)
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {

        TextButton(
            modifier = Modifier
                .weight(1f)
                .background(primaryBlack_Dark, CircleShape)
                .border(1.dp, primaryWhite, CircleShape),
            onClick = {
                eventTunnel(HidePopup)
            }
        ) {
            Text(
                text = stringResource(id = R.string.cancel),
                fontSize = 16.sp,
                color = primaryWhite,
                fontWeight = FontWeight.Bold,
            )
        }
        TextButton(
            modifier = Modifier
                .weight(1f)
                .background(primaryBlack_Dark, CircleShape)
                .border(1.dp, primaryWhite, CircleShape),
            onClick = {
                val deleteItemId = mainListItem?.id ?: (innerListItem?.id ?: 0)
                eventTunnel(DeleteMainListItem(deleteItemId))
            }
        ) {
            Text(
                text = stringResource(id = R.string.confirm),
                fontSize = 16.sp,
                color = primaryWhite,
                fontWeight = FontWeight.Bold,
            )
        }

    }

}