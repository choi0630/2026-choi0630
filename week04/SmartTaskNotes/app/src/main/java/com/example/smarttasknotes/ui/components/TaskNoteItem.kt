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


@Composable
fun TaskNoteItem(item: TaskNoteType, modifier: Modifier = Modifier) {
    
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
