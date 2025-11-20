package com.evenclose.versalist.domain.use_case.inner_list

import com.evenclose.versalist.data.model.InnerListItem
import com.evenclose.versalist.data.repository.ListRepository
import javax.inject.Inject

class AddNewInnerListItemUseCase @Inject constructor(private val listRepository: ListRepository) {

    suspend operator fun invoke(innerListItem: InnerListItem) = listRepository.addNewInnerListItem(innerListItem)

}