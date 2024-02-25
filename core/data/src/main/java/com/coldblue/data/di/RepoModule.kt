package com.coldblue.data.di

import com.coldblue.data.repo.MandaRepo
import com.coldblue.data.repo.MandaRepoImpl
import com.coldblue.data.repo.TodoRepository
import com.coldblue.data.repo.TodoRepositoryImpl
import com.coldblue.data.repo.UserRepo
import com.coldblue.data.repo.UserRepoImpl
import com.coldblue.data.util.LoginHelper
import com.coldblue.data.util.LoginHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {
    @Binds
    fun bindMandaRepo(
        mandaRepo: MandaRepoImpl
    ): MandaRepo
    @Binds
    fun bindTodoRepo(
        todoRepo: TodoRepositoryImpl
    ): TodoRepository
    @Binds
    fun bindUserPreferencesRepo(
        userPreferencesRepo: UserRepoImpl
    ): UserRepo
    @Binds
    fun bindLoginHelperRepo(
        loginHelper: LoginHelperImpl
    ): LoginHelper

}