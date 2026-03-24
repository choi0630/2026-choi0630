package com.example.smarttasknotes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasknotes.data.mock.MockDataFactory
import com.example.smarttasknotes.data.model.TaskNoteType

// 💡 수정: 중복된 함수를 지우고, item을 받아서 구분하는 로직 하나로 합쳤습니다.
@Composable
fun TaskNoteItem(item: TaskNoteType, modifier: Modifier = Modifier) {
    // 데이터가 Task(할 일)인지 Note(메모)인지에 따라 다른 디자인을 보여줍니다.
    when (item) {
        is TaskNoteType.Task -> TaskItem(item = item, modifier = modifier)
        is TaskNoteType.Note -> NoteItem(item = item, modifier = modifier)
    }
}

// 💡 수정: 미리보기 화면에서 여러 아이템이 잘 보이도록 구성했습니다.
@Preview(showBackground = true)
@Composable
private fun TaskNoteItemPreview() {
    val items = MockDataFactory.getDataList()
    Column {
        items.forEach {
            TaskNoteItem(it)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
