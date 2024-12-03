package com.evenclose.versalist.app.ui.composables.dialog.privacydialog

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.theme.primaryGreen_Light
import com.evenclose.versalist.app.ui.theme.primaryWhite

@Composable
fun PrivacyDialog(
    onDismiss: () -> Unit
) {

    val context = LocalContext.current

    Dialog(
        onDismissRequest = onDismiss
    ){
        Box(
            modifier = Modifier.offset(y = (-64).dp)
        ){
            Column(
                modifier = Modifier
            ){
                Spacer(
                    modifier = Modifier
                        .height(100.dp)
                )
                Box(
                    modifier = Modifier
                        .background(primaryGreen_Light, RoundedCornerShape(16.dp))
                        .border(2.dp, primaryWhite, RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                    ){
                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )
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
                                .padding(top = 16.dp)
                        )
                        Text(
                            text = "Privacy Policy",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth()
                                .clickable {
                                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mariorobertofortunato.com/versalist_pro_privacy_policy.html")))
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
                                .padding(top = 16.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            TextButton(
                                onClick = {
                                    onDismiss()
                                }
                            ) {
                                Text(
                                    text = stringResource(id = R.string.dismiss),
                                    fontSize = 16.sp,
                                    color = primaryWhite,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(top = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
            PrivacyDialogHeaderImage(
                modifier = Modifier
                    .size(170.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }
}