package com.example.smarttasknotes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasknotes.data.mock.MockDataFactory
import com.example.smarttasknotes.data.model.TaskNoteType
import com.example.smarttasknotes.ui.components.TaskNoteItem
import com.example.smarttasknotes.ui.components.TaskNoteTitle

@Composable
fun Week04HomeScreenA(modifier: Modifier = Modifier) {
    val itemList = remember {
        mutableStateListOf<TaskNoteType>().apply {
            addAll(MockDataFactory.getDataList())
        }
    }

    var title by remember { mutableStateOf("") }

    fun addTaskItem() {
        if (title.isEmpty()) return
        val task = TaskNoteType.Task(id = itemList.size + 1, title = title)
        itemList.add(task)
        title = ""
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TaskNoteTitle()

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { title = it },
            label = { Text(text = "제목") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { addTaskItem() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "등록")
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (itemList.isEmpty()) {
            Text(
                text = "등록된 항목이 없습니다.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            Column {
                itemList.forEach { item ->
                    TaskNoteItem(item = item)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Week04HomeScreenAPreview() {
    MaterialTheme {
        Week04HomeScreenA()
    }
}