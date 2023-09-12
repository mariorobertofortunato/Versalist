package com.evenclose.versalistpro.presentation.composables.dialog.languagedialog


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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.evenclose.versalistpro.R
import com.evenclose.versalistpro.presentation.ui.theme.onDark
import com.evenclose.versalistpro.presentation.ui.theme.secondary
import com.evenclose.versalistpro.presentation.ui.theme.secondaryContainer
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel
import com.evenclose.versalistpro.presentation.setLanguage

@Composable
fun LanguageDialog(
    viewModel: ListViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {

    val context = LocalContext.current
    val languageList = listOf(
        "Arabic",
        "Chinese (Simplified)",
        "Czech",
        "Danish",
        "Dutch",
        "English",
        "Finnish",
        "French",
        "German",
        "Greek",
        "Hebrew",
        "Hindi",
        "Indonesian",
        "Italian",
        "Japanese",
        "Korean",
        "Norwegian",
        "Polish",
        "Portuguese",
        "Russian",
        "Spanish",
        "Swedish",
        "Thai",
        "Turkish",
        "Vietnamese"
    )

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
                        .height(155.dp)
                )
                Box(
                    modifier = Modifier
                        .background(
                            color = secondaryContainer,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = onDark,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = 32.dp, vertical = 16.dp)
                    ) {
                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.choose_language),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.headlineSmall,
                            color = onDark,
                            fontWeight = FontWeight.Bold,
                        )
                        Divider(
                            color = onDark,
                            thickness = 1.dp,
                            modifier = Modifier
                                .fillMaxWidth(0.95f)
                                .padding(top = 16.dp, bottom = 4.dp)
                        )
                        Scaffold(
                            containerColor = secondaryContainer,
                            bottomBar = {
                                Divider(
                                    color = onDark,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .align(CenterHorizontally)
                                        .padding(top = 2.dp)
                                )
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    TextButton(
                                        onClick = {
                                            onDismiss()
                                        }
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.dismiss),
                                            fontSize = 16.sp,
                                            color = onDark,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                                .padding(top = 4.dp)
                                        )
                                    }
                                }
                            }
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .padding(it)
                            ) {
                                items(
                                    items = languageList,
                                    key = { language -> language }
                                ) { language ->
                                    Column(
                                        horizontalAlignment = CenterHorizontally,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable(
                                                onClick = {
                                                    val newLanguage = when (language) {
                                                        "Arabic" -> {
                                                            "ar"
                                                        }

                                                        "Chinese (Simplified)" -> {
                                                            "zh"
                                                        }

                                                        "Czech" -> {
                                                            "cs"
                                                        }

                                                        "Danish" -> {
                                                            "da"
                                                        }

                                                        "Dutch" -> {
                                                            "nl"
                                                        }

                                                        "English" -> {
                                                            "en"
                                                        }

                                                        "Finnish" -> {
                                                            "fi"
                                                        }

                                                        "French" -> {
                                                            "fr"
                                                        }

                                                        "German" -> {
                                                            "de"
                                                        }

                                                        "Greek" -> {
                                                            "el"
                                                        }

                                                        "Hebrew" -> {
                                                            "iw"
                                                        }

                                                        "Hindi" -> {
                                                            "hi"
                                                        }

                                                        "Indonesian" -> {
                                                            "in"
                                                        }

                                                        "Italian" -> {
                                                            "it"
                                                        }

                                                        "Japanese" -> {
                                                            "ja"
                                                        }

                                                        "Korean" -> {
                                                            "ko"
                                                        }

                                                        "Norwegian" -> {
                                                            "no"
                                                        }

                                                        "Polish" -> {
                                                            "pl"
                                                        }

                                                        "Portuguese" -> {
                                                            "pt"
                                                        }

                                                        "Russian" -> {
                                                            "ru"
                                                        }

                                                        "Spanish" -> {
                                                            "es"
                                                        }

                                                        "Swedish" -> {
                                                            "sv"
                                                        }

                                                        "Thai" -> {
                                                            "th"
                                                        }

                                                        "Turkish" -> {
                                                            "tr"
                                                        }

                                                        "Vietnamese" -> {
                                                            "vi"
                                                        }

                                                        else -> {
                                                            "en"
                                                        }
                                                    }
                                                    viewModel.saveLanguage(newLanguage, context)
                                                    context.setLanguage(newLanguage, true)
                                                    onDismiss()
                                                }
                                            )
                                    ) {
                                        Text(
                                            text = language,
                                            style = MaterialTheme.typography.headlineSmall,
                                            color = onDark,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 4.dp)
                                        )
                                        Divider(
                                            color = secondary,
                                            thickness = 1.dp,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            LanguageDialogHeaderImage(
                modifier = Modifier
                    .size(290.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }
}