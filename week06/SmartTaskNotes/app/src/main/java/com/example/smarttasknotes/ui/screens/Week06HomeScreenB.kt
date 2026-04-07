package com.example.smarttasknotes.ui.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smarttasknotes.ui.components.SingleChoiceButton
import com.example.smarttasknotes.ui.components.TaskNoteItem
import com.example.smarttasknotes.ui.components.TaskNoteTitle
import com.example.smarttasknotes.ui.components.TextInput
import com.example.smarttasknotes.util.HomeTab
import com.example.smarttasknotes.viewmodel.AddUiActions
import com.example.smarttasknotes.viewmodel.AddUiState
import com.example.smarttasknotes.viewmodel.HomeUiActions
import com.example.smarttasknotes.viewmodel.HomeUiState
import com.example.smarttasknotes.viewmodel.TaskNoteViewModel
import com.example.smarttasknotes.viewmodel.TaskNoteViewModelB

@Composable
fun Week06HomeScreenB(
    homeUiState: HomeUiState,
    homeUiActions: HomeUiActions,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TaskNoteTitle()
        Spacer(modifier = Modifier.height(20.dp))

        SingleChoiceButton(
            selectedHomeTab = homeUiState.selectedHomeTab,
            onSelectedChange = homeUiActions.onHomeTabChange
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "입력한 내용을 바로 목록에 추가해보세요 ✨",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("✨ 새 항목 추가")
        }

        if (homeUiState.filteredItems.isEmpty()) {
            Text(
                text = "📭 아직 등록된 항목이 없습니다.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            if (homeUiState.selectedHomeTab == HomeTab.TASK) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("미완성만 보기")
                    Switch(
                        checked = homeUiState.showOnlyUncompleted,
                        onCheckedChange = homeUiActions.onSwitchChange,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = homeUiState.filteredItems) {
                    TaskNoteItem(
                        item = it,
                        toggleTaskDone = homeUiActions.toggleTaskDone,
                        onDeleteTask = {},
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Week06HomeScreenBPreview() {
    val vm: TaskNoteViewModelB =viewModel()
    Week06HomeScreenB(
        vm.homeUiState,
        vm.homeUiActions
    )
}