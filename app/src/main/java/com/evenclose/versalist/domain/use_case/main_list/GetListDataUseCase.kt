package com.evenclose.versalist.domain.use_case.main_list

import com.evenclose.versalist.data.repository.ListRepository
import javax.inject.Inject

class GetListDataUseCase @Inject constructor(private val listRepository: ListRepository) {

    suspend operator fun invoke(id: Int) = listRepository.getListData(id)

}