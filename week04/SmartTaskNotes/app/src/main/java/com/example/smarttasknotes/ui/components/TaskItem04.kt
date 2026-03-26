package com.example.smarttasknotes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasknotes.data.mock.MockDataFactory
import com.example.smarttasknotes.data.model.TaskNoteType

@Composable
fun TaskItem04(
    item: TaskNoteType.Task,
    toggleTaskDone: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card {

        //TODO : 체크박스 그림문자 대신에 CheckBox 컴포넌트 추가하기

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = item.done,
                    onCheckedChange = {
                        toggleTaskDone(item.id)
                    }
                )

                Text(
                    text = " ${item.title}",
                    textDecoration = if (item.done) TextDecoration.LineThrough else TextDecoration.None,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "📅 ${item.dueDate}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

    @Preview
    @Composable
    private fun TaskItemPreview() {
        val items = MockDataFactory.getDataList().filterIsInstance<TaskNoteType.Task>()
        Column {
            TaskItem04(item = items[0], toggleTaskDone = {})
            Spacer(modifier = Modifier.padding(8.dp))

        }
    }


