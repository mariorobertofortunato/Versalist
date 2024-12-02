package com.evenclose.versalist.app.ui.composables.dialog.deleteitemdialog

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.evenclose.versalist.R
import com.evenclose.versalist.data.model.InnerListItem
import com.evenclose.versalist.data.model.MainListItem
import com.evenclose.versalist.app.ui.theme.light
import com.evenclose.versalist.app.ui.theme.primaryGreen_Light
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.app.ui.theme.secondaryContainer
import com.evenclose.versalist.app.viewmodel.ListViewModel

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
                        .background(primaryGreen_Light, RoundedCornerShape(16.dp))
                        .border(2.dp, primaryWhite, RoundedCornerShape(16.dp))
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
                                .padding(top = 8.dp,)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = primaryWhite,
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
                                    text = stringResource(id = R.string.cancel),
                                    fontSize = 16.sp,
                                    color = primaryWhite,
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
                                    text = stringResource(id = R.string.confirm),
                                    fontSize = 16.sp,
                                    color = primaryWhite,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                }
            }
            DeleteItemDialogHeaderImage(
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }
}