package com.evenclose.versalistpro.domain.use_case.inner_list

import com.evenclose.versalistpro.data.repository.ListRepository
import javax.inject.Inject

class AddNewInnerListItemUseCase @Inject constructor(private val listRepository: ListRepository) {

    suspend operator fun invoke(value: String, mainListId: Int) = listRepository.addNewInnerListItem(value, mainListId)

}