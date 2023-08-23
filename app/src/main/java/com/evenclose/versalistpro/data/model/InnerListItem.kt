package com.evenclose.versalistpro.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "inner_list")
data class InnerListItem(
    @PrimaryKey(autoGenerate = false)
    val name: String = "",
    val isChecked: Boolean,
) : Parcelable