package com.example.smarttasknotes.viewmodel

import android.util.Log.i
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.smarttasknotes.data.mock.MockDataFactory
import com.example.smarttasknotes.data.model.TaskNoteType
import com.example.smarttasknotes.util.HomeTab

data class HomeUiState(
    val selectedHomeTab: HomeTab = HomeTab.TASK,
    val showOnlyUncompleted: Boolean = false,
    val filteredItems: List<TaskNoteType> = emptyList(),
    val isEmpty: Boolean = true
)

data class AddUiState(
    val editingId: Int = -1,
    val title: String = "",
    val dueDate: String = "",
    val content: String = "",
    val selectedHomeTab: HomeTab = HomeTab.TASK
)

data class HomeUiActions(
    val onHomeTabChange: (HomeTab) -> Unit,
    val onSwitchChange: (Boolean) -> Unit,
    val toggleTaskDone: (Int) -> Unit,
    val onChangeEditingId: (Int) -> Unit
)

data class AddUiActions(
    val onTitleChange: (String) -> Unit,
    val onDueDateChange: (String) -> Unit,
    val onContentChange: (String) -> Unit,
    val saveItem:()->Unit,
)


class TaskNoteViewModelB : ViewModel() {
    val itemList = MockDataFactory.getDataList().toMutableStateList()

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

    var editingId by mutableStateOf(-1)
    val onChangeEditingId: (Int) -> Unit = { id ->
        editingId = id
        if (id != -1) {
            val item = itemList.find { it.id == id }
            item?.let {
                title = it.title
                when (it) {
                    is TaskNoteType.Task -> {
                        dueDate = it.dueDate
                        selectedHomeTab = HomeTab.TASK
                    }

                    is TaskNoteType.Note -> {
                        content = it.content
                        selectedHomeTab = HomeTab.NOTE
                    }
                }
            }
        } else {
            clearInputs()
        }
    }

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
        editingId = -1
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
        editingId = -1
    }

    fun addItem() {
        when (selectedHomeTab) {
            HomeTab.TASK -> {
                addTaskItem()
            }

            HomeTab.NOTE -> {
                addNoteItem()
            }
        }
    }

    fun saveItem() {
        if (editingId == -1) {
            addItem()
        } else {
            val index = itemList.indexOfFirst { it.id == editingId }
            if (index != -1) {
                itemList[index] = when (selectedHomeTab) {
                    HomeTab.TASK -> (itemList[index] as TaskNoteType.Task).copy(
                        title = title, dueDate = dueDate
                    )

                    HomeTab.NOTE -> (itemList[index] as TaskNoteType.Note).copy(
                        title = title, content = content
                    )
                }
            }
        }
    }

    val homeUiState by derivedStateOf {
        HomeUiState(
            selectedHomeTab = selectedHomeTab,
            showOnlyUncompleted = showOnlyUncompleted,
            filteredItems = filteredItems,
            isEmpty = filteredItems.isEmpty()
        )
    }

    val addUiState by derivedStateOf {
        AddUiState(
            editingId = editingId,
            title = title,
            dueDate = dueDate,
            content = content,
            selectedHomeTab = selectedHomeTab
        )
    }

    val homeUiActions = HomeUiActions(
        onHomeTabChange = onHomeTabChange,
        onSwitchChange = onSwitchChange,
        toggleTaskDone = toggleTaskDone,
        onChangeEditingId = onChangeEditingId
    )

    val addUiActions = AddUiActions(
        onTitleChange = onTitleChange,
        onDueDateChange = onDueDateChange,
        onContentChange = onContentChange,
        saveItem = ::saveItem
    )
}