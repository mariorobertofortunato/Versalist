package com.evenclose.versalist.app.ui.composables.forms

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.theme.errorColor
import com.evenclose.versalist.app.ui.theme.primaryBlack_Dark
import com.evenclose.versalist.app.ui.theme.primaryGreen_Dark
import com.evenclose.versalist.app.ui.theme.primaryGreen_Light
import com.evenclose.versalist.app.ui.theme.primaryWhite

@Composable
fun NewItemForm (
    onConfirmClick: (newItemValue: String) -> Unit,
    onCancelClick: () -> Unit,
    focusRequester: FocusRequester
){
    var newItemValue by remember { mutableStateOf("") }
    var errorTextVisibility by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /** Text field */
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .border(1.dp, primaryGreen_Dark, CircleShape),
            value = newItemValue,
            onValueChange = { newValue ->
                newItemValue = newValue
                errorTextVisibility = false
            },
            singleLine = true,
            shape = CircleShape,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.new_item),
                    color = primaryGreen_Light,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            colors = TextFieldDefaults.colors(
                cursorColor = primaryGreen_Dark,
                focusedContainerColor = primaryWhite,
                unfocusedContainerColor = primaryWhite,
                disabledContainerColor = primaryWhite,
                focusedTextColor = primaryGreen_Dark,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),

            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
        )

        /** Error Text */
        AnimatedVisibility(
            visible = errorTextVisibility,
            enter = slideInVertically(animationSpec = tween(100)) + fadeIn(),
            exit = slideOutVertically(animationSpec = tween(100)) + fadeOut()
        ) {
            Text(
                text = stringResource(id = R.string.empty_new_item_error),
                color = errorColor,
                fontWeight = FontWeight.Bold
            )
        }

        /** Confirm/Cancel CTA Group */
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            FloatingActionButton(
                containerColor = primaryBlack_Dark,
                contentColor = primaryWhite,
                shape = CircleShape,
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, primaryWhite, CircleShape),
                onClick = {
                    onCancelClick()
                    newItemValue = ""
                }
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "Cancel Icon",
                        tint = primaryWhite,
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.cancel),
                        color = primaryWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
            FloatingActionButton(
                containerColor = primaryBlack_Dark,
                contentColor = primaryWhite,
                shape = CircleShape,
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, primaryWhite, CircleShape),
                onClick = {
                    if (newItemValue != "") {
                        onConfirmClick(newItemValue)
                        newItemValue = ""
                    } else {
                        errorTextVisibility = true
                    }
                }
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = "Confirm Icon",
                        tint = primaryWhite,
                        modifier = Modifier
                            .padding(end = 8.dp)
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
}