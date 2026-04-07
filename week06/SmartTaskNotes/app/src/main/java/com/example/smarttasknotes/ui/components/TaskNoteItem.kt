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
fun TaskNoteItem(
    item: TaskNoteType,
    toggleTaskDone: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    when (item) {
        is TaskNoteType.Task -> TaskItem(
            item = item,
            toggleTaskDone = toggleTaskDone,
            modifier = modifier
        )
        is TaskNoteType.Note -> NoteItem(
            item = item,
            modifier = modifier
        )
    }
}

@Preview
@Composable
private fun TaskNoteItemPreview() {
    val items = MockDataFactory.getDataList()
    Column {
        items.forEach { item ->
            TaskNoteItem(item = item, toggleTaskDone = {})
            Spacer(Modifier.height(8.dp))
        }
    }
}
