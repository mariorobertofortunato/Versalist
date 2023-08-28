package com.evenclose.versalistpro.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "main_list")
data class MainListItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val name: String,
    var type: String
    ) : Parcelable