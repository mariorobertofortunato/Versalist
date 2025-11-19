package com.evenclose.versalist.data.model

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Immutable
@Entity(tableName = "inner_list")
data class InnerListItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")                val id: Int?  = null,
    @ColumnInfo(name = "name")              val name: String = "",
    @ColumnInfo(name = "isChecked")         val isChecked: Boolean,
    @ColumnInfo(name = "mainListId")        val mainListId: Int
): Serializable