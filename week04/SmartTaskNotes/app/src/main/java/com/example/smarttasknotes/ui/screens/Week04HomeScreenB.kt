package com.example.smarttasknotes.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasknotes.data.mock.MockDataFactory
import com.example.smarttasknotes.data.model.TaskNoteType
import com.example.smarttasknotes.ui.components.TaskNoteItem04
import com.example.smarttasknotes.ui.components.TaskNoteTitle

@Composable
fun Week04HomeScreenB(modifier: Modifier = Modifier) {
    val itemList = remember {
        MockDataFactory
            .getDataList().toMutableStateList()
    }

    var title by remember { mutableStateOf("") }

    fun addTaskItem() {
        if (title.isEmpty()) return
        itemList.add(TaskNoteType.Task(id = itemList.size, title = title))
        title = ""
    }

    val toggleTaskDone = { taskId: Int ->
        val index = itemList.indexOfFirst { it is TaskNoteType.Task && it.id == taskId }
        if (index != -1) {
            val task = itemList[index] as TaskNoteType.Task
            itemList[index] = task.copy(done = !task.done)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TaskNoteTitle()
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "입력한 내용을 바로 목록에 추가해보세요 ✨",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("제목") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                addTaskItem()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("등록")
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (itemList.isEmpty()) {
            Text(
                text = "📭 아직 등록된 항목이 없습니다.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("미완성만 보기")
            // TODO : Switch 추가하기

            }

            // TODO : TaskNoteItem04가 수행되도록 수정
            itemList.forEach {
                TaskNoteItem04(item = it, toggleTaskDone = toggleTaskDone)
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
private fun Week04HomeScreenBPreview() {
    Week04HomeScreenB()
}