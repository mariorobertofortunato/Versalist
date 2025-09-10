package com.evenclose.versalist.data.repository

import android.util.Log
import com.evenclose.versalist.data.database.ListDao
import com.evenclose.versalist.data.model.InnerListItem
import com.evenclose.versalist.data.model.MainListItem
import kotlinx.coroutines.flow.flow
import java.time.Instant
import javax.inject.Inject

class ListRepository @Inject constructor(
    private val dao: ListDao,
) {

    private lateinit var allLists: List<MainListItem>
    private lateinit var currentInnerList: List<InnerListItem>
    private lateinit var currentListData: MainListItem


    /** MAIN LIST */
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

    suspend fun addNewList(name: String, type: String, category: String) {
        try {
            dao.addNewList(
                MainListItem(
                    name = name,
                    type = type,
                    category = category,
                    isFav = false,
                    reminderDate = null
                )
            )
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

    suspend fun updateMainListReminder(id: Int, reminderDate: Instant?) {
        try {
            dao.updateMainListReminder(id, reminderDate)
        } catch (e: Exception) {
            Log.e("TAG Error Update Main List Reminder", "$e")
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
            currentInnerList = dao.fetchCurrentInnerList(id)
            emit(currentInnerList)
        } catch (e: Exception) {
            Log.e("TAG Error Fetch Inner List", "$e")
        }
    }


}