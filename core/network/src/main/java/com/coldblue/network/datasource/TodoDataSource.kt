package com.coldblue.network.datasource

import com.coldblue.network.model.NetworkTodo


interface TodoDataSource {

    suspend fun getTodo(update: String): List<NetworkTodo>

    suspend fun upsertTodo(todo: List<NetworkTodo>): List<Int>
}