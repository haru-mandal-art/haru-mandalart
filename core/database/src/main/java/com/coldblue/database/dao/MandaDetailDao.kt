package com.coldblue.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.coldblue.database.entity.MandaDetailEntity
import com.coldblue.database.entity.MandaKeyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MandaDetailDao {
    @Query("Select * From manda_detail")
    fun getMandaDetails(): Flow<List<MandaDetailEntity>>

    @Query("SELECT * FROM manda_detail WHERE update_time > :updateTime AND is_sync=0")
    fun getToWriteMandaDetail(updateTime: String): List<MandaDetailEntity>
    @Transaction
    fun getMandaDetailIdByOriginIds(originIds: List<Int>): List<Int?> {
        return originIds.map { originId ->
            getMandaDetailIdByOriginId(originId)
        }
    }

    @Query("SELECT id FROM manda_detail WHERE origin_id = :originId")
    fun getMandaDetailIdByOriginId(originId: Int): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMandaDetails(mandaDetailEntity: List<MandaDetailEntity>)

    @Query("Update manda_detail Set is_del = 1, is_Sync = 0, update_time = :date Where id in (:id)")
    suspend fun deleteMandaDetails(date: String, id: List<Int>)

    @Query("Update manda_detail Set is_del = 1, is_Sync = 0, update_time = :date")
    suspend fun deleteAllMandaDetail(date: String)
}