package com.evenclose.versalistpro.data.repository

import android.util.Log
import com.evenclose.versalistpro.data.database.ListDao
import com.evenclose.versalistpro.data.model.InnerListItem
import com.evenclose.versalistpro.data.model.MainListItem
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListRepository @Inject constructor(
    private val dao: ListDao,
){

    private lateinit var allLists: List<MainListItem>
    private lateinit var currentInnerList: List<InnerListItem>
    private lateinit var currentListData: MainListItem

    suspend fun fetchAllLists() = flow {
        try {
            allLists = dao.fetchAllLists()
            emit(allLists)
        } catch (e: Exception) {
            Log.e("TAG Error Fetch All Lists", "$e")
        }
    }

    suspend fun getListData(id: Int) = flow {
        try {
            currentListData = dao.fetchCurrentListData(id)
            emit(currentListData)
        } catch (e: Exception) {
            Log.e("TAG Error Fetch List data", "$e")
        }
    }

    suspend fun addNewList(name: String) {
        try {
            dao.addNewList(MainListItem(name = name))
        } catch (e: Exception) {
            Log.e("TAG Error Add New List", "$e")
        }
    }

    suspend fun addNewInnerListItem(value: String, mainListId: Int) {
        try {
            dao.addNewInnerListItem(InnerListItem(name = value, isChecked = false, mainListId = mainListId))
        } catch (e: Exception) {
            Log.e("TAG Error Add New Inner List Item", "$e")
        }
    }

    suspend fun getCurrentInnerList(id: Int) = flow {
        try {
            currentInnerList = dao.fetchCurrentInnerList(id)
            emit(currentInnerList)
        } catch (e: Exception) {
            Log.e("TAG Error Fetch Inner List", "$e")
        }
    }



}