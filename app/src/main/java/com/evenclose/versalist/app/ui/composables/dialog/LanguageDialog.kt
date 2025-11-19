package com.evenclose.versalist.app.ui.composables.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.evenclose.versalist.R
import com.evenclose.versalist.app.compositions.LocalCompositionMainScreen
import com.evenclose.versalist.app.contracts.MainScreenEvent
import com.evenclose.versalist.app.ui.theme.primaryWhite

@Composable
fun LanguageDialog() {

    val eventTunnel = LocalCompositionMainScreen.current
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
    )

    Column(
        modifier = Modifier
    ) {
        languageList.forEach { language ->
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
                            eventTunnel(MainScreenEvent.SaveLanguage(newLanguage, context))
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