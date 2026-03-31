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

// TODO : TaskItem04를 수행할 수 있도록 TaskNoteItem04 바꾸기
@Composable
fun TaskNoteItem04(
    item: TaskNoteType,
    modifier: Modifier = Modifier,
    toggleTaskDone: (Int) -> Unit
) {
    when (item) {
        is TaskNoteType.Task -> TaskItem04(item = item, onChecked = {toggleTaskDone(item.id)})
        is TaskNoteType.Note -> NoteItem(item = item)
    }
}

// TODO : TaskNoteItem04 미리보기 구현하기
@Preview
@Composable
private fun TaskNoteItem04Preview() {
    val items = MockDataFactory.getDataList()
    Column {
        items.forEach { item ->
            TaskNoteItem04(item = item, toggleTaskDone = {})
            Spacer(Modifier.height(8.dp))
        }
    }


}
