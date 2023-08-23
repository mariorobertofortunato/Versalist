package com.evenclose.versalistpro.domain.use_case.list

import com.evenclose.versalistpro.data.repository.ListRepository
import javax.inject.Inject

class GetCurrentInnerListUseCase @Inject constructor(private val listRepository: ListRepository) {

    suspend operator fun invoke(id: Int) = listRepository.getCurrentInnerList(id)

}