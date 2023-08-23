package com.evenclose.versalistpro.domain.use_case

import com.evenclose.versalistpro.domain.use_case.list.AddNewList
import com.evenclose.versalistpro.domain.use_case.list.FetchAllListsUseCase
import com.evenclose.versalistpro.domain.use_case.list.GetListDataUseCase

class UseCase (
    var FetchAllListsUseCase: FetchAllListsUseCase,
    var GetListDataUseCase: GetListDataUseCase,
    var AddNewList: AddNewList
        )