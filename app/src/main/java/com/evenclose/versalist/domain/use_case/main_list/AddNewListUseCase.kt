package com.evenclose.versalist.domain.use_case.main_list

import com.evenclose.versalist.data.repository.ListRepository
import javax.inject.Inject

class AddNewListUseCase @Inject constructor(private val listRepository: ListRepository) {

    suspend operator fun invoke(name: String, type: String, category: String) = listRepository.addNewList(name, type, category)

}