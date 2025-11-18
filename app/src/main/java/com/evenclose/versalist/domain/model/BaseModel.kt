package com.evenclose.versalist.domain.model

import com.evenclose.versalist.data.model.MainListItem
import java.io.Serializable

open class BaseModel: Serializable
data class ListsModel (
    val lists: List<MainListItem>
): BaseModel()