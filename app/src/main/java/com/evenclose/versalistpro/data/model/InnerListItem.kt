package com.evenclose.versalistpro.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "inner_list")
data class InnerListItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val name: String = "",
    var isChecked: Boolean,
    val mainListId: Int
) : Parcelable