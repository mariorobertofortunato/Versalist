package com.evenclose.versalist.domain.use_case.main_list

import com.evenclose.versalist.data.model.MainListItem
import com.evenclose.versalist.data.repository.ListRepository
import javax.inject.Inject

class AddNewListUseCase @Inject constructor(private val listRepository: ListRepository) {

    suspend operator fun invoke(item: MainListItem) = listRepository.addNewList(item)

}