package com.evenclose.versalist.app.contracts

import com.evenclose.versalist.data.model.MainListItem

/** EVENTS */
sealed class MainScreenEvent {
    object FetchMainList : MainScreenEvent()
    data class AddNewMainListItem(val listItem: MainListItem) : MainScreenEvent()
    data class DeleteMainListItem(val mainListItemId: Int): MainScreenEvent()
    data class ToggleMainListItemFav(val mainListItemId: Int) : MainScreenEvent()
    data class ShowPopup(val popupType: String, val data: Any? = null): MainScreenEvent()
    object HidePopup : MainScreenEvent()
    object HideToast : MainScreenEvent()
    data class NavigateToInnerList(val mainListItemId: Int) : MainScreenEvent()
}

/** STATE */
data class MainScreenState(
    val isLoading: Boolean = false,
    val toastMessage: String? = null,
    val popupType: String? = null,
    val selectedItem: MainListItem? = null,
    val mainListItems: List<MainListItem> = emptyList(),
    val eventSink: (MainScreenEvent) -> Unit = {}
)

