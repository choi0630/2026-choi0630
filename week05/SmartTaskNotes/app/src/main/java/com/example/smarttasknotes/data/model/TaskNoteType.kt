package com.example.smarttasknotes.data.model

import com.example.smarttasknotes.util.nowText

sealed class TaskNoteType {
    abstract val id: Int
    abstract val title: String

    data class Task(
        override val id: Int,
        override val title: String,
        val dueDate: String=nowText(),
        val done: Boolean = false
    ) : TaskNoteType()

    data class Note(
        override val id: Int,
        override val title: String,
        val content: String
    ) : TaskNoteType()
}

