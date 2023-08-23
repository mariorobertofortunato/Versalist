package com.evenclose.versalistpro.domain.use_case.list

import com.evenclose.versalistpro.data.repository.ListRepository
import javax.inject.Inject

class AddNewList @Inject constructor(private val listRepository: ListRepository) {

    suspend operator fun invoke(name: String) = listRepository.addNewList(name)

}