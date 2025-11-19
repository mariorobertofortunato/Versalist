package com.evenclose.versalist.app.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evenclose.versalist.app.common.ViewState
import com.evenclose.versalist.app.contracts.MainScreenEvent
import com.evenclose.versalist.app.contracts.MainScreenState
import com.evenclose.versalist.data.DataStore
import com.evenclose.versalist.data.model.MainListItem
import com.evenclose.versalist.domain.use_case.UseCase
import com.evenclose.versalist.utils.setLanguage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenSingularity @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState(
        eventTunnel = { event -> processEvent(event) }
    ))
    val state: StateFlow<MainScreenState> = _state.asStateFlow()

    init {
        processEvent(MainScreenEvent.FetchMainList)
    }

    private fun processEvent(event: MainScreenEvent) {
        when (event) {
            MainScreenEvent.FetchMainList -> fetchMainList()
            is MainScreenEvent.ShowPopup -> _state.update { it.copy(popupType = event.popupType, selectedItem = event.data) }
            MainScreenEvent.HidePopup -> _state.update { it.copy(popupType = null, selectedItem = null)  }
            MainScreenEvent.HideToast -> _state.update { it.copy(toastMessage = null) }
            is MainScreenEvent.AddNewMainListItem -> addNewMainListItem(event.listItem)
            is MainScreenEvent.DeleteMainListItem -> deleteMainListItem(event.mainListItemId)
            is MainScreenEvent.SaveLanguage -> saveLanguage(newLanguage = event.language, context = event.context)
            is MainScreenEvent.ToggleMainListItemFav -> toggleMainListItemFav(mainListItemId = event.mainListItemId, newFavouriteStatus = event.newFavStatus)
            //is MainScreenIntent.UpdateMainListItem -> updateMainListItem(intent)
            else -> {}
        }
    }

    private fun fetchMainList() {
        setLoadingState()
        viewModelScope.launch {
            delay(500)
            useCase.fetchAllListsUseCase().collect { mainListItems ->
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
        setLoadingState()
        viewModelScope.launch {
            useCase.addNewListUseCase(listItem)
            fetchMainList()
        }
    }

    private fun deleteMainListItem(id: Int) {
        setLoadingState()
        viewModelScope.launch {
            useCase.deleteMainListItemUseCase(id)
            useCase.deleteInnerListItemFromMainListUseCase(id)
            fetchMainList()
        }
    }

    private fun toggleMainListItemFav(mainListItemId: Int, newFavouriteStatus: Boolean) {
        setLoadingState()
        viewModelScope.launch {
            useCase.updateMainListFavouriteStatusUseCase(mainListItemId, newFavouriteStatus)
            fetchMainList()
        }
    }

    private fun saveLanguage(newLanguage: String, context: Context) {
        viewModelScope.launch {
            val dataStore = DataStore(context)
            dataStore.saveLanguage(newLanguage)
            context.setLanguage(newLanguage, true)
            processEvent(MainScreenEvent.HidePopup)
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


}