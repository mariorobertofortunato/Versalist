package com.evenclose.versalistpro.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evenclose.versalistpro.data.DataStore
import com.evenclose.versalistpro.data.model.InnerListItem
import com.evenclose.versalistpro.data.model.MainListItem
import com.evenclose.versalistpro.domain.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val useCase: UseCase): ViewModel(){

    val mainList = MutableLiveData<List<MainListItem>>(emptyList())
    val currentListData = MutableLiveData<MainListItem>(null)
    val currentInnerList = MutableLiveData<List<InnerListItem>>(emptyList())

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