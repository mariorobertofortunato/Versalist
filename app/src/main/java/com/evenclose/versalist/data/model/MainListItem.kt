package com.evenclose.versalist.data.model

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Immutable
@Entity(tableName = "main_list")
data class MainListItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")                                    val id: Int? = null,
    @ColumnInfo(name = "name")                                  val name: String,
    @ColumnInfo(name = "type")                                  val type: String,
    @ColumnInfo(name = "category")                              val category: String,
    @ColumnInfo(name = "isFav")                                 val isFav: Boolean,
): Serializable