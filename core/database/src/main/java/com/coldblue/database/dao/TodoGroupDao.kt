package com.coldblue.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.coldblue.database.entity.TodoGroupEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoGroupDao {
    @Query("Select * From todo_group WHERE is_del=0")
    fun getTodoGroup(): Flow<List<TodoGroupEntity>>

    @Query("UPDATE todo_group SET name = :name , update_time = :updateTime,is_sync=0 WHERE id = :todoGroupId")
    suspend fun upsertTodoGroup2(todoGroupId: Int, name: String, updateTime: String)

    @Query("UPDATE todo_group SET name = :name , update_time = :updateTime,is_sync=0 WHERE origin_id = :originId")
    suspend fun upsertTodoGroupOrigin(originId: Int, name: String, updateTime: String)

    @Transaction
    suspend fun upsertTodoGroup(
        originGroupId: Int, todoGroupId: Int, name: String, updateTime: String
    ) {
        if (originGroupId == 0) {
            upsertTodoGroup2(todoGroupId, name, updateTime)
        } else {
            upsertTodoGroupOrigin(originGroupId, name, updateTime)
        }

    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTodoGroup(todoGroup: TodoGroupEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTodoGroupList(todoGroups: List<TodoGroupEntity>)

    @Transaction
    suspend fun syncWriteTodoGroup(
        todoGroups: List<TodoGroupEntity>,
        currentGroupUpdateTime: String
    ) {
        upsertTodoGroupList(todoGroups)
        todoGroups.forEach {
            updateOriginGroupId(it.originId, it.id, currentGroupUpdateTime)
            updateTodoGroupId(it.originId, it.id, currentGroupUpdateTime)
        }
    }

    @Query("UPDATE current_group SET is_sync=0, update_time=:updateTime, origin_group_id = :originId WHERE todo_group_id = :todoGroupId")
    suspend fun updateOriginGroupId(originId: Int, todoGroupId: Int, updateTime: String): Int

    @Query("UPDATE todo SET is_sync=0, update_time=:updateTime, origin_group_id = :originId WHERE todo_group_id = :todoGroupId")
    suspend fun updateTodoGroupId(originId: Int, todoGroupId: Int, updateTime: String): Int


    @Transaction
    suspend fun deleteTodoGroupAndRelated(todoGroupId: Int, updateTime: String) {
        deleteTodoGroup(todoGroupId, updateTime)
        deleteCurrentGroup(todoGroupId, updateTime)
        setOffTodo(todoGroupId, updateTime)
    }

    @Query("UPDATE todo_group SET is_del = 1 , update_time = :updateTime,is_sync = 0 WHERE id = :todoGroupId")
    suspend fun deleteTodoGroup(todoGroupId: Int, updateTime: String)

    @Query("UPDATE current_group SET is_del = 1  , update_time = :updateTime ,is_sync = 0 WHERE todo_group_id = :todoGroupId")
    suspend fun deleteCurrentGroup(todoGroupId: Int, updateTime: String)

    @Query("UPDATE todo SET todo_group_id = NULL , update_time = :updateTime ,is_sync = 0 WHERE todo_group_id = :todoGroupId")
    suspend fun setOffTodo(todoGroupId: Int, updateTime: String)


    @Query("SELECT * FROM todo_group WHERE update_time > :updateTime AND is_sync=0")
    fun getToWriteTodoGroups(updateTime: String): List<TodoGroupEntity>

    @Transaction
    fun getTodoGroupIdByOriginIds(originIds: List<Int>): List<Int?> {
        return originIds.map { originId ->
            getTodoGroupIdByOriginId(originId)
        }
    }

    @Query("SELECT id FROM todo_group WHERE origin_id = :originId")
    fun getTodoGroupIdByOriginId(originId: Int): Int?
}