package com.evenclose.versalist.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evenclose.versalist.data.model.InnerListItem
import com.evenclose.versalist.data.model.MainListItem
import java.time.Instant

@Dao
interface ListDao {

    /** MAIN LIST */
    @Query("SELECT * FROM main_list ORDER BY isFav DESC")
    suspend fun fetchAllLists(): List<MainListItem>

    @Query("SELECT * FROM main_list WHERE id = :id")
    suspend fun fetchCurrentListData(id: Int): MainListItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewList(newList: MainListItem)

    @Query("DELETE FROM main_list WHERE id = :id")
    suspend fun deleteMainListItem(id: Int)

    @Query("UPDATE main_list SET isFav=:newFavouriteStatus WHERE id = :id")
    suspend fun updateMainListFavouriteStatus(id: Int, newFavouriteStatus: Boolean)

    @Query("UPDATE main_list SET reminderDate=:reminderDate WHERE id = :id")
    suspend fun updateMainListReminder(id: Int, reminderDate: Instant?)

    /** INNER LIST */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewInnerListItem(newItem: InnerListItem)

    @Query("SELECT * FROM inner_list WHERE mainListId = :id")
    suspend fun fetchCurrentInnerList(id: Int): List<InnerListItem>

    @Query("DELETE FROM inner_list WHERE id = :id")
    suspend fun deleteInnerListItem(id: Int)

    @Query("DELETE FROM inner_list WHERE mainListId = :id")
    suspend fun deleteInnerListItemFromMainList(id: Int)

    @Query("UPDATE inner_list SET isChecked=:newCheckStatus WHERE id = :id")
    suspend fun updateItemCheckStatus(id: Int, newCheckStatus: Boolean)

}