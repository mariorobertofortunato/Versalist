package com.evenclose.versalist.app.ui.composables.dialog.deletereminderdialog

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalist.AlarmReceiver
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.composables.dialog.deleteitemdialog.DeleteItemDialogHeaderImage
import com.evenclose.versalist.app.ui.theme.primaryBlack_Light
import com.evenclose.versalist.app.ui.theme.primaryGreen_Dark
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.app.viewmodel.ListViewModel

@Composable
fun DeleteReminderDialog(
    mainListId : Int,
    navController: NavController,
    listViewModel: ListViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {

    val context = LocalContext.current

    Dialog(
        onDismissRequest = onDismiss
    ) {

        Box(
            modifier = Modifier.offset(y= (-100).dp)
        ){
            Column(
                modifier = Modifier
            ){
                Spacer(
                    modifier = Modifier
                        .height(130.dp)
                )
                Box(
                    modifier = Modifier
                        .background(
                            color = primaryBlack_Light,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(1.dp, primaryWhite, RoundedCornerShape(12.dp))
                ){
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){

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
                            text = "This reminder " + stringResource(id = R.string.will_be_permanently_deleted),
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
                                    // Cancel alarm
                                    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                                    val intent = Intent(context, AlarmReceiver::class.java)
                                    val pendingIntent = PendingIntent.getBroadcast(
                                        context,
                                        mainListId,
                                        intent,
                                        PendingIntent.FLAG_IMMUTABLE
                                    )
                                    alarmManager.cancel(pendingIntent)
                                    // Delete from DB
                                    listViewModel.updateMainListReminder(mainListItemId = mainListId, reminderDate = null)
                                    onDismiss()
                                    navController.navigateUp()
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