package com.example.console.data.model

enum class Priority {
    P1, P2, P3;
    companion object {
        fun fromInput(input: String): Priority? =
            entries.firstOrNull { it.name.equals(input.trim(), ignoreCase = true) }
    }
}