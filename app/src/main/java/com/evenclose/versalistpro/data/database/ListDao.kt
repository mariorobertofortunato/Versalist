package com.evenclose.versalistpro.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evenclose.versalistpro.data.model.InnerListItem
import com.evenclose.versalistpro.data.model.MainListItem

@Dao
interface ListDao {

    /** MAIN LIST */
    @Query("SELECT * FROM main_list")
    suspend fun fetchAllLists(): List<MainListItem>

    @Query("SELECT * FROM main_list WHERE id = :id")
    suspend fun fetchCurrentListData(id: Int): MainListItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewList(newList: MainListItem)

    /** INNER LIST */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewInnerListItem(newItem: InnerListItem)

    @Query("SELECT * FROM inner_list WHERE mainListId = :id")
    suspend fun fetchCurrentInnerList(id: Int): List<InnerListItem>

    @Query("UPDATE inner_list SET isChecked=:newCheckStatus WHERE id = :id")
    suspend fun updateItemCheckStatus(id: Int, newCheckStatus: Boolean)

}