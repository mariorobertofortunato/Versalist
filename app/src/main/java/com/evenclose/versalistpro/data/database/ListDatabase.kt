package com.evenclose.versalistpro.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evenclose.versalistpro.data.model.InnerListItem
import com.evenclose.versalistpro.data.model.MainListItem

@Database(entities = [MainListItem::class, InnerListItem::class], version = 1, exportSchema = false)
abstract class ListDatabase : RoomDatabase()  {
    abstract val listDao: ListDao
}