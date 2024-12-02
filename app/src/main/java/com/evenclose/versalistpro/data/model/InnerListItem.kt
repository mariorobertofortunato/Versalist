package com.evenclose.versalistpro.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "inner_list")
data class InnerListItem(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int? = null,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "isChecked") var isChecked: Boolean,
    @ColumnInfo(name = "mainListId") val mainListId: Int
): Serializable