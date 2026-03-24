package com.example.smarttasknotes.data.mock

import com.example.smarttasknotes.data.model.TaskNoteType

object MockDataFactory {
    // 💡 수정 포인트: 친구분이 만든 'Note' 타입은 제목(title)뿐만 아니라 내용(content)도 필요합니다.
    // 짤렸던 데이터를 채워 넣고, Note 타입에 content 매개변수를 추가했습니다.
    fun getDataList() = listOf(
        // 1. Task 타입 (체크박스 디자인)
        TaskNoteType.Task(
            id = 1,
            title = "모프 과제 제출"
        ),
        // 2. Task 타입 (체크박스 디자인)
        TaskNoteType.Task(
            id = 2,
            title = "모프 복습"
        ),
        // 3. Note 타입 (메모 아이콘 디자인)
        TaskNoteType.Note(
            id = 3,
            title = "봄꽃",
            // 💡 친구 코드에 맞게 content(내용)를 추가했습니다.
            content = "벌써 목련이 피었다. 참 이쁘다."
        ),
        // 4. Note 타입 (메모 아이콘 디자인)
        TaskNoteType.Note(
            id = 4,
            title = "슬로우 러닝",
            // 💡 친구 코드에 맞게 content(내용)를 추가했습니다.
            content = "걷기보다 좋은 것 맞니? 암튼 해보자."
        )
    )
}