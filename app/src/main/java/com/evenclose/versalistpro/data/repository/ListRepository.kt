package com.evenclose.versalistpro.data.repository

import android.util.Log
import com.evenclose.versalistpro.data.database.ListDao
import com.evenclose.versalistpro.data.model.MainListItem
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListRepository @Inject constructor(
    private val dao: ListDao,
){

    private lateinit var allLists: List<MainListItem>

    suspend fun fetchAllLists() = flow {
        try {
            allLists = dao.fetchAllLists()
            emit(allLists)
        } catch (e: Exception) {
            Log.e("TAG Error Fetch All Lists", "$e")
        }
    }

    suspend fun getListData(name: String) = flow {
        try {
            //allLists = dao.fetchAllLists()
            emit(dao.getListData(name))
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



}