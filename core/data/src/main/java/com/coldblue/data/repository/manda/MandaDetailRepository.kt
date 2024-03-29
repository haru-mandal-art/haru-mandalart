package com.coldblue.data.repository.manda

import com.coldblue.data.sync.Syncable
import com.coldblue.model.MandaDetail
import kotlinx.coroutines.flow.Flow

interface MandaDetailRepository : Syncable {
    fun getMandaDetails(): Flow<List<MandaDetail>>
    suspend fun upsertMandaDetails(mandaDetails: List<MandaDetail>)
    suspend fun deleteMandaDetail(idList: List<Int>)
    suspend fun deleteAllMandaDetail()
}