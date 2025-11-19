package com.evenclose.versalist.app.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evenclose.versalist.app.common.ViewState
import com.evenclose.versalist.data.DataStore
import com.evenclose.versalist.data.model.InnerListItem
import com.evenclose.versalist.data.model.MainListItem
import com.evenclose.versalist.domain.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    val viewState = MutableStateFlow<ViewState>(ViewState.None)

    val mainList = MutableStateFlow<List<MainListItem>>(emptyList())
    val currentInnerList = MutableStateFlow<List<InnerListItem>>(emptyList())
    val currentListData = MutableStateFlow(
        MainListItem(
            id = -1,
            name = "",
            type = "",
            category = "",
            isFav = false
        )
    )

    fun resetViewState() {
        viewState.value = ViewState.None
    }


    fun fetchAllLists() {
        viewState.value = ViewState.Loading
        viewModelScope.launch {
            delay(500)
            useCase.FetchAllListsUseCase().collect {
                mainList.value = it.lists
                viewState.value = ViewState.Done(it)
            }
        }
    }

/*    fun addNewList(name: String, type: String, category: String) {
        viewState.value = ViewState.Loading
        viewModelScope.launch {
            useCase.AddNewListUseCase(name, type, category)
            fetchAllLists()
        }
    }*/

    fun deleteMainListItem(id: Int) {
        viewState.value = ViewState.Loading
        viewModelScope.launch {
            useCase.DeleteMainListItemUseCase(id)
            useCase.DeleteInnerListItemFromMainListUseCase(id)
            fetchAllLists()
        }
    }

    fun getListData(id: Int) {
        viewState.value = ViewState.Loading
        viewModelScope.launch {
            useCase.GetListDataUseCase(id).collect {
                currentListData.value = it
                viewState.value = ViewState.Done()
            }
        }
    }

    fun updateMainListFavouriteStatus(mainListItemId: Int, newFavouriteStatus: Boolean) {
        viewState.value = ViewState.Loading
        viewModelScope.launch {
            useCase.UpdateMainListFavouriteStatusUseCase(mainListItemId, newFavouriteStatus)
            fetchAllLists()
        }
    }

    fun getCurrentInnerList(id: Int) {
        viewState.value = ViewState.Loading
        viewModelScope.launch {
            useCase.GetCurrentInnerListUseCase(id).collect {
                currentInnerList.value = it
                viewState.value = ViewState.Done()
            }
        }
    }

    fun addNewInnerListItem(value: String, mainListId: Int) {
        viewState.value = ViewState.Loading
        viewModelScope.launch {
            useCase.AddNewInnerListItemUseCase(value, mainListId)
        }
    }

    fun deleteInnerListItem(id: Int, mainListId: Int) {
        viewState.value = ViewState.Loading
        viewModelScope.launch {
            useCase.DeleteInnerListItemUseCase(id)
            getCurrentInnerList(mainListId)
        }
    }

    fun updateItemCheckStatus(innerListItemId: Int, newCheckStatus: Boolean, mainListId: Int) {
        viewModelScope.launch {
            useCase.UpdateItemCheckStatusUseCase(innerListItemId, newCheckStatus)
            getCurrentInnerList(mainListId)
        }
    }


    fun saveLanguage(newLanguage: String, context: Context) {
        viewModelScope.launch {
            val dataStore = DataStore(context)
            dataStore.saveLanguage(newLanguage)
        }
    }
}