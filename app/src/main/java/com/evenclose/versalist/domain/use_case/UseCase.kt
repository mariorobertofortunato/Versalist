package com.evenclose.versalist.domain.use_case

import com.evenclose.versalist.domain.use_case.inner_list.AddNewInnerListItemUseCase
import com.evenclose.versalist.domain.use_case.inner_list.DeleteInnerListItemFromMainListUseCase
import com.evenclose.versalist.domain.use_case.main_list.AddNewListUseCase
import com.evenclose.versalist.domain.use_case.inner_list.DeleteInnerListItemUseCase
import com.evenclose.versalist.domain.use_case.main_list.DeleteMainListItemUseCase
import com.evenclose.versalist.domain.use_case.main_list.FetchAllListsUseCase
import com.evenclose.versalist.domain.use_case.inner_list.GetCurrentInnerListUseCase
import com.evenclose.versalist.domain.use_case.main_list.GetListDataUseCase
import com.evenclose.versalist.domain.use_case.inner_list.UpdateItemCheckStatusUseCase
import com.evenclose.versalist.domain.use_case.main_list.UpdateMainListFavouriteStatusUseCase

class UseCase (
    var FetchAllListsUseCase: FetchAllListsUseCase,
    var GetListDataUseCase: GetListDataUseCase,
    var AddNewListUseCase: AddNewListUseCase,
    var AddNewInnerListItemUseCase: AddNewInnerListItemUseCase,
    var GetCurrentInnerListUseCase: GetCurrentInnerListUseCase,
    var UpdateItemCheckStatusUseCase: UpdateItemCheckStatusUseCase,
    var DeleteMainListItemUseCase: DeleteMainListItemUseCase,
    var DeleteInnerListItemUseCase: DeleteInnerListItemUseCase,
    var UpdateMainListFavouriteStatusUseCase: UpdateMainListFavouriteStatusUseCase,
    var DeleteInnerListItemFromMainListUseCase: DeleteInnerListItemFromMainListUseCase,
        )