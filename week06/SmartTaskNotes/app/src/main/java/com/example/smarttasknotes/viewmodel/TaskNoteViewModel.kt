package com.example.smarttasknotes.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.smarttasknotes.data.mock.MockDataFactory
import com.example.smarttasknotes.data.model.TaskNoteType
import com.example.smarttasknotes.util.HomeTab

class TaskNoteViewModel : ViewModel() {
    val itemList =
        MockDataFactory
            .getDataList().toMutableStateList()

    var title by mutableStateOf("")
    val onTitleChange: (String) -> Unit = { title = it }
    var dueDate by mutableStateOf("")
    val onDueDateChange: (String) -> Unit = { dueDate = it }
    var content by mutableStateOf("")
    val onContentChange: (String) -> Unit = { content = it }

    var selectedHomeTab by mutableStateOf(HomeTab.TASK)
    val onHomeTabChange: (HomeTab) -> Unit = { selectedHomeTab = it }
    var showOnlyUncompleted by mutableStateOf(false)
    val onSwitchChange: (Boolean) -> Unit = { showOnlyUncompleted = it }

    val filteredItems by derivedStateOf {
        when (selectedHomeTab) {
            HomeTab.TASK -> {
                itemList.filterIsInstance<TaskNoteType.Task>()
                    .filter { !showOnlyUncompleted || !it.done }
            }

            HomeTab.NOTE -> {
                itemList.filterIsInstance<TaskNoteType.Note>()
            }
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

    val onDeleteTask: (TaskNoteType.Task) -> Unit = { task ->
        itemList.remove(task)
    }

}