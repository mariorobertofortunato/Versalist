package com.evenclose.versalist.app.ui.composables.forms

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.EventNote
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Diversity1
import androidx.compose.material.icons.outlined.EmojiPeople
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Spa
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.theme.errorColor
import com.evenclose.versalist.app.ui.theme.primaryBlack_Dark
import com.evenclose.versalist.app.ui.theme.primaryBlack_Light
import com.evenclose.versalist.app.ui.theme.primaryGreen_Dark
import com.evenclose.versalist.app.ui.theme.primaryGreen_Light
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.data.model.ListCategory.HEALTH
import com.evenclose.versalist.data.model.ListCategory.MISC
import com.evenclose.versalist.data.model.ListCategory.PERSONAL
import com.evenclose.versalist.data.model.ListCategory.SHOPPING
import com.evenclose.versalist.data.model.ListCategory.SOCIAL
import com.evenclose.versalist.data.model.ListCategory.WORK

@Composable
fun NewListForm(
    onConfirmClick: (
        newListValue: String,
        selectedListTypeOption: String,
        selectedListCategoryOption: String
    ) -> Unit,
    onCancelClick: () -> Unit,
    focusRequester: FocusRequester
) {

    // List name
    var newListValue by remember { mutableStateOf("") }
    var errorTextVisibility by remember { mutableStateOf(false) }

    // List type
    val listTypeOptions = listOf("Open list", "Checklist")
    val (selectedListTypeOption, onListTypeOptionSelected) = remember {
        mutableStateOf(
            listTypeOptions[0]
        )
    }

    // List category
    val listCategoryOptions = listOf("Personal", "Work", "Health", "Shopping", "Social", "Misc")
    val (selectedListCategoryOption, onListCategoryOptionSelected) = remember {
        mutableStateOf(
            listCategoryOptions[0]
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = CenterHorizontally
    ) {

        /** List name Text field */
        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.new_list_name),
                color = primaryWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .focusRequester(focusRequester)
                    .border(1.dp, primaryBlack_Light, CircleShape),
                value = newListValue,
                onValueChange = { newValue ->
                    newListValue = newValue
                    errorTextVisibility = false
                },
                singleLine = true,
                shape = CircleShape,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.new_list),
                        color = primaryBlack_Light,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                },
                colors = TextFieldDefaults.colors(
                    cursorColor = primaryBlack_Light,
                    focusedContainerColor = primaryWhite,
                    unfocusedContainerColor = primaryWhite,
                    disabledContainerColor = primaryWhite,
                    focusedTextColor = primaryBlack_Light,
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
                    text = stringResource(id = R.string.empty_new_list_name_error),
                    color = errorColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }


        /** List type "radio" group */
        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.list_type),
                color = primaryWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .background(primaryWhite, CircleShape)
                    .border(1.dp, primaryBlack_Light, CircleShape),
            ) {
                listTypeOptions.forEach { text ->
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = CenterVertically,
                        modifier = Modifier
                            .padding(4.dp)
                            .border(
                                width = 1.dp,
                                color = primaryBlack_Light,
                                shape = CircleShape
                            )
                            .weight(1f)
                            .selectable(
                                selected = (text == selectedListTypeOption),
                                onClick = { onListTypeOptionSelected(text) }
                            )
                            .clickable(
                                // Disable ripple effect because it sucks
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = { onListTypeOptionSelected(text) }
                            )
                            .background(
                                color = if (text == selectedListTypeOption) primaryBlack_Light else primaryWhite,
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = if (text == "Open list") Icons.AutoMirrored.Outlined.List else Icons.Outlined.Checklist,
                            contentDescription = "List Type Icon",
                            tint = if (text == selectedListTypeOption) primaryWhite else primaryBlack_Light,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .padding(vertical = 12.dp)
                        )
                        Text(
                            text = text,
                            color = if (text == selectedListTypeOption) primaryWhite else primaryBlack_Light,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }


        /** List Category Group */
        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.list_category),
                color = primaryWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .height(135.dp)
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .background(primaryWhite, RoundedCornerShape(32.dp))
                    .border(1.dp, primaryBlack_Light, RoundedCornerShape(32.dp)),
            ) {
                items(
                    items = listCategoryOptions,
                    key = { item -> item }) { category ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .border(
                                width = 1.dp,
                                color = primaryBlack_Light,
                                shape = CircleShape
                            )
                            .weight(1f)
                            .selectable(
                                selected = (category == selectedListCategoryOption),
                                onClick = { onListCategoryOptionSelected(category) }
                            )
                            .clickable(
                                // Disable ripple effect because it sucks
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = { onListCategoryOptionSelected(category) }
                            )
                            .background(
                                color = if (category == selectedListCategoryOption) primaryBlack_Light else primaryWhite,
                                shape = CircleShape
                            )
                    ) {
                        Column(
                            horizontalAlignment = CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            val categoryIcon: ImageVector
                            val categoryName: String
                            when (category) {
                                PERSONAL -> {
                                    categoryIcon = Icons.Outlined.EmojiPeople
                                    categoryName =
                                        stringResource(id = R.string.personal)
                                }

                                WORK -> {
                                    categoryIcon = Icons.Outlined.Badge
                                    categoryName = stringResource(id = R.string.work)
                                }

                                HEALTH -> {
                                    categoryIcon = Icons.Outlined.Spa
                                    categoryName = stringResource(id = R.string.health)
                                }

                                SHOPPING -> {
                                    categoryIcon = Icons.Outlined.ShoppingCart
                                    categoryName =
                                        stringResource(id = R.string.shopping)
                                }

                                SOCIAL -> {
                                    categoryIcon = Icons.Outlined.Diversity1
                                    categoryName = stringResource(id = R.string.social)
                                }

                                MISC -> {
                                    categoryIcon = Icons.AutoMirrored.Outlined.EventNote
                                    categoryName = stringResource(id = R.string.misc)
                                }

                                else -> {
                                    categoryIcon = Icons.AutoMirrored.Outlined.List
                                    categoryName = stringResource(id = R.string.misc)
                                }
                            }
                            Icon(
                                imageVector = categoryIcon,
                                contentDescription = "category icon",
                                tint = if (category == selectedListCategoryOption) primaryWhite else primaryBlack_Light,
                            )
                            Text(
                                text = categoryName,
                                color = if (category == selectedListCategoryOption) primaryWhite else primaryBlack_Light,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }

        }


        /** Confirm/Cancel CTA Group */
        Row(
            verticalAlignment = CenterVertically,
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
                    newListValue = ""
                }
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically,
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
                    if (newListValue != "") {
                        onConfirmClick(
                            newListValue,
                            selectedListTypeOption,
                            selectedListCategoryOption
                        )
                        newListValue = ""
                    } else {
                        errorTextVisibility = true
                    }
                }
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically,
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