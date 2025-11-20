package com.evenclose.versalist.data.model

import com.evenclose.versalist.R
import com.evenclose.versalist.data.model.PopupTypeKey.ABOUT
import com.evenclose.versalist.data.model.PopupTypeKey.DELETE_MAIN_LIST_ITEM
import com.evenclose.versalist.data.model.PopupTypeKey.HELP
import com.evenclose.versalist.data.model.PopupTypeKey.LANGUAGE
import com.evenclose.versalist.data.model.PopupTypeKey.PRIVACY

object PopupTypes {

    val help = Popup(
        type = HELP,
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
}

object PopupTypeKey {
    const val HELP = "Help"
    const val ABOUT = "About"
    const val PRIVACY = "Privacy"
    const val LANGUAGE = "Language"
    const val DELETE_MAIN_LIST_ITEM = "DeleteMainListItem"
}

data class Popup(
    val type: String,
    val animationResId: Int,
    val infiniteAnimation: Boolean = false
)

