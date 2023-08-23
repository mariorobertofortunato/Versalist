package com.evenclose.versalistpro.data.database

import androidx.room.Dao
import androidx.room.Query
import com.evenclose.versalistpro.data.model.MainListItem

@Dao
interface ListDao {

    @Query("SELECT * FROM main_list")
    fun fetchAllLists(): List<MainListItem>

    @Query("SELECT * FROM main_list WHERE name = :name")
    suspend fun getListData(name: String): MainListItem

}