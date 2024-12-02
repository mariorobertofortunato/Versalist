package com.evenclose.versalist.app.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.theme.light
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NewListFab() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = R.string.new_list),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = light,
        )
    }
}

@Composable
@Preview
private fun NewListFabPreview() {
    NewListFab()
}