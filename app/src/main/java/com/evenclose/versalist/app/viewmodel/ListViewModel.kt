package com.evenclose.versalist.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evenclose.versalist.app.common.ViewState
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




    fun fetchAllLists() {
        viewState.value = ViewState.Loading
        viewModelScope.launch {
            delay(500)
            useCase.fetchAllListsUseCase().collect {
                mainList.value = it.lists
                viewState.value = ViewState.Done(it)
            }
        }
    }

    fun getListData(id: Int) {
        viewState.value = ViewState.Loading
        viewModelScope.launch {
            useCase.getListDataUseCase(id).collect {
                currentListData.value = it
                viewState.value = ViewState.Done()
            }
        }
    }



    fun getCurrentInnerList(id: Int) {
        viewState.value = ViewState.Loading
        viewModelScope.launch {
            useCase.getCurrentInnerListUseCase(id).collect {
                currentInnerList.value = it
                viewState.value = ViewState.Done()
            }
        }
    }

    fun addNewInnerListItem(value: String, mainListId: Int) {
        viewState.value = ViewState.Loading
        viewModelScope.launch {
            useCase.addNewInnerListItemUseCase(value, mainListId)
        }
    }

    fun deleteInnerListItem(id: Int, mainListId: Int) {
        viewState.value = ViewState.Loading
        viewModelScope.launch {
            useCase.deleteInnerListItemUseCase(id)
            getCurrentInnerList(mainListId)
        }
    }

    fun updateItemCheckStatus(innerListItemId: Int, newCheckStatus: Boolean, mainListId: Int) {
        viewModelScope.launch {
            useCase.updateItemCheckStatusUseCase(innerListItemId, newCheckStatus)
            getCurrentInnerList(mainListId)
        }
    }



}