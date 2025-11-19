package com.evenclose.versalist.data.repository

import android.util.Log
import com.evenclose.versalist.data.database.ListDao
import com.evenclose.versalist.data.model.InnerListItem
import com.evenclose.versalist.data.model.MainListItem
import com.evenclose.versalist.domain.model.ListsModel
import kotlinx.coroutines.flow.flow
import java.time.Instant
import javax.inject.Inject

class ListRepository @Inject constructor(
    private val dao: ListDao,
) {

    /** MAIN LIST */
    suspend fun fetchAllLists() = flow {
        try {
            emit(ListsModel(lists = dao.fetchAllLists()))
        } catch (e: Exception) {
            Log.e("TAG Error Fetch All Lists", "$e")
        }
    }

    suspend fun getListData(id: Int) = flow {
        try {
            emit(dao.fetchCurrentListData(id))
        } catch (e: Exception) {
            Log.e("TAG Error Fetch List data", "$e")
        }
    }

    suspend fun addNewList(item: MainListItem) {
        try {
            dao.addNewList(item)
        } catch (e: Exception) {
            Log.e("TAG Error Add New List", "$e")
        }
    }

    suspend fun deleteMainListItem(id: Int) {
        try {
            dao.deleteMainListItem(id)
        } catch (e: Exception) {
            Log.e("TAG Error Delete main List item", "$e")
        }
    }

    suspend fun updateMainListFavouriteStatus(id: Int, newFavouriteStatus: Boolean) {
        try {
            dao.updateMainListFavouriteStatus(id, newFavouriteStatus)
        } catch (e: Exception) {
            Log.e("TAG Error Update Main List Favourite Status", "$e")
        }
    }


    /** INNER LIST */
    suspend fun addNewInnerListItem(value: String, mainListId: Int) {
        try {
            dao.addNewInnerListItem(
                InnerListItem(
                    name = value,
                    isChecked = false,
                    mainListId = mainListId
                )
            )
        } catch (e: Exception) {
            Log.e("TAG Error Add New Inner List Item", "$e")
        }
    }

    suspend fun updateItemCheckStatus(id: Int, newCheckStatus: Boolean) {
        try {
            dao.updateItemCheckStatus(id, newCheckStatus)
        } catch (e: Exception) {
            Log.e("TAG Error Update Item Check Status", "$e")
        }
    }

    suspend fun deleteInnerListItem(id: Int) {
        try {
            dao.deleteInnerListItem(id)
        } catch (e: Exception) {
            Log.e("TAG Error Delete Inner List item", "$e")
        }
    }

    suspend fun deleteInnerListItemFromMainList(id: Int) {
        try {
            dao.deleteInnerListItemFromMainList(id)
        } catch (e: Exception) {
            Log.e("TAG Error Delete Inner List item from Main List", "$e")
        }
    }

    suspend fun getCurrentInnerList(id: Int) = flow {
        try {
            emit(dao.fetchCurrentInnerList(id))
        } catch (e: Exception) {
            Log.e("TAG Error Fetch Inner List", "$e")
        }
    }


}