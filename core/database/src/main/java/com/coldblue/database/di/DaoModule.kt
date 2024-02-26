package com.coldblue.database.di

import com.coldblue.database.AppDataBase
import com.coldblue.database.dao.CurrentGroupDao
import com.coldblue.database.dao.MandaDao
import com.coldblue.database.dao.MandaDetailDao
import com.coldblue.database.dao.TodoDao
import com.coldblue.database.dao.TodoGroupDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Singleton
    @Provides
    fun provideCurrentGroupDao(dataBase: AppDataBase): CurrentGroupDao =
        dataBase.currentGroupDao()

    @Singleton
    @Provides
    fun provideMandaDao(dataBase: AppDataBase): MandaDao =
        dataBase.mandaDao()
    @Singleton
    @Provides
    fun provideMandaDetailDao(dataBase: AppDataBase): MandaDetailDao =
        dataBase.mandaDetailDao()
    @Singleton
    @Provides
    fun provideTodoDao(dataBase: AppDataBase): TodoDao =
        dataBase.todoDao()
    @Singleton
    @Provides
    fun provideTodoGroupDao(dataBase: AppDataBase): TodoGroupDao =
        dataBase.todoGroupDao()

}