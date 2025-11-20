package com.evenclose.versalist.domain.model

import com.evenclose.versalist.domain.model.CategoryTypeKey.HEALTH
import com.evenclose.versalist.domain.model.CategoryTypeKey.MISC
import com.evenclose.versalist.domain.model.CategoryTypeKey.PERSONAL
import com.evenclose.versalist.domain.model.CategoryTypeKey.SHOPPING
import com.evenclose.versalist.domain.model.CategoryTypeKey.SOCIAL
import com.evenclose.versalist.domain.model.CategoryTypeKey.WORK

val categoryTypes = listOf(
    Category(
        type = PERSONAL
    ),
    Category(
        type = WORK
    ),
    Category(
        type = HEALTH
    ),
    Category(
        type = SHOPPING
    ),
    Category(
        type = SOCIAL
    ),
    Category(
        type = MISC
    )
)

object CategoryTypeKey {
    const val PERSONAL = "Personal"
    const val WORK = "Work"
    const val HEALTH = "Health"
    const val SHOPPING = "Shopping"
    const val SOCIAL = "Social"
    const val MISC = "Misc"
}

data class Category(
    val type: String
)
