package com.coldblue.data.repo

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepo {
    val token: Flow<String>
    val todoUpdateTime: Flow<String>
    val mandaUpdateTime: Flow<String>
    val isTutorial: Flow<Boolean>
    val isAlarm: Flow<Boolean>

    suspend fun updateToken(token: String)
    suspend fun updateTodoTime(time: String)
    suspend fun updateMandaTime(time: String)
    suspend fun updateTutorial(state: Boolean)
    suspend fun updateAlarm(state: Boolean)
}