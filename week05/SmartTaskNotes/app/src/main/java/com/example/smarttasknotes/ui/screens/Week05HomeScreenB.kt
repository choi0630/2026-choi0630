package com.example.smarttasknotes.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.smarttasknotes.ui.components.SingleChoiceButtonB
import com.example.smarttasknotes.ui.components.TaskNoteItem
import com.example.smarttasknotes.ui.components.TaskNoteTitle
import com.example.smarttasknotes.ui.components.TextInput
import com.example.smarttasknotes.util.HomeTab
import kotlin.collections.filter
import kotlin.collections.forEach
import kotlin.collections.indexOfFirst
import kotlin.text.isBlank
import kotlin.text.isEmpty


// 1) LazyColumn 적용하기
// 2) SingleChoiceButton 적용하기
// 3) 등록 버튼 클릭 : Task 또는 Note 저장

@Composable
fun Week05HomeScreenB(modifier: Modifier = Modifier) {
    val itemList = remember {
        MockDataFactory
            .getDataList().toMutableStateList()
    }

    var title by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    var selectedHomeTab by remember { mutableStateOf(HomeTab.TASK) }
    var showOnlyUncompleted by remember { mutableStateOf(false) }
    val filteredItems = when (selectedHomeTab) {
        HomeTab.TASK -> {
            itemList.filterIsInstance<TaskNoteType.Task>()
                .filter { !showOnlyUncompleted || !it.done }
        }

        HomeTab.NOTE -> {
            itemList.filterIsInstance<TaskNoteType.Note>()
        }
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

        SingleChoiceButtonB(
            selectedHomeTab = selectedHomeTab,
            onSelectedChange = { selectedHomeTab = it }
        )
        Spacer(modifier = Modifier.height(8.dp))

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

        if (selectedHomeTab == HomeTab.NOTE) {
            TextInput(content, "📝 내용", {content = it})
        }else{
            TextInput(dueDate, "📅 마감일", {dueDate = it})
        }

        Button(
            onClick = {
                when(selectedHomeTab){
                    HomeTab.NOTE -> addNoteItem()
                    HomeTab.TASK -> addTaskItem()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("등록")
        }

        Spacer(modifier = Modifier.height(10.dp))

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

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = filteredItems){
                    TaskNoteItem(
                        item = it,
                        toggleTaskDone = toggleTaskDone
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Week05HomeScreenAPreview() {
    Week05HomeScreenB()
}