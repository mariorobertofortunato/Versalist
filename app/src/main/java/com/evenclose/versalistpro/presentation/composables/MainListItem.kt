package com.evenclose.versalistpro.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.evenclose.versalistpro.data.model.MainListItem
import com.evenclose.versalistpro.presentation.ui.theme.white
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel

@Composable
fun MainListItem(
    list: MainListItem,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    val listData = listViewModel.currentList.observeAsState(null)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
    ) {


/*        Image(
            painter = rememberImagePainter(data = user.photoUrl ?: R.drawable.message_circle),
            contentDescription = "Avatar Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )*/
        Column() {
            // LIST NAME
            Text(
                text = list.name ?: "ERRORE lista",
                //style = Typography.titleMedium,
                color = white,
            )
        }
    }
}