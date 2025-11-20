package com.evenclose.versalist.domain.model

import com.evenclose.versalist.R
import com.evenclose.versalist.domain.model.PopupTypeKey.ABOUT
import com.evenclose.versalist.domain.model.PopupTypeKey.DELETE_INNER_LIST_ITEM
import com.evenclose.versalist.domain.model.PopupTypeKey.DELETE_MAIN_LIST_ITEM
import com.evenclose.versalist.domain.model.PopupTypeKey.HELP_LIST_SCREEN
import com.evenclose.versalist.domain.model.PopupTypeKey.HELP_MAIN_SCREEN
import com.evenclose.versalist.domain.model.PopupTypeKey.LANGUAGE
import com.evenclose.versalist.domain.model.PopupTypeKey.PRIVACY

object PopupTypes {

    val helpMainScreen = Popup(
        type = HELP_MAIN_SCREEN,
        animationResId = R.raw.animation_help,
        infiniteAnimation = true
    )

    val helpListScreen = Popup(
        type = HELP_LIST_SCREEN,
        animationResId = R.raw.animation_help,
        infiniteAnimation = true
    )

    val about = Popup(
        type = ABOUT,
        animationResId = R.raw.animation_info,
    )

    val privacy = Popup(
        type = PRIVACY,
        animationResId = R.raw.animation_privacy,
    )

    val language = Popup(
        type = LANGUAGE,
        animationResId = R.raw.animation_language,
    )

    val deleteMainListItem = Popup(
        type = DELETE_MAIN_LIST_ITEM,
        animationResId = R.raw.animation_warning,
    )

    val deleteInnerListItem = Popup(
        type = DELETE_INNER_LIST_ITEM,
        animationResId = R.raw.animation_warning,
    )
}

object PopupTypeKey {
    const val HELP_MAIN_SCREEN = "Help Main Screen"
    const val HELP_LIST_SCREEN = "Help List Screen"
    const val ABOUT = "About"
    const val PRIVACY = "Privacy"
    const val LANGUAGE = "Language"
    const val DELETE_MAIN_LIST_ITEM = "DeleteMainListItem"
    const val DELETE_INNER_LIST_ITEM = "DeleteInnerListItem"
}

data class Popup(
    val type: String,
    val animationResId: Int,
    val infiniteAnimation: Boolean = false
)

