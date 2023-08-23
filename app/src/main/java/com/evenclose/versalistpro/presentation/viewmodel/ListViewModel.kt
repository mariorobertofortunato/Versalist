package com.evenclose.versalistpro.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evenclose.versalistpro.data.model.MainListItem
import com.evenclose.versalistpro.domain.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val useCase: UseCase): ViewModel(){

    val allLists = MutableLiveData<List<MainListItem>>(emptyList())
    val currentList = MutableLiveData<MainListItem>(null)

    fun fetchAllLists() {
        viewModelScope.launch {
            useCase.FetchAllListsUseCase().collect {
                //allLists.postValue(it)
            }
        }
    }

    fun getListData(name: String) {
        viewModelScope.launch {
            useCase.GetListDataUseCase(name).collect {
                //currentList.postValue(it)
            }
        }
    }

}