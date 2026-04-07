package com.example.smarttasknotes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smarttasknotes.ui.components.SingleChoiceButton
import com.example.smarttasknotes.ui.components.TextInput
import com.example.smarttasknotes.util.HomeTab
import com.example.smarttasknotes.viewmodel.AddUiActions
import com.example.smarttasknotes.viewmodel.AddUiState
import com.example.smarttasknotes.viewmodel.TaskNoteViewModel

@Composable
fun Week06AddScreen(
    state: AddUiState,
    actions: AddUiActions,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = if (state.editingId != -1) "✨ 항목 수정" else "✨ 새 항목 추가",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "입력한 내용을 바로 목록에 추가해보세요 ✨",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        OutlinedTextField(
            value = state.title,
            onValueChange = actions.onTitleChange,
            label = { Text("제목") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (state.selectedHomeTab == HomeTab.NOTE) {
            TextInput(state.content, "📝 내용", onValueChange = actions.onContentChange)
        } else {
            TextInput(state.dueDate, "📅 마감일", onValueChange = actions.onDueDateChange)
        }

        Button(
            onClick = {
                /*TODO()*/
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            when (state.editingId) {
                -1 -> {
                    Text(
                        text = when (state.selectedHomeTab) {
                            HomeTab.TASK -> "✅ Task 저장"
                            HomeTab.NOTE -> "📝 Note 저장"
                        }
                    )
                }

                else -> {
                    Text(
                        text = when (state.selectedHomeTab) {
                            HomeTab.TASK -> "✅ Task 수정"
                            HomeTab.NOTE -> "📝 Note 수정"
                        }
                    )
                }
            }
        }
    }
}