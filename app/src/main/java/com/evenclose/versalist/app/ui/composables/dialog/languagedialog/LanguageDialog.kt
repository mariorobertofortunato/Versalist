package com.evenclose.versalist.app.ui.composables.dialog.languagedialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.composables.CustomCTA
import com.evenclose.versalist.app.ui.theme.primaryBlack_Light
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.app.viewmodel.ListViewModel
import com.evenclose.versalist.utils.setLanguage

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
                        .background(primaryBlack_Light, RoundedCornerShape(16.dp))
                        .border(2.dp, primaryWhite, RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                    ) {
                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.choose_language),
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
                                .padding(top = 16.dp, bottom = 4.dp)
                        )
                        Scaffold(
                            containerColor = primaryBlack_Light,
                            bottomBar = {
                                Column {
                                    HorizontalDivider(
                                        color = primaryWhite,
                                        thickness = 1.dp,
                                        modifier = Modifier
                                            .fillMaxWidth(0.95f)
                                            .align(CenterHorizontally)
                                            .padding(vertical = 16.dp)
                                    )
                                    CustomCTA(
                                        text = stringResource(id = R.string.dismiss),
                                        onClick = {
                                            onDismiss()
                                        }
                                    )
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
                                            color = primaryWhite,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 4.dp)
                                        )
                                        HorizontalDivider(
                                            color = primaryWhite,
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