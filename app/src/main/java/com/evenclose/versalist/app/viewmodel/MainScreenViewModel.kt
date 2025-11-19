package com.evenclose.versalist.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evenclose.versalist.app.common.ViewState
import com.evenclose.versalist.app.contracts.MainScreenEvent
import com.evenclose.versalist.app.contracts.MainScreenState
import com.evenclose.versalist.data.model.MainListItem
import com.evenclose.versalist.domain.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState(
        eventSink = { event -> processEvent(event) }
    ))
    val state: StateFlow<MainScreenState> = _state.asStateFlow()

    init {
        processEvent(MainScreenEvent.FetchMainList)
    }

    private fun processEvent(event: MainScreenEvent) {
        when (event) {
            MainScreenEvent.FetchMainList -> fetchMainList()
            is MainScreenEvent.ShowPopup -> _state.update { it.copy(popupType = event.popupType, selectedItem = event.data as MainListItem) }
            MainScreenEvent.HidePopup -> _state.update { it.copy(popupType = null, selectedItem = null)  }
            MainScreenEvent.HideToast -> _state.update { it.copy(toastMessage = null) }
            is MainScreenEvent.AddNewMainListItem -> addNewMainListItem(event.listItem)
            is MainScreenEvent.DeleteMainListItem -> deleteMainListItem(event.mainListItemId)
            //is MainScreenIntent.UpdateMainListItem -> updateMainListItem(intent)
            else -> {}
        }
    }

    private fun fetchMainList() {

        _state.update {
            it.copy(
                isLoading = true,
                toastMessage = null,
                popupType = null
            )
        }

        viewModelScope.launch {
            delay(500)
            useCase.FetchAllListsUseCase().collect { mainListItems ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        mainListItems = mainListItems.lists,
                        toastMessage = "Main List Fetched Successfully"
                    )
                }
            }
        }
    }

    private fun addNewMainListItem(listItem: MainListItem) {
        _state.update {
            it.copy(
                isLoading = true,
                toastMessage = null,
                popupType = null
            )
        }
        viewModelScope.launch {
            useCase.AddNewListUseCase(listItem)
            fetchMainList()
        }
    }

    private fun deleteMainListItem(id: Int) {
        _state.update {
            it.copy(
                isLoading = true,
                toastMessage = null,
                popupType = null
            )
        }
        viewModelScope.launch {
            useCase.DeleteMainListItemUseCase(id)
            useCase.DeleteInnerListItemFromMainListUseCase(id)
            fetchMainList()
        }
    }


}