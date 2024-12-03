package com.evenclose.versalist.app.ui.composables.dialog.helpdialog.listscreenhelpdialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.composables.CustomCTA
import com.evenclose.versalist.app.ui.composables.dialog.helpdialog.HelpDialogHeaderImage
import com.evenclose.versalist.app.ui.theme.primaryGreen_Light
import com.evenclose.versalist.app.ui.theme.primaryWhite

@Composable
fun ListScreenHelpDialog(
    onDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = onDismiss
    ) {

        Box(
            modifier = Modifier.offset(y = (-40).dp)
        ) {
            Column(
                modifier = Modifier
            ) {
                Spacer(
                    modifier = Modifier
                        .height(100.dp)
                )
                Box(
                    modifier = Modifier
                        .background(primaryGreen_Light, RoundedCornerShape(16.dp))
                        .border(2.dp, primaryWhite, RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                    ) {
                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )
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
                            text = stringResource(id = R.string.list_screen_help_text_1),
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
                            text = stringResource(id = R.string.list_screen_help_text_2),
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(top = 16.dp)
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
                            text = stringResource(id = R.string.list_screen_help_text_3),
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(top = 16.dp)
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
                        CustomCTA(
                            text = stringResource(id = R.string.got_it),
                            onClick = {
                                onDismiss()
                            }
                        )
                    }
                }
            }
            HelpDialogHeaderImage(
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }
}