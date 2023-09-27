package com.evenclose.versalistpro.domain.use_case.main_list

import com.evenclose.versalistpro.data.repository.ListRepository
import java.time.Instant
import java.util.Calendar
import javax.inject.Inject

class UpdateMainListReminderUseCase @Inject constructor(private val listRepository: ListRepository) {

    suspend operator fun invoke(id: Int, reminderDate: Instant?) = listRepository.updateMainListReminder(id, reminderDate)

}