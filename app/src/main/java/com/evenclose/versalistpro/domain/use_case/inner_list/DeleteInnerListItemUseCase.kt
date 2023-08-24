package com.evenclose.versalistpro.domain.use_case.inner_list

import com.evenclose.versalistpro.data.repository.ListRepository
import javax.inject.Inject

class DeleteInnerListItemUseCase @Inject constructor(private val listRepository: ListRepository) {

    suspend operator fun invoke(id: Int) = listRepository.deleteInnerListItem(id)

}