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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasknotes.data.mock.MockDataFactory
import com.example.smarttasknotes.data.model.TaskNoteType
import com.example.smarttasknotes.ui.components.TaskNoteItem
import com.example.smarttasknotes.ui.components.TaskNoteTitle
import kotlin.collections.filter
import kotlin.collections.forEach
import kotlin.collections.indexOfFirst
import kotlin.text.isBlank
import kotlin.text.isEmpty


// 1) LazyColumn 적용하기
// 2) SingleChoiceButton 적용하기
// 3) 등록 버튼 클릭 : Task 또는 Note 저장

@Composable
fun Week05HomeScreenA(modifier: Modifier = Modifier) {
    val itemList = remember {
        MockDataFactory
            .getDataList().toMutableStateList()
    }

    var title by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    var showOnlyUncompleted by remember { mutableStateOf(false) }
    val filteredItems = if (showOnlyUncompleted) {
        itemList.filter { it is TaskNoteType.Task && !it.done }
    } else {
        itemList
    }

    val toggleTaskDone = { taskId: Int ->
        val index = itemList.indexOfFirst { it is TaskNoteType.Task && it.id == taskId }
        if (index != -1) {
            val task = itemList[index] as TaskNoteType.Task
            itemList[index] = task.copy(done = !task.done)
        }
    }

    fun clearInputs() {
        title = ""
        dueDate = ""
        content = ""
    }

    fun addTaskItem() {
        if (title.isEmpty()) return
        if (dueDate.isBlank())
            itemList.add(TaskNoteType.Task(id = itemList.size, title = title))
        else
            itemList.add(TaskNoteType.Task(id = itemList.size, title = title, dueDate = dueDate))

        clearInputs()
    }

    fun addNoteItem() {
        if (title.isBlank() || content.isBlank()) return
        val newNote = TaskNoteType.Note(
            id = itemList.size,
            title = title,
            content = content
        )
        itemList.add(newNote)

        clearInputs()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TaskNoteTitle()
        Spacer(modifier = Modifier.height(20.dp))

        // TODO : SingleChoiceButton 호출하기

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

        // TODO : Task의 마감일 또는 Note의 내용 입력

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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("미완성만 보기")
                Switch(
                    checked = showOnlyUncompleted,
                    onCheckedChange = { showOnlyUncompleted = it },
                    modifier = Modifier.padding(end = 8.dp)
                )
            }

            // TODO : LazyColumn으로 변경하기
            filteredItems.forEach {
                TaskNoteItem(
                    item = it,
                    toggleTaskDone = toggleTaskDone
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
private fun Week05HomeScreenAPreview() {
    Week05HomeScreenA()
}