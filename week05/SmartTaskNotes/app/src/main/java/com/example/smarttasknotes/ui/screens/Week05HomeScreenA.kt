package com.example.smarttasknotes.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.smarttasknotes.ui.components.SingleChoiceButton
import com.example.smarttasknotes.ui.components.TaskNoteItem
import com.example.smarttasknotes.ui.components.TaskNoteTitle
import com.example.smarttasknotes.util.HomeTab

@Composable
fun Week05HomeScreenA(modifier: Modifier = Modifier) {

    val itemList = remember { MockDataFactory.getDataList().toMutableStateList() }
    var title by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var selectedHomeTab by remember { mutableStateOf(HomeTab.TASK) }
    var showOnlyUncompleted by remember { mutableStateOf(false) }


    fun clearInputs() {
        title = ""
        dueDate = ""
        content = ""
    }

    fun addTaskItem() {
        if (title.isNotBlank()) {
            itemList.add(0, TaskNoteType.Task(id = itemList.size, title = title, dueDate = dueDate))
            clearInputs()
        }
    }

    fun addNoteItem() {
        if (title.isNotBlank() && content.isNotBlank()) {
            itemList.add(0, TaskNoteType.Note(id = itemList.size, title = title, content = content))
            clearInputs()
        }
    }

    val toggleTaskDone: (Int) -> Unit = { taskId ->
        val index = itemList.indexOfFirst { it is TaskNoteType.Task && it.id == taskId }
        if (index != -1) {
            val task = itemList[index] as TaskNoteType.Task
            itemList[index] = task.copy(done = !task.done)
        }
    }


    val filteredItems = if (showOnlyUncompleted) {
        itemList.filter { it is TaskNoteType.Task && !it.done }
    } else {
        itemList
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TaskNoteTitle()

        Spacer(modifier = Modifier.height(20.dp))

        // [사진 4] SingleChoiceButton 호출
        SingleChoiceButton(
            selectedHomeTab = selectedHomeTab,
            onChangeSelectedHomeTab = { selectedHomeTab = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("제목") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // [사진 5] 탭에 따른 입력 필드 분기
        if (selectedHomeTab == HomeTab.TASK) {
            OutlinedTextField(
                value = dueDate,
                onValueChange = { dueDate = it },
                label = { Text("마감일") },
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("내용") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (selectedHomeTab == HomeTab.TASK) addTaskItem() else addNoteItem()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("등록")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("미완성만 보기")
            Switch(
                checked = showOnlyUncompleted,
                onCheckedChange = { showOnlyUncompleted = it }
            )
        }


        LazyColumn(
            contentPadding = PaddingValues(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(items = filteredItems) { item ->
                TaskNoteItem(
                    item = item,
                    toggleTaskDone = toggleTaskDone
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Week05HomeScreenAPreview() {
    MaterialTheme {
        Week05HomeScreenA()
    }
}


