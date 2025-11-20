package com.evenclose.versalist.app.contracts

import android.content.Context
import com.evenclose.versalist.data.model.MainListItem
import com.evenclose.versalist.domain.model.Popup

sealed class MainScreenEvent {
    object FetchMainList : MainScreenEvent()
    data class AddNewMainListItem(val listItem: MainListItem) : MainScreenEvent()
    data class DeleteMainListItem(val mainListItemId: Int): MainScreenEvent()
    data class ToggleMainListItemFav(val mainListItemId: Int, val newFavStatus: Boolean) : MainScreenEvent()
    data class SaveLanguage(val language: String, val context: Context): MainScreenEvent()

    data class ShowPopup(val popupType: Popup, val data: MainListItem? = null): MainScreenEvent()
    object HidePopup : MainScreenEvent()
    object HideToast : MainScreenEvent()
}

data class MainScreenState(
    val isLoading: Boolean = false,
    val toastMessage: String? = null,
    val popupType: Popup? = null,
    val selectedItem: MainListItem? = null,
    val mainListItems: List<MainListItem> = emptyList(),
    val eventTunnel: (MainScreenEvent) -> Unit = {}
)