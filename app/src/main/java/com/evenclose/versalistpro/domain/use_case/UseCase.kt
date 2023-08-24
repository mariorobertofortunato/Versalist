package com.evenclose.versalistpro.domain.use_case

import com.evenclose.versalistpro.domain.use_case.list.AddNewInnerListItemUseCase
import com.evenclose.versalistpro.domain.use_case.list.AddNewListUseCase
import com.evenclose.versalistpro.domain.use_case.list.FetchAllListsUseCase
import com.evenclose.versalistpro.domain.use_case.list.GetCurrentInnerListUseCase
import com.evenclose.versalistpro.domain.use_case.list.GetListDataUseCase
import com.evenclose.versalistpro.domain.use_case.list.UpdateItemCheckStatusUseCase

class UseCase (
    var FetchAllListsUseCase: FetchAllListsUseCase,
    var GetListDataUseCase: GetListDataUseCase,
    var AddNewListUseCase: AddNewListUseCase,
    var AddNewInnerListItemUseCase: AddNewInnerListItemUseCase,
    var GetCurrentInnerListUseCase: GetCurrentInnerListUseCase,
    var UpdateItemCheckStatusUseCase: UpdateItemCheckStatusUseCase
        )