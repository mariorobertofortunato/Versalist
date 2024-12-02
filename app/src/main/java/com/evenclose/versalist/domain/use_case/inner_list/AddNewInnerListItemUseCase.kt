package com.evenclose.versalist.domain.use_case.inner_list

import com.evenclose.versalist.data.repository.ListRepository
import javax.inject.Inject

class AddNewInnerListItemUseCase @Inject constructor(private val listRepository: ListRepository) {

    suspend operator fun invoke(value: String, mainListId: Int) = listRepository.addNewInnerListItem(value, mainListId)

}