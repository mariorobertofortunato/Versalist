package com.evenclose.versalist.app.contracts

import com.evenclose.versalist.data.model.InnerListItem

/** INTENTS */
sealed class ListScreenIntent {
    data class FetchInnerList(val mainListItemId: Int) : ListScreenIntent()
    data class AddNewInnerListItem(val listItem: InnerListItem) : ListScreenIntent()
    data class DeleteInnerListItem(val innerListItemId: Int) : ListScreenIntent()
    data class UpdateInnerListItem(val innerListItemId: Int) : ListScreenIntent()
}

/** EVENTS */
sealed class ListScreenEvent {
    data class ShowPopup(val popupType: String) : ListScreenEvent()
    data class ShowSuccessToast(val successMessage: String) : ListScreenEvent()
    data class ShowErrorToast(val errorMessage: String) : ListScreenEvent()
    object NavigateUp : ListScreenEvent()
}

/** STATE */
data class ListScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val innerListItems: List<InnerListItem> = emptyList(),
    val eventSink: (ListScreenEvent) -> Unit = {}
)
