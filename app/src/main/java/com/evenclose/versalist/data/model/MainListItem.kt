package com.evenclose.versalist.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.Instant

@Entity(tableName = "main_list")
data class MainListItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")                                    var id: Int? = null,
    @ColumnInfo(name = "name")                                  val name: String,
    @ColumnInfo(name = "type")                                  var type: String,
    @ColumnInfo(name = "category")                              var category: String,
    @ColumnInfo(name = "isFav")                                 var isFav: Boolean,
): Serializable