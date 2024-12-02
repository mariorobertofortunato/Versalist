package com.evenclose.versalist.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evenclose.versalist.data.DataStore
import com.evenclose.versalist.data.model.InnerListItem
import com.evenclose.versalist.data.model.MainListItem
import com.evenclose.versalist.domain.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val useCase: UseCase): ViewModel(){

    val mainList = MutableStateFlow<List<MainListItem>>(emptyList())
    val currentListData = MutableStateFlow<MainListItem>(MainListItem(
        id = -1,
        name = "",
        type = "",
        category = "",
        isFav = false,
        reminderDate = null
    ))
    val currentInnerList = MutableStateFlow<List<InnerListItem>>(emptyList())

    fun fetchAllLists() {
        viewModelScope.launch {
            useCase.FetchAllListsUseCase().collect {
                mainList.value = it
            }
        }
    }

    fun addNewList(name: String, type: String, category: String) {
        viewModelScope.launch {
            useCase.AddNewListUseCase(name, type, category)
            fetchAllLists()
        }
    }

    fun deleteMainListItem(id: Int) {
        viewModelScope.launch {
            useCase.DeleteMainListItemUseCase(id)
            useCase.DeleteInnerListItemFromMainListUseCase(id)
            fetchAllLists()
        }
    }

    fun getListData(id: Int) {
        viewModelScope.launch {
            useCase.GetListDataUseCase(id).collect {
                currentListData.value = it
            }
        }
    }

    fun updateMainListFavouriteStatus(mainListItemId: Int, newFavouriteStatus: Boolean) {
        viewModelScope.launch {
            useCase.UpdateMainListFavouriteStatusUseCase(mainListItemId, newFavouriteStatus)
            fetchAllLists()
        }
    }

    fun updateMainListReminder(mainListItemId: Int, reminderDate: Instant?) {
        viewModelScope.launch {
            useCase.UpdateMainListReminderUseCase(mainListItemId, reminderDate)
            fetchAllLists()
        }
    }

    fun getCurrentInnerList(id: Int) {
        viewModelScope.launch {
            useCase.GetCurrentInnerListUseCase(id).collect {
                currentInnerList.value = it
            }
        }
    }

    fun addNewInnerListItem(value: String, mainListId: Int) {
        viewModelScope.launch {
            useCase.AddNewInnerListItemUseCase(value, mainListId)
        }
    }

    fun deleteInnerListItem(id: Int, mainListId: Int) {
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