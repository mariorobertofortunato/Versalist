package com.evenclose.versalistpro.di

import android.app.Application
import androidx.room.Room
import com.evenclose.versalistpro.data.database.ListDao
import com.evenclose.versalistpro.data.database.ListDatabase
import com.evenclose.versalistpro.data.repository.ListRepository
import com.evenclose.versalistpro.domain.use_case.UseCase
import com.evenclose.versalistpro.domain.use_case.list.AddNewList
import com.evenclose.versalistpro.domain.use_case.list.FetchAllListsUseCase
import com.evenclose.versalistpro.domain.use_case.list.GetListDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ListDatabase {
        return Room.databaseBuilder(app, ListDatabase::class.java, "list_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(database: ListDatabase): ListDao {
        return database.listDao
    }

    @Singleton
    @Provides
    fun provideListRepository(
        dao: ListDao,
    ): ListRepository = ListRepository(dao)

    @Singleton
    @Provides
    fun provideUseCase(
        listRepository: ListRepository,
    ): UseCase = UseCase(
        FetchAllListsUseCase(listRepository),
        GetListDataUseCase(listRepository),
        AddNewList(listRepository)
    )
}