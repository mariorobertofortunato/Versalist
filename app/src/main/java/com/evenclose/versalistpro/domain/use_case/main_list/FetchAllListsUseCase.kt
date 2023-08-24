package com.evenclose.versalistpro.domain.use_case.main_list

import com.evenclose.versalistpro.data.repository.ListRepository
import javax.inject.Inject

class FetchAllListsUseCase @Inject constructor(private val listRepository: ListRepository) {

    suspend operator fun invoke() = listRepository.fetchAllLists()

}