package com.evenclose.versalistpro.domain.use_case.list

import com.evenclose.versalistpro.data.repository.ListRepository
import javax.inject.Inject

class UpdateItemCheckStatusUseCase @Inject constructor(private val listRepository: ListRepository) {

    suspend operator fun invoke(id: Int, newCheckStatus: Boolean) = listRepository.updateItemCheckStatus(id, newCheckStatus)

}