package com.coldblue.data.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val token: Flow<String>
    val isTutorial: Flow<Boolean>
    val isAlarm: Flow<Boolean>
    val isInit: Flow<Boolean>


    suspend fun updateToken(token: String)
    suspend fun updateTutorial(state: Boolean)
    suspend fun updateAlarm(state: Boolean)
    suspend fun updateInit(state: Boolean)
}