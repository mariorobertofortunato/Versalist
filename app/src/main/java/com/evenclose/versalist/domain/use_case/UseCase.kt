package com.evenclose.versalist.domain.use_case

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
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
@Immutable
class UseCase(
    var fetchAllListsUseCase: FetchAllListsUseCase,
    var getListDataUseCase: GetListDataUseCase,
    var addNewListUseCase: AddNewListUseCase,
    var addNewInnerListItemUseCase: AddNewInnerListItemUseCase,
    var getCurrentInnerListUseCase: GetCurrentInnerListUseCase,
    var updateItemCheckStatusUseCase: UpdateItemCheckStatusUseCase,
    var deleteMainListItemUseCase: DeleteMainListItemUseCase,
    var deleteInnerListItemUseCase: DeleteInnerListItemUseCase,
    var updateMainListFavouriteStatusUseCase: UpdateMainListFavouriteStatusUseCase,
    var deleteInnerListItemFromMainListUseCase: DeleteInnerListItemFromMainListUseCase,
)