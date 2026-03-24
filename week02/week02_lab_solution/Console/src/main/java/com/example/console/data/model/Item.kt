package com.example.console.data.model

import com.example.console.util.nowText

data class Item(
    val id: Int,
    val type: ItemType,
    var title: String,
    var done: Boolean = false,
    var priority: Priority? = null,
    val createdTime: String = nowText()
)
