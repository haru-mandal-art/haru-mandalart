package com.coldblue.model


data class ToggleInfo(
    val isChecked: Boolean,
    val text: String,
    val todoGroupId: Int = -1,
    val plus: Long = 0
)
