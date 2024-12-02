package com.evenclose.versalist.domain.use_case.inner_list

import com.evenclose.versalist.data.repository.ListRepository
import javax.inject.Inject

class GetCurrentInnerListUseCase @Inject constructor(private val listRepository: ListRepository) {

    suspend operator fun invoke(id: Int) = listRepository.getCurrentInnerList(id)

}