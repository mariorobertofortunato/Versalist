package com.evenclose.versalist.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import java.util.Locale

fun Context.setLanguage(language: String, recreateActivity: Boolean) {
    val locale = Locale(language)
    val config = this.resources.configuration
    Locale.setDefault(locale)
    config.setLocale(locale)
    config.setLayoutDirection(locale)
    this.resources.updateConfiguration(config, resources.displayMetrics)
    if (recreateActivity) {
        this.findActivity().recreate()
    }

}

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}
