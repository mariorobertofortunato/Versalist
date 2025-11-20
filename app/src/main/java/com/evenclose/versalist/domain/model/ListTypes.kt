package com.evenclose.versalist.domain.model

import com.evenclose.versalist.domain.model.ListTypeKey.CHECKLIST
import com.evenclose.versalist.domain.model.ListTypeKey.OPEN_LIST

val listTypes = listOf(
    ListType(
        type = OPEN_LIST
    ), ListType(
        type = CHECKLIST
    )
)

object ListTypeKey {
    const val OPEN_LIST = "Open List"
    const val CHECKLIST = "Checklist"
}

data class ListType(
    val type: String,
)