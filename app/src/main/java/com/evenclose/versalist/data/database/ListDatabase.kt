package com.evenclose.versalist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.evenclose.versalist.data.model.InnerListItem
import com.evenclose.versalist.data.model.MainListItem

@Database(entities = [MainListItem::class, InnerListItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ListDatabase : RoomDatabase()  {
    abstract val listDao: ListDao
}