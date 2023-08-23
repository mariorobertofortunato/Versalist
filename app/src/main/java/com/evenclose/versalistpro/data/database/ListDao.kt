package com.evenclose.versalistpro.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evenclose.versalistpro.data.model.MainListItem

@Dao
interface ListDao {

    /** MAIN LIST */
    @Query("SELECT * FROM main_list")
    suspend fun fetchAllLists(): List<MainListItem>

    @Query("SELECT * FROM main_list WHERE name = :name")
    suspend fun getListData(name: String): MainListItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewList(newList: MainListItem)

}