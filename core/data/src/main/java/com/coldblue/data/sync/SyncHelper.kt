package com.coldblue.data.sync

import com.coldblue.database.entity.SyncableEntity

interface SyncHelper {
    fun syncWrite()

    fun initialize()

//    suspend fun <T> toSyncData(getToSyncData: suspend (String) -> List<T>,updateTime:String): List<T>

    suspend fun setMaxUpdateTime(data: List<SyncableEntity>,updateTime: suspend (String)->Unit)
}