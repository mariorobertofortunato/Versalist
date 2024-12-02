package com.evenclose.versalist.domain.use_case.main_list

import com.evenclose.versalist.data.repository.ListRepository
import javax.inject.Inject

class FetchAllListsUseCase @Inject constructor(private val listRepository: ListRepository) {

    suspend operator fun invoke() = listRepository.fetchAllLists()

}