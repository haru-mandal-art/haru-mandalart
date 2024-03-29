package com.coldblue.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkCurrentGroup(
    val id: Int = 0,
    val todo_group_id: Int,
    val orgin_group_id: Int,
    val index: Int,
    val date: String,
    val update_time: String,
    val is_del: Boolean,
    val user_id: String? = null,
)