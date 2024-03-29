package com.coldblue.datastore

import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    val token: Flow<String>
    val isStarted: Flow<Boolean>
    val email: Flow<String>
    val isTutorial: Flow<Boolean>
    val isAlarm: Flow<Boolean>
    val mandaInitState: Flow<Boolean>
    val permissionInitState: Flow<Boolean>


    suspend fun updateToken(token: String)
    suspend fun updateStarted(state: Boolean)
    suspend fun updateEmail(email: String)
    suspend fun updateTutorial(state: Boolean)
    suspend fun updateAlarm(state: Boolean)
    suspend fun updateMandaInitState(state: Boolean)
    suspend fun updatePermissionInitState(state: Boolean)
    suspend fun reset()
}