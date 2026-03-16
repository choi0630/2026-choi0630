package com.example.console.data.model

enum class ItemType {
    TASK, NOTE;
    companion object{
        fun fromInput(input:String): ItemType? =
            entries.firstOrNull {
                it.name.equals(input.trim(),ignoreCase = true)
            }
    }
}