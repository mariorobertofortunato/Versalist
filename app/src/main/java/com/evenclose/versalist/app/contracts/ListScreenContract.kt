package com.evenclose.versalist.app.contracts

import com.evenclose.versalist.data.model.InnerListItem
import com.evenclose.versalist.domain.model.Popup


sealed class ListScreenEvent {
    data class FetchInnerList(val mainListItemId: Int) : ListScreenEvent()
    data class AddNewInnerListItem(val listItem: InnerListItem) : ListScreenEvent()
    data class DeleteInnerListItem(val innerListItem: InnerListItem) : ListScreenEvent()
    data class ToggleInnerListCheckStatus(val innerListItem: InnerListItem) : ListScreenEvent()
    data class ShowPopup(val popupType: Popup, val data: InnerListItem? = null) : ListScreenEvent()

    object HidePopup : ListScreenEvent()
    object HideToast : ListScreenEvent()

}

data class ListScreenState(
    val isLoading: Boolean = false,
    val toastMessage: String? = null,
    val popupType: Popup? = null,
    val selectedItem: InnerListItem? = null,
    val innerListItems: List<InnerListItem> = emptyList(),
    val eventTunnel: (ListScreenEvent) -> Unit = {}
)
