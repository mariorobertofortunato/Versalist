package com.evenclose.versalistpro.presentation.composables.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.evenclose.versalistpro.data.model.InnerListItem
import com.evenclose.versalistpro.data.model.MainListItem
import com.evenclose.versalistpro.presentation.ui.theme.onDark
import com.evenclose.versalistpro.presentation.ui.theme.secondaryContainer
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel

@Composable
fun DeleteItemDialog (
    mainListItem: MainListItem?,
    innerListItem: InnerListItem?,
    listViewModel: ListViewModel = hiltViewModel(),
    onDismiss: () -> Unit
){

    val itemName = mainListItem?.name ?: innerListItem?.name

    Dialog(
        onDismissRequest = onDismiss
    ) {

        Box(
            modifier = Modifier.offset(y= (-100).dp)
        ) {
            Column(
                modifier = Modifier
            ) {
                Spacer(
                    modifier = Modifier
                        .height(130.dp)
                )
                Box(
                    modifier = Modifier
                        .background(
                            color = secondaryContainer,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(1.dp, onDark,RoundedCornerShape(12.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(
                            modifier = Modifier.height(24.dp)
                        )

                        Text(
                            text = "Are you sure?",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.headlineSmall,
                            color = onDark,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            text = "$itemName will be permanently deleted.",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 8.dp,)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = onDark,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            TextButton(
                                onClick = {
                                    onDismiss()
                                }
                            ) {
                                Text(
                                    text = "Cancel",
                                    fontSize = 16.sp,
                                    color = onDark,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            TextButton(
                                onClick = {
                                    if (mainListItem != null ) {
                                        listViewModel.deleteMainListItem(mainListItem.id!!)
                                    } else if (innerListItem != null ) {
                                        listViewModel.deleteInnerListItem(innerListItem.id!!, innerListItem.mainListId)
                                    }
                                    onDismiss()
                                }
                            ) {
                                Text(
                                    text = "Ok",
                                    fontSize = 16.sp,
                                    color = onDark,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                }
            }
            DialogHeaderImage(
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }
}