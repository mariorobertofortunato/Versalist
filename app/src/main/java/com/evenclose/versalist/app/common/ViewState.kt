package com.evenclose.versalist.app.common

import com.evenclose.versalist.domain.model.BaseModel
import java.io.Serializable

sealed class ViewState : Serializable {
    var isLoading = false

    init {
        if (this is Loading) isLoading = true
    }

    data object Loading : ViewState()
    data object None : ViewState()
    data class Done(val model: BaseModel = BaseModel()) : ViewState()
    data class Error(
        @Transient val exception: Throwable? = Exception(),
        val code: Int? = null,
        val message: String? = null
    ) : ViewState()


}