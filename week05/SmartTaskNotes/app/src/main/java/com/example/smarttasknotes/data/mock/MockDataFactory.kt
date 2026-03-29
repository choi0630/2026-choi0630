package com.example.smarttasknotes.data.mock

import com.example.smarttasknotes.data.model.TaskNoteType

object MockDataFactory {
    fun getDataList() = listOf(
        TaskNoteType.Task(
            id = 1,
            title = "모프 과제 제출",
            dueDate = "2026-03-20",
            done = false
        ),
        TaskNoteType.Task(
            id = 2,
            title = "모프 복습",
            dueDate = "2026-03-22",
            done = true
        ),
        TaskNoteType.Note(
            id = 3,
            title = "봄꽃",
            content = "벌써 목련이 피었다. 참 이쁘다."
        ),
        TaskNoteType.Note(
            id = 4,
            title = "슬로우 러닝",
            content = "걷기보다 좋은 것 맞나? 암튼 해보자."
        )
    )
}