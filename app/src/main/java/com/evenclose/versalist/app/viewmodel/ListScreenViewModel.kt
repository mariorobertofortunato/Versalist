package com.evenclose.versalist.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evenclose.versalist.app.contracts.ListScreenEvent
import com.evenclose.versalist.app.contracts.ListScreenState
import com.evenclose.versalist.app.contracts.MainScreenEvent
import com.evenclose.versalist.data.model.InnerListItem
import com.evenclose.versalist.domain.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _state = MutableStateFlow(
        ListScreenState(
        eventTunnel = { event -> processEvent(event) }
    ))
    val state: StateFlow<ListScreenState> = _state.asStateFlow()

    private fun processEvent(event: ListScreenEvent) {
        when (event) {
            is ListScreenEvent.FetchInnerList -> fetchInnerList(event.mainListItemId)
            is ListScreenEvent.HidePopup -> _state.update { it.copy(popupType = null, selectedItem = null)  }
            is ListScreenEvent.HideToast -> _state.update { it.copy(toastMessage = null) }
            is ListScreenEvent.ShowPopup -> _state.update { it.copy(popupType = event.popupType, selectedItem = event.data) }
            is ListScreenEvent.AddNewInnerListItem -> addNewInnerListItem(event.listItem)
            is ListScreenEvent.DeleteInnerListItem -> deleteInnerListItem(event.innerListItem)
            is ListScreenEvent.ToggleInnerListCheckStatus -> toggleInnerListCheckStatus(event.innerListItem)
           else -> {}
        }
    }

    private fun fetchInnerList(mainListId: Int) {
        setLoadingState()
        viewModelScope.launch {
            useCase.fetchCurrentInnerListUseCase(mainListId).collect { innerListItems ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        innerListItems = innerListItems,
                        toastMessage = "Main List Fetched Successfully"
                    )
                }
            }
        }
    }

    private fun addNewInnerListItem(innerListItem: InnerListItem) {
        setLoadingState()
        viewModelScope.launch {
            useCase.addNewInnerListItemUseCase(innerListItem)
            fetchInnerList(innerListItem.mainListId)
        }
    }

    fun toggleInnerListCheckStatus(innerListItem: InnerListItem) {
        setLoadingState()
        viewModelScope.launch {
            useCase.toggleInnerListItemCheckStatusUseCase(innerListItem)
            fetchInnerList(innerListItem.mainListId)
        }
    }

    private fun setLoadingState() {
        _state.update {
            it.copy(
                isLoading = true,
                toastMessage = null,
                popupType = null
            )
        }
    }


    fun deleteInnerListItem(innerListItem: InnerListItem) {
        setLoadingState()
        viewModelScope.launch {
            useCase.deleteInnerListItemUseCase(innerListItem.id ?: 0)
            fetchInnerList(innerListItem.mainListId)
        }
    }




}